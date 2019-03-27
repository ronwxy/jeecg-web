package org.jeecgframework.web.cgform.service.impl.config.util;

import org.hibernate.Session;
import org.hibernate.internal.SessionImpl;
import org.jeecgframework.web.cgform.service.config.DbTableHandleI;
import org.jeecgframework.web.cgform.service.config.DbTableServiceI;
import org.jeecgframework.web.cgform.service.impl.config.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 数据库工具类
 *
 * @author jueyue
 * 2013年7月6日
 */
public class DbTableUtil {

    /**
     * 获取列的Map
     * key 是 cloumn_name value 是 List<Map<String, Object>>
     *
     * @param queryForList
     * @return
     */
    public static Map<String, Object> getColumnMap(
            List<Map<String, Object>> queryForList) {
        Map<String, Object> columnMap = new HashMap<String, Object>();
        for (int i = 0; i < queryForList.size(); i++) {
            columnMap.put(queryForList.get(i).get("column_name").toString(), queryForList.get(i));
        }
        return columnMap;
    }

    /**
     * 把配置中的字段翻译成数据库中的字段  如:A ---> _a
     *
     * @param fileName
     * @return
     */
    public static String translatorToDbField(String fileName) {

        //去掉转换
        return fileName;
//		String name = "";
//		char[] chars = fileName.toCharArray();
//		for(int i =0 ;i<chars.length;i++){
//			name+= chars[i]>'A'&&chars[i]<'Z'?("_"+Character.toLowerCase(chars[i])):chars[i];
//		}
//		return name;
    }

    /**
     * 获取DB 维护表的工具类
     *
     * @return
     */
    public static DbTableServiceI getTableUtil(Session session) {
        DbTableServiceI tableUtil = null;
        String dialect = ((SessionImpl) session).getFactory().getDialect()
                .getClass().getName();
        if ("org.hibernate.dialect.MySQLDialect".equals(dialect)) {
            tableUtil = new DbTableServiceMysqlImpl();
        }
        return tableUtil;
    }

    public static DbTableHandleI getTableHandle(Session session) {
        DbTableHandleI dbTableHandle = null;
        String dialect = ((SessionImpl) session).getFactory().getDialect().getClass().getName();
        if ("org.hibernate.dialect.MySQLDialect".equals(dialect)) {
            dbTableHandle = new DbTableMysqlHandleImpl();
        } else if (dialect.contains("Oracle")) {
            dbTableHandle = new DbTableOracleHandleImpl();
        } else if ("org.hibernate.dialect.PostgreSQLDialect".equals(dialect)) {
            dbTableHandle = new DbTablePostgresHandleImpl();
        } else if ("org.hibernate.dialect.SQLServerDialect".equals(dialect)) {
            dbTableHandle = new TableSQLServerHandleImpl();
        } else if ("org.jeecgframework.core.common.hibernate.dialect.MySQLServer2008Dialect".equals(dialect)) {
            dbTableHandle = new TableSQLServerHandleImpl();
        }
        return dbTableHandle;
    }

    /**
     * 数据库类型
     *
     * @param session
     * @return
     */

    public static String getDataType(Session session) {
        String dataType = "MYSQL";
        String dialect = ((SessionImpl) session).getFactory().getDialect()
                .getClass().getName();
        if ("org.hibernate.dialect.MySQLDialect".equals(dialect)) {
            dataType = "MYSQL";
        } else if (dialect.contains("Oracle")) {
            dataType = "ORACLE";
        } else if ("org.hibernate.dialect.PostgreSQLDialect".equals(dialect)) {
            dataType = "POSTGRESQL";
        } else if ("org.hibernate.dialect.SQLServerDialect".equals(dialect)) {
            dataType = "SQLSERVER";
        } else if ("org.jeecgframework.core.common.hibernate.dialect.MySQLServer2008Dialect".equals(dialect)) {
            dataType = "SQLSERVER";
        }
        return dataType;
    }
}
