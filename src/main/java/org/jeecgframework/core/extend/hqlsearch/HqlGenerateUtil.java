package org.jeecgframework.core.extend.hqlsearch;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Restrictions;
import org.jeecgframework.core.annotation.query.QueryTimeFormat;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.common.QueryCondition;
import org.jeecgframework.core.extend.hqlsearch.parse.ObjectParseUtil;
import org.jeecgframework.core.extend.hqlsearch.parse.PageValueConvertRuleEnum;
import org.jeecgframework.core.extend.hqlsearch.parse.vo.HqlRuleEnum;
import org.jeecgframework.core.util.*;
import org.jeecgframework.web.system.pojo.base.TSDataRule;
import org.springframework.util.NumberUtils;

import javax.persistence.Column;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author 张代浩
 * @de
 */
@SuppressWarnings({"unchecked", "rawtypes"})
public class HqlGenerateUtil {

    /**
     * 时间查询符号
     */
    private static final String END = "_end";
    private static final String BEGIN = "_begin";

    private static final ThreadLocal<SimpleDateFormat> LOCAL = new ThreadLocal<SimpleDateFormat>();
    /**
     * 自定义sql表达式时rule_column的默认前缀
     */
    private static final String SQL_RULES_COLUMN = "SQL_RULES_COLUMN";
    private static String CLASS_JAVA_LANG_INTEGER = "class java.lang.Integer";
    private static String CLASS_JAVA_MATH_BIGDECIMAL = "class java.math.BigDecimal";
    private static String CLASS_JAVA_LANG_SHORT = "class java.lang.Short";
    private static String CLASS_JAVA_LANG_LONG = "class java.lang.Long";
    private static String CLASS_JAVA_LANG_FLOAT = "class java.lang.Float";
    private static String CLASS_JAVA_LANG_DOUBLE = "class java.lang.Double";

    private static SimpleDateFormat getTime() {
        SimpleDateFormat time = LOCAL.get();
        if (time == null) {
            time = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            LOCAL.set(time);
        }
        LOCAL.remove();
        return time;
    }

    /**
     * 自动生成查询条件HQL 模糊查询 不带有日期组合
     *
     * @param cq
     * @param searchObj
     * @throws Exception
     */
    public static void installHql(CriteriaQuery cq, Object searchObj) {
        installHql(cq, searchObj, null);

    }

    /**
     * 自动生成查询条件HQL（扩展区间查询功能）
     *
     * @param cq
     * @param searchObj
     * @param parameterMap request参数集合，封装了所有查询信息
     */
    public static void installHql(CriteriaQuery cq, Object searchObj, Map<String, String[]> parameterMap) {
        installHqlJoinAlias(cq, searchObj, getRuleMap(), parameterMap, "");
        try {
            String json = null;
            String sqlBuilder = "sqlbuilder";
            if (StringUtil.isNotEmpty(cq.getDataGrid().getSqlbuilder())) {
                json = cq.getDataGrid().getSqlbuilder();
            } else if (parameterMap != null && StringUtil.isNotEmpty(parameterMap.get(sqlBuilder))) {
                json = parameterMap.get(sqlBuilder)[0];
            }
            if (StringUtil.isNotEmpty(json)) {
                List<QueryCondition> list = JSONHelper.toList(json, QueryCondition.class);
                String sql = getSql(list, "", searchObj.getClass());
                LogUtil.debug("DEBUG sqlbuilder:" + sql);
                //TODO 此用法在多表关联查询，两个表存在相同字段的时候，会存在问题（hibernate维护的实体关系）
                cq.add(Restrictions.sqlRestriction(sql));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        cq.add();
    }

    /**
     * 添加Alias别名的查询
     *
     * @param cq
     * @param searchObj
     * @param parameterMap
     * @param alias
     * @date 2014年1月19日
     */
    private static void installHqlJoinAlias(CriteriaQuery cq, Object searchObj,
                                            Map<String, TSDataRule> ruleMap,
                                            Map<String, String[]> parameterMap, String alias) {
        PropertyDescriptor[] origDescriptors = PropertyUtils.getPropertyDescriptors(searchObj);
        ///直接拿到rulemap判断有没有key为xxx的直接循环组装cq

        boolean addPreCondition = true;
        for (String c : ruleMap.keySet()) {
            if (oConvertUtils.isNotEmpty(c) && c.startsWith(SQL_RULES_COLUMN)) {
                if (addPreCondition) {
                    cq.add(Restrictions.sqlRestriction("1=1"));
                    addPreCondition = false;
                }
                cq.add(Restrictions.sqlRestriction("(" + getSqlRuleValue(ruleMap.get(c).getRuleValue()) + ")"));
            }
        }

        String aliasName, name, type;
        for (PropertyDescriptor origDescriptor : origDescriptors) {
            aliasName = ("".equals(alias) ? "" : alias + ".") + origDescriptor.getName();
            name = origDescriptor.getName();
            type = origDescriptor.getPropertyType().toString();
            try {
                if (judgedIsUselessField(name) || !PropertyUtils.isReadable(searchObj, name)) {
                    continue;
                }
                // 如果规则包含这个属性
                if (ruleMap.containsKey(aliasName)) {
                    addRuleToCriteria(ruleMap.get(aliasName), aliasName, origDescriptor.getPropertyType(), cq);
                }

                // 添加 判断是否有区间值
                String beginValue = null;
                String endValue = null;
                if (parameterMap != null && parameterMap.containsKey(name + BEGIN)) {
                    beginValue = parameterMap.get(name + BEGIN)[0].trim();
                }
                if (parameterMap != null && parameterMap.containsKey(name + END)) {
                    endValue = parameterMap.get(name + END)[0].trim();
                }

                Object value = PropertyUtils.getSimpleProperty(searchObj, name);
                // 根据类型分类处理
                // 基础类型查询拼装的替换
                if (type.contains("class java.lang") || type.contains("class java.math")) {
                    createBaseDateTypeCriteria(cq, parameterMap, aliasName, name, type, beginValue, endValue, value);
                    // 日期类型查询拼装的替换
                } else if ("class java.util.Date".equals(type)) {
                    createDataTypeCriteria(cq, aliasName, origDescriptor, beginValue, endValue, value);
                    // 实体类类型查询拼装的替换
                } else if (!StringUtil.isJavaClass(origDescriptor.getPropertyType())) {
                    Object param = PropertyUtils.getSimpleProperty(searchObj, name);
                    boolean isInstallHqlJoinAlias = isHaveRuleData(ruleMap, aliasName) || (isNotEmpty(param) && itIsNotAllEmpty(param));
                    if (isInstallHqlJoinAlias) {
                        // 如果是实体类,创建别名,继续创建查询条件

                        // 用户反馈
                        String newAliasName = aliasName.replaceAll("\\.", "_");
                        cq.createAlias(aliasName, newAliasName);

                        installHqlJoinAlias(cq, param, ruleMap, parameterMap, newAliasName);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static void createDataTypeCriteria(CriteriaQuery cq, String aliasName, PropertyDescriptor origDescriptor,
                                               String beginValue, String endValue, Object value) throws ParseException {
        QueryTimeFormat format = origDescriptor.getReadMethod().getAnnotation(QueryTimeFormat.class);
        SimpleDateFormat userDefined = null;
        int longDataLength = 19;
        int shortDataLength = 10;
        if (format != null) {
            userDefined = new SimpleDateFormat(format.format());
        }
        if (StringUtils.isNotBlank(beginValue)) {
            if (userDefined != null) {
                cq.ge(aliasName, userDefined.parse(beginValue));
            } else if (beginValue.length() == longDataLength) {
                cq.ge(aliasName, getTime().parse(beginValue));
            } else if (beginValue.length() == shortDataLength) {
                cq.ge(aliasName, getTime().parse(beginValue + " 00:00:00"));
            }
        }
        if (StringUtils.isNotBlank(endValue)) {
            if (userDefined != null) {
                cq.ge(aliasName, userDefined.parse(beginValue));
            } else if (endValue.length() == longDataLength) {
                cq.le(aliasName, getTime().parse(endValue));
            } else if (endValue.length() == shortDataLength) {
                // 对于"yyyy-MM-dd"格式日期，因时间默认为0，故此添加" 23:59:59"并使用time解析，以方便查询日期时间数据
                cq.le(aliasName, getTime().parse(endValue + " 23:59:59"));
            }
        }
        if (isNotEmpty(value)) {
            cq.eq(aliasName, value);
        }
    }

    private static void createBaseDateTypeCriteria(CriteriaQuery cq, Map<String, String[]> parameterMap,
                                                   String aliasName, String name, String type, String beginValue,
                                                   String endValue, Object value) {
        // for：查询拼装的替换
        if (value != null && !"".equals(value)) {
            String checkboxValueSpliter = ",";
            //checkbox多选查询
            if (value.toString().startsWith(checkboxValueSpliter) && value.toString().endsWith(checkboxValueSpliter)) {
                String[] vals = value.toString().replace(",,", ",").split(",");
                //Criteria.add(Restrictions.or(cq, Restrictions.or(Restrictions.or(lhs, rhs))))
                //替代多个Restrictions.or
                Disjunction dis = Restrictions.disjunction();
                for (String val : vals) {
                    if (StringUtils.isNotBlank(val)) {
                        dis.add(Restrictions.eq(name, val));
                    }
                }
                cq.add(dis);
            } else {
                HqlRuleEnum rule = PageValueConvertRuleEnum.convert(value);
                //						if(HqlRuleEnum.LIKE.equals(rule)&&(!(value+"").contains("*"))&&!"class java.lang.Integer".contains(type)){
                //							value="*%"+String.valueOf(value.toString())+"%*";
                //						} else {
                //							rule = HqlRuleEnum.EQ;
                //						}
                value = PageValueConvertRuleEnum.replaceValue(rule, value);
                ObjectParseUtil.addCriteria(cq, aliasName, rule, value);
            }
        } else if (parameterMap != null) {
            addBeginAndEndCriteria(cq, aliasName, type, beginValue, endValue);
        }
    }

    private static void addBeginAndEndCriteria(CriteriaQuery cq, String aliasName, String type, String beginValue, String endValue) {
        Object beginValueObj = null, endValueObj = null;

        if (CLASS_JAVA_LANG_INTEGER.equals(type)) {
            if (!"".equals(beginValue) && null != beginValue) {
                beginValueObj = Integer.parseInt(beginValue);
            }
            if (!"".equals(endValue) && null != endValue) {
                endValueObj = Integer.parseInt(endValue);
            }
        } else if (CLASS_JAVA_MATH_BIGDECIMAL.equals(type)) {
            if (!"".equals(beginValue) && null != beginValue) {
                beginValueObj = new BigDecimal(beginValue);
            }
            if (!"".equals(endValue) && null != endValue) {
                endValueObj = new BigDecimal(endValue);
            }
        } else if (CLASS_JAVA_LANG_SHORT.equals(type)) {
            if (!"".equals(beginValue) && null != beginValue) {
                beginValueObj = Short.parseShort(beginValue);
            }
            if (!"".equals(endValue) && null != endValue) {
                endValueObj = Short.parseShort(endValue);
            }
        } else if (CLASS_JAVA_LANG_LONG.equals(type)) {
            if (!"".equals(beginValue) && null != beginValue) {
                beginValueObj = Long.parseLong(beginValue);
            }
            if (!"".equals(endValue) && null != endValue) {
                endValueObj = Long.parseLong(endValue);
            }
        } else if (CLASS_JAVA_LANG_FLOAT.equals(type)) {
            if (!"".equals(beginValue) && null != beginValue) {
                beginValueObj = Float.parseFloat(beginValue);
            }
            if (!"".equals(endValue) && null != endValue) {
                endValueObj = Float.parseFloat(endValue);
            }

        } else if (CLASS_JAVA_LANG_DOUBLE.equals(type)) {
            if (!"".equals(beginValue) && null != beginValue) {
                beginValueObj = Double.parseDouble(beginValue);
            }
            if (!"".equals(endValue) && null != endValue) {
                endValueObj = Double.parseDouble(endValue);
            }

        } else {
            beginValueObj = beginValue;
            endValueObj = endValue;
        }
        ObjectParseUtil.addCriteria(cq, aliasName, HqlRuleEnum.GE, beginValueObj);
        ObjectParseUtil.addCriteria(cq, aliasName, HqlRuleEnum.LE, endValueObj);
    }

    private static String getSqlRuleValue(String sqlRule) {
        try {
            Set<String> varParams = getSqlRuleParams(sqlRule);
            for (String var : varParams) {
                String tempValue = ResourceUtil.converRuleValue(var);
                sqlRule = sqlRule.replace("#{" + var + "}", tempValue);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sqlRule;
    }

    private static Set<String> getSqlRuleParams(String sql) {
        if (oConvertUtils.isEmpty(sql)) {
            return null;
        }
        Set<String> varParams = new HashSet<String>();
        String regex = "\\#\\{\\w+\\}";

        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(sql);
        while (m.find()) {
            String var = m.group();
            varParams.add(var.substring(var.indexOf("{") + 1, var.indexOf("}")));
        }
        return varParams;
    }


    /**
     * 判断数据规则是不是包含这个实体类
     *
     * @param ruleMap
     * @param aliasName
     * @return
     */
    private static boolean isHaveRuleData(Map<String, TSDataRule> ruleMap,
                                          String aliasName) {
        for (String key : ruleMap.keySet()) {
            if (key.contains(aliasName)) {
                return true;
            }
        }
        return false;
    }

    private static void addRuleToCriteria(TSDataRule tsDataRule,
                                          String aliasName, Class propertyType, CriteriaQuery cq) {
        HqlRuleEnum rule = HqlRuleEnum.getByValue(tsDataRule.getRuleConditions());
        if (HqlRuleEnum.IN.equals(rule)) {
            String[] values = tsDataRule.getRuleValue().split(",");
            Object[] objs = new Object[values.length];
            if (!propertyType.equals(String.class)) {
                for (int i = 0; i < values.length; i++) {
                    objs[i] = NumberUtils.parseNumber(values[i], propertyType);
                }
            } else {
                objs = values;
            }
            ObjectParseUtil.addCriteria(cq, aliasName, rule, objs);
        } else {
            if (propertyType.equals(String.class)) {
                ObjectParseUtil.addCriteria(cq, aliasName, rule, converRuleValue(tsDataRule.getRuleValue()));
            } else {
                ObjectParseUtil.addCriteria(cq, aliasName, rule, NumberUtils.parseNumber(tsDataRule.getRuleValue(), propertyType));
            }
        }
    }

    private static String converRuleValue(String ruleValue) {

        //这个方法建议去掉，直接调用ResourceUtil.converRuleValue(ruleValue)
        String value = ResourceUtil.converRuleValue(ruleValue);
        return value != null ? value : ruleValue;
    }

    private static boolean judgedIsUselessField(String name) {
        return "class".equals(name) || "ids".equals(name)
                || "page".equals(name) || "rows".equals(name)
                || "sort".equals(name) || "order".equals(name);
    }

    /**
     * 判断是不是空
     */
    public static boolean isNotEmpty(Object value) {
        return value != null && !"".equals(value);
    }

    /**
     * 判断这个类是不是所以属性都为空
     *
     * @param param
     * @return
     */
    private static boolean itIsNotAllEmpty(Object param) {
        boolean isNotEmpty = false;
        try {
            PropertyDescriptor[] origDescriptors = PropertyUtils
                    .getPropertyDescriptors(param);
            String name;
            for (PropertyDescriptor origDescriptor : origDescriptors) {
                name = origDescriptor.getName();
                if ("class".equals(name)
                        || !PropertyUtils.isReadable(param, name)) {
                    continue;
                }
                if (Map.class.isAssignableFrom(origDescriptor
                        .getPropertyType())) {
                    Map<?, ?> map = (Map<?, ?>) PropertyUtils
                            .getSimpleProperty(param, name);
                    if (map != null && map.size() > 0) {
                        isNotEmpty = true;
                        break;
                    }
                } else if (Collection.class.isAssignableFrom(origDescriptor
                        .getPropertyType())) {
                    Collection<?> c = (Collection<?>) PropertyUtils
                            .getSimpleProperty(param, name);
                    if (c != null && c.size() > 0) {
                        isNotEmpty = true;
                        break;
                    }
                } else if (StringUtil.isNotEmpty(PropertyUtils
                        .getSimpleProperty(param, name))) {
                    isNotEmpty = true;
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isNotEmpty;
    }

    private static Map<String, TSDataRule> getRuleMap() {
        List<TSDataRule> list = JeecgDataAutorUtils.loadDataSearchConditonSQL();

        //	(List<TSDataRule>) ContextHolderUtils.getRequest().getAttribute(Globals.MENU_DATA_AUTHOR_RULES)
        if (list == null || list.size() == 0) {
            return new HashMap<>(0);
        }
        if (list.get(0) == null) {
            return new HashMap<>(0);
        }

        Map<String, TSDataRule> ruleMap = new HashMap<>(list.size());
        for (TSDataRule rule : list) {

            String column = rule.getRuleColumn();
            if (oConvertUtils.isEmpty(column)) {
                column = SQL_RULES_COLUMN + rule.getId();
            }
            ruleMap.put(column, rule);
        }
        return ruleMap;
    }

//	--author：龙金波 ------start---date：20150628--------for：sql高级查询器参数的sql组装

    /**
     * @param list
     * @param tab tab格式化
     * @param claszz
     * @return
     * @author ljb
     * 根据对象拼装sql
     * TODO 结合DataRule
     */
    public static String getSql(List<QueryCondition> list, String tab, Class claszz) {

        if (list == null || list.size() == 0) {
            return "";
        }

        StringBuffer sb = new StringBuffer();
        String orSql = "or";
        if (orSql.equals(list.get(0).getRelation())) {
            sb.append(" 1=0 ");
        } else {
            sb.append(" 1=1 ");
        }

        for (QueryCondition c : list) {
            String column = invokeFindColumn(claszz, c.getField());
            String type = invokeFindType(claszz, c.getField());
            c.setType(type);
            c.setField(column);
            sb.append(tab + c);
            sb.append("\r\n");
            if (c.getChildren() != null) {
                List list1 = JSONHelper.toList(c.getChildren(), QueryCondition.class);
                sb.append(tab);
                sb.append(c.getRelation() + "( ");
                sb.append(getSql(list1, tab + "\t", claszz));
                sb.append(tab + ")\r\n");
            }
        }
        return sb.toString();
    }

    /**
     * 根据字段名称,获取字段的类型字符串
     * return: java.lang.Integer
     */
    public static String invokeFindType(Class clazz, String fieldName) {
        String type = null;
        Field field;
        try {
            field = clazz.getDeclaredField(fieldName);
            if (field != null) {
                type = field.getType().getSimpleName();
            }
        } catch (Exception e) {
            return type;
        }
        return type;
    }

    /**
     * 根据字段名称返回hibernate映射数据库字段名
     *
     * @param clazz
     * @param fieldName 字段名称
     * @return
     */
    public static String invokeFindColumn(Class clazz, String fieldName) {
        String column = null;
        Field field;
        try {

            //TODO	只能向上找一级，其他则失败。
            boolean flag = getSuperDeclaredField(clazz, fieldName);
            if (flag) {
                field = clazz.getDeclaredField(fieldName);
            } else {
                Class cla = clazz.getSuperclass();
                field = cla.getDeclaredField(fieldName);
            }

            PropertyDescriptor pd = new PropertyDescriptor(field.getName(), clazz);
            //获得get方法
            Method getMethod = pd.getReadMethod();
            Column col = getMethod.getAnnotation(Column.class);
            if (col != null) {
                column = col.name();
            }
        } catch (Exception e) {
            return column;
        }
        return column;
    }


    /**
     * 获取装载数据权限条件的HQL
     *
     * @param cq
     * @param searchObj
     * @return cq
     */
    public static CriteriaQuery getDataAuthorConditionHql(CriteriaQuery cq, Object searchObj) {
        Map<String, TSDataRule> ruleMap = getRuleMap();
        PropertyDescriptor[] origDescriptors = PropertyUtils.getPropertyDescriptors(searchObj);
        String aliasName, name, type;
        for (PropertyDescriptor origDescriptor : origDescriptors) {
            aliasName = origDescriptor.getName();
            name = origDescriptor.getName();
            type = origDescriptor.getPropertyType().toString();
            try {
                if (judgedIsUselessField(name) || !PropertyUtils.isReadable(searchObj, name)) {
                    continue;
                }
                // 如果规则包含这个属性
                if (ruleMap.containsKey(aliasName)) {
                    addRuleToCriteria(ruleMap.get(aliasName), aliasName, origDescriptor.getPropertyType(), cq);
                }

                Object value = PropertyUtils.getSimpleProperty(searchObj, name);
                // 根据类型分类处理
                if (type.contains("class java.lang") || type.contains("class java.math")) {

                    // for：查询拼装的替换
                    if (value != null && !"".equals(value)) {
                        HqlRuleEnum rule = PageValueConvertRuleEnum.convert(value);
                        value = PageValueConvertRuleEnum.replaceValue(rule, value);
                        ObjectParseUtil.addCriteria(cq, aliasName, rule, value);
                    }

                    // for：查询拼装的替换
                } else if ("class java.util.Date".equals(type)) {
                    QueryTimeFormat format = origDescriptor.getReadMethod().getAnnotation(QueryTimeFormat.class);
                    SimpleDateFormat userDefined = null;
                    if (format != null) {
                        userDefined = new SimpleDateFormat(format.format());
                    }
                    if (isNotEmpty(value)) {
                        cq.eq(aliasName, value);
                    }
                } else if (!StringUtil.isJavaClass(origDescriptor.getPropertyType())) {
                    Object param = PropertyUtils.getSimpleProperty(searchObj, name);
                    boolean isGetDataAuthorConditionHql = isHaveRuleData(ruleMap, aliasName) || (isNotEmpty(param) && itIsNotAllEmpty(param));
                    if (isGetDataAuthorConditionHql) {
                        // 如果是实体类,创建别名,继续创建查询条件

                        // for：用户反馈
                        cq.createAlias(aliasName, aliasName.replaceAll("\\.", "_"));

                        getDataAuthorConditionHql(cq, param);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return cq;
    }

    /**
     * 判断有没有field字段
     *
     * @param clazz
     * @param fieldName
     * @return
     */
    public static boolean getSuperDeclaredField(Class clazz, String fieldName) {
        Field[] fields = clazz.getDeclaredFields();
        boolean b = false;
        for (Field field : fields) {
            if (field.getName().equals(fieldName)) {
                b = true;
                break;
            }
        }
        return b;
    }

}
