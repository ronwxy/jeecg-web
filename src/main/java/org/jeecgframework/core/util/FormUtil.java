package org.jeecgframework.core.util;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.jeecgframework.web.system.manager.ClientManager;

import java.text.MessageFormat;
import java.util.*;

/**
 * 处理雷劈表单编辑器html解析内容
 *
 * @author 龙金波
 */
public class FormUtil {
    /**
     * view
     */
    private static String temp_view = "<div style=\"{0}\"/>{1}</div>";

    public static void main(String[] arg) {
        String test = "都 `发````";//test.replaceAll(regex, replacement);
        System.out.print(test.split("`").length);

    }

    /**
     * 解析后的html
     *
     * @param parseHtml 替换过控件的html
     * @param contentData 控件信息json数组
     * @param action view
     * @return
     */
    public static String getHtml(String parseHtml, String contentData,
                                 String action) {

        // action=action!=null && !"".equals(action)?action:"view";

        // 表单数据
        Map<String, Object> tableData = new HashMap<String, Object>();

        String html = parseHtml;
        JSONArray jsonArray = JSONArray.fromObject(contentData);
        for (int f = 0; f < jsonArray.size(); f++) {
            if (jsonArray.getJSONObject(f) == null
                    || "".equals(jsonArray.getJSONObject(f))) {
                continue;
            }

            // 获取对象
            JSONObject json = jsonArray.getJSONObject(f);

            String name = "";
            if (json == null) {
                continue;

            }
            String leipiplugins = json.getString("leipiplugins");
            if ("checkboxs".equals(leipiplugins)) {
                name = json.getString("parse_name");
            } else {
                name = json.getString("name");
            }

            String tempHtml = "";
            if ("text".equals(leipiplugins)) {
                tempHtml = getTextBox(json, tableData, action);
            } else if ("textarea".equals(leipiplugins)) {
                tempHtml = getTextArea(json, tableData, action);
            } else if ("radios".equals(leipiplugins)) {
                tempHtml = getRadios(json, tableData, action);
            } else if ("select".equals(leipiplugins)) {
                tempHtml = getSelect(json, tableData, action);
            } else if ("checkboxs".equals(leipiplugins)) {
                tempHtml = getCheckboxs(json, tableData, action);
            } else if ("macros".equals(leipiplugins)) {
                tempHtml = getMacros(json, tableData, action);
            } else if ("qrcode".equals(leipiplugins)) {
                tempHtml = getQrCode(json, tableData, action);
            } else if ("listctrl".equals(leipiplugins)) {
                tempHtml = getListCtrl(json, tableData, action);
            } else if ("progressbar".equals(leipiplugins)) {
                // case ""://进度条 (未做处理)
                /* temp_html = GetProgressbar(json, tableData, action); */
                tempHtml = json.getString("content");
            } else if ("popup".equals(leipiplugins)) {
                tempHtml = getPopUp(json, tableData, action);
            } else {
                tempHtml = json.getString("content");
            }

            html = html.replace("{" + name + "}", tempHtml);
        }
        return html;
    }

    // text
    private static String getTextBox(JSONObject item,
                                     Map<String, Object> formData, String action) {
        String temp = "<input type=\"text\" value=\"{0}\"  name=\"{1}\"  style=\"{2}\"/>";
        String name = item.getString("name");

        String value = formData.get(name) == null ? null : formData.get(name)
                .toString();
        if (value == null) {
            value = item.getString("value") == null ? "" : item.getString("value");
        }
        String style = item.getString("style") == null ? "" : item.getString("style");
        String tempHtml = MessageFormat.format(temp, value, name, style);
        if ("view".equals(action)) {
            return MessageFormat.format(temp_view, style, value);
        } else {
            return tempHtml;
        }
    }

    // popup
    private static String getPopUp(JSONObject item, Map<String, Object> formData, String action) {
        String dictionary = item.getString("dictionary");
        String[] dic = new String[]{"", "", ""};
        if (dictionary.split(",").length > 1) {
            dic = dictionary.split(",");
        }
        String temp = "<input type=\"text\" value=\"{0}\"   class=\"searchbox-inputtext\" value=\"\"  name=\"{1}\"  style=\"{2}\" onClick=\"inputClick(this,''{3}'',''{4}'');\" />";
        String name = item.getString("name");

        String value = "";
//		if (value == null)
//			value = item.getString("value") == null ? "" : item.getString(
//					"value").toString();
        String style = item.getString("style") == null ? "" : item.getString("style");
        String tempHtml = MessageFormat.format(temp, value, name, style, dic[1], dic[0]);
        if ("view".equals(action)) {
            return MessageFormat.format(temp_view, style, value, dic[1], dic[0]);
        } else {
            return tempHtml;
        }
    }

    // TextArea
    private static String getTextArea(JSONObject item,
                                      Map<String, Object> formData, String action) {
        String script = "";
        if (item.getString("orgrich") != null
                && "1".equals(item.getString("orgrich").toString())) {
            script = "orgrich=\"true\" ";
        }
        String name = item.getString("name").toString();

        String value = formData.get(name) == null ? null : formData.get(name)
                .toString();
        if (value == null) {
            value = item.getString("value") == null ? "" : item.getString(
                    "value").toString();
        }
        String style = item.getString("style") == null ? "" : item.getString(
                "style").toString();

        String temp = "<textarea  name=\"{0}\" id=\"{1}\"  style=\"{2}\" {3}>{4}</textarea>";

        String tempHtml = MessageFormat.format(temp, name, name, style,
                script, value);

        if ("view".equals(action)) {
            return MessageFormat.format(temp_view, style, value);
        } else {
            return tempHtml;
        }
    }

    // Radios
    private static String getRadios(JSONObject item,
                                    Map<String, Object> formData, String action) {
        JSONArray radiosOptions = item.getJSONArray("options");
        // JArray radiosOptions = item["options"] as JArray;
        String temp = "<input type=\"radio\" name=\"{0}\" value=\"{1}\"  {2}>{3}&nbsp;";
        String tempHtml = "";
        String name = item.getString("name").toString();
        String value = formData.get(name) == null ? null : formData.get(name)
                .toString();

        String cValue = "";

        for (int f = 0; f < radiosOptions.size(); f++) {
            JSONObject json = radiosOptions.getJSONObject(f);// 获取对象
            String jsonValue = json.getString("value").toString();
            String isChecked = "";

            if (value == null) {
                String check = (json.has("checked") && json
                        .getString("checked") != null) ? json.getString(
                        "checked").toString() : "";
                if ("checked".equals(check) || "true".equals(check)) {
                    isChecked = " checked=\"checked\" ";
                    cValue = jsonValue;
                }

            }

            tempHtml += MessageFormat.format(temp, name, jsonValue, isChecked,
                    jsonValue);
        }
        if ("view".equals(action)) {
            return MessageFormat.format(temp_view, "&nbsp;", cValue);
        } else {
            return tempHtml;
        }
    }

    // Checkboxs
    private static String getCheckboxs(JSONObject item,
                                       Map<String, Object> formData, String action) {
        String tempHtml = "";
        String temp = "<input type=\"checkbox\" name=\"{0}\" value=\"{1}\" {2}>{3}&nbsp;";

        String viewValue = "";// view 查看值

        JSONArray checkOptions = item.getJSONArray("options");
        for (int f = 0; f < checkOptions.size(); f++) {

            JSONObject json = checkOptions.getJSONObject(f);// 获取对象

            String name = json.getString("name").toString();
            String value = formData.get(name) == null ? null : formData.get(
                    name).toString();
            String cvalue = json.getString("value").toString();
            String isChecked = "";
            if (value == null) {
                String check = (json.has("checked") && json
                        .getString("checked") != null) ? json.getString(
                        "checked").toString() : "";
                if ("checked".equals(check) || "true".equals(check)) {
                    isChecked = " checked=\"checked\" ";
                    viewValue += cvalue + "&nbsp";// view 查看值
                }
            } else if (value != null && value.equals(cvalue)) {
                isChecked = " checked=\"checked\" ";
                viewValue += cvalue + "&nbsp";// view 查看值
            }

            tempHtml += MessageFormat.format(temp, name, cvalue, isChecked,
                    cvalue);

        }
        if ("view".equals(action)) {
            return MessageFormat.format(temp_view, "&nbsp;", viewValue);
        } else {
            return tempHtml;
        }
    }

    // Select(比较特殊)
    private static String getSelect(JSONObject item, Map<String, Object> formData, String action) {

        String name = item.getString("name").toString();
        String value = formData.get(name) == null ? null : formData.get(name)
                .toString();

        String tempHtml = item.getString("content").toString();
        // 用户设置过值
        if (value != null) {
            tempHtml = tempHtml.replace("selected=\"selected\"", "");
            value = "value=\"" + value + "\"";
            String r = value + " selected=\"selected\"";
            tempHtml = tempHtml.replace(value, r);
        }

        // 查看
        if ("view".equals(action)) {
            return MessageFormat.format(temp_view, "&nbsp;",
                    value != null ? value : item.getString("value").toString()
                            .split(",")[0]);
        } else {
            return tempHtml;
        }
    }

    // Macros
    private static String getMacros(JSONObject item,
                                    Map<String, Object> formData, String action) {
        String name = item.getString("name").toString();
        String value = formData.get(name) == null ? null : formData.get(name)
                .toString();
        String tempHtml = item.getString("content").toString();
        String microType = "text";
        if (value == null) {
            // region 制造规则值
            String type = item.getString("orgtype").toString();

            String dateFormat = "";

            //date”、“week”、“month”、“time”、“datetime”和“datetime-local
            Date date = new Date();
            if ("sys_date".equals(type)) {
                dateFormat = "yyyy-MM-dd";
                value = DateUtils.formatDate(date, dateFormat);
                microType = "date";
            } else if ("sys_date_cn".equals(type)) {
                dateFormat = "yyyy年MM月dd日";
                value = DateUtils.formatDate(date, dateFormat);
            } else if ("sys_date_cn_short3".equals(type)) {
                dateFormat = "yyyy年";
                value = DateUtils.formatDate(date, dateFormat);
            } else if ("sys_date_cn_short4".equals(type)) {
                dateFormat = "yyyy";
                value = DateUtils.formatDate(date, dateFormat);
            } else if ("sys_date_cn_short1".equals(type)) {
                dateFormat = "yyyy年MM月";
                value = DateUtils.formatDate(date, dateFormat);
                microType = "month";
            } else if ("sys_date_cn_short2".equals(type)) {
                dateFormat = "MM月dd日";
                value = DateUtils.formatDate(date, dateFormat);
            } else if ("sys_time".equals(type)) {
                dateFormat = "HH:mm:ss";
                microType = "time";
                value = DateUtils.formatDate(date, dateFormat);
            } else if ("sys_datetime".equals(type)) {
                dateFormat = "yyyy-MM-dd'T'HH:mm";
                microType = "datetime-local";
                value = DateUtils.formatDate(date, dateFormat);
            } else if ("sys_week".equals(type)) {
                // String[] Day = new String[] { "星期日", "星期一", "星期二", "星期三",
                // "星期四", "星期五", "星期六" };
                // value =
                // Day[Convert.ToInt32(DateTime.Now.DayOfWeek.ToString("d"))].ToString();
                value = DateUtils.formatDate(date, "EEEE");
                //microtype = "week";
            } else if ("sys_userid".equals(type)) {
                // if(!$def_value)
                // $def_value = $controller["user"]["uid"];
                // $tpl = str_replace("{macros}",$def_value,$tpl);
                value = "${userId}";
            } else if ("sys_realname".equals(type)) {
                // if(!$def_value)
                // $def_value = $controller["user"]["real_name"];
                value = "${userName}";
            } else {
            }
            // endregion
        }
        if ("view".equals(action)) {
            return value.replace("${userId}", ClientManager.getInstance().getClient().getUser().getId())
                    .replace("${userName}", ClientManager.getInstance().getClient().getUser().getUserName());
        }
        if (value != null) {
            tempHtml = tempHtml.replace("type=\"text\"", "type=\"" + microType + "\" ");
            return tempHtml.replace("{macros}", value);
        } else {
            return tempHtml;
        }
    }

    // Qrcode 二维码
    private static String getQrCode(JSONObject item,
                                    Map<String, Object> formData, String action) {
        String name = item.getString("name").toString();
        String value = formData.get(name) == null ? null : formData.get(name)
                .toString();
        String tempHtml = "";
        String temp = "";
        String orgType = item.getString("orgtype").toString();
        String style = item.getString("style").toString();
        if ("text".equals(orgType)) {
            orgType = "文本";
        } else if ("url".equals(orgType)) {
            orgType = "超链接";
        } else if ("tel".equals(orgType)) {
            orgType = "电话";
        }
        String qrCodeValue = "";
        if (item.getString("value") != null) {
            qrCodeValue = item.getString("value").toString();
        }
        // print_R($qrcode_value);exit; //array(value,qrcode_url)
        if ("edit".equals(action)) {
            temp = orgType
                    + "二维码 <input type=\"text\" name=\"{0}\" value=\"{1}\"/>";
            tempHtml = MessageFormat.format(temp, name, value);
        } else if ("view".equals(action)) {
            // 可以采用 http://qrcode.leipi.org/

            style = "";
            if (item.getString("orgwidth") != null) {
                style = "width:" + item.getString("orgwidth").toString()
                        + "px;";
            }
            if (item.getString("orgheight") != null) {
                style += "height:" + item.getString("orgheight").toString()
                        + "px;";
            }
            temp = "<img src=\"{0}\" title=\"{1}\" style=\"{2}\"/>";
            tempHtml = MessageFormat.format(tempHtml, name, value, style);

        } else if ("preview".equals(action)) {
            style = "";
            if (item.getString("orgwidth") != null) {
                style = "width:" + item.getString("orgwidth").toString()
                        + "px;";
            }
            if (item.getString("orgheight") != null) {
                style += "height:" + item.getString("orgheight").toString()
                        + "px;";
            }
            temp = "<img src=\"{0}\" title=\"{1}\" style=\"{2}\"/>";
            tempHtml = MessageFormat.format(tempHtml, name, value, style);
        }

        return tempHtml;
    }

    // Listctrl
    private static String getListCtrl(JSONObject item,
                                      Map<String, Object> formData, String action) {
        String valuetest = "{\"data_110\":[\"1\",\"2\"],\"data_111\":[\"21\",\"22\",\"22\"]}";

        String name = item.getString("name").toString();
        String value = formData.get(name) == null ? null : formData.get(name)
                .toString();
        String tempHtml = "";
        String orgSum = item.getString("orgsum").toString();
        String orgUnit = item.getString("orgunit").toString();
        String orgTitle = item.getString("orgtitle").toString();
        String title = item.getString("title").toString();
        String style = item.getString("style").toString();
        String orgcolvalue = item.getString("orgcolvalue").toString();
        String orgcoltype = item.getString("orgcoltype").toString();
        List<String> listTitle = Arrays.asList(orgTitle.split("`"));
        List<String> listSum = Arrays.asList(orgSum.split("`"));
        List<String> listUnit = Arrays.asList(orgUnit.split("`"));
        List<String> listValue = Arrays.asList(orgcolvalue.split("`"));
        List<String> listType = Arrays.asList(orgcoltype.split("`"));
        int tdCount = listTitle.size();

        String temp = "<table id=\""
                + name
                + "_table\" bindTable=\"true\" cellspacing=\"0\" class=\"table table-bordered table-condensed\" style=\""
                + style + "\"><thead>{0}</thead><tbody>{1}</tbody>{2}</table>";
        String btnAdd = "<span class=\"pull-right\"><button class=\"btn btn-small btn-success listAdd\" type=\"button\" tbname=\""
                + name + "\">添加一行</button></span>"; // 添加按钮
        String theader = "<tr><th colspan=\"{0}\">{1}{2}</th></tr>{3}";// 头部模版

        String trTitle = "";// 标题
        for (int i = 0; i < tdCount; i++) {
            if (i == tdCount - 1) {
                listTitle.set(i, "操作");
            }
            if ("view".equals(action) && i == tdCount - 1) {
                continue;// 如果是查看最后一列不显示
            }
            trTitle += MessageFormat.format("<th>{0}</th>", listTitle.get(i));
        }
        trTitle = "<tr>" + trTitle + "</tr>";

        JSONObject dataValue = JSONObject.fromObject(valuetest);

        int rowCount = dataValue != null ? dataValue.size() : 1;

        StringBuilder sbTr = new StringBuilder();
        // 如果有统计增加一行
        String tdSum = "";

        TreeMap<Integer, Float> sumValueDic = new TreeMap<>();
        for (int row = 0; row < rowCount; row++) {

            JSONArray rowValue = (dataValue != null && dataValue
                    .has(name + row)) ? dataValue.getJSONArray(name + row)
                    : null;

            // 默认一行
            String tr = "";
            for (int i = 0; i < tdCount; i++) {
                String tdName = name + "[" + i + "]";
                // 是否参与统计
                String sum = "1".equals(listSum.get(i)) ? "sum=\"" + tdName + "\"" : "";
                String tdValue = null;
                if (i < listValue.size()) {
                    tdValue = listValue.get(i);
                }
                tdValue = (rowValue != null && rowValue.size() > i) ? rowValue.getString(i).toString() : tdValue;
                // 类型
                String type = listType.get(i);

                // 一次循环计算该列的值
                if (!"".equals(sum)) {
                    // region 计算统计值
                    float tempTdValue = 0;
                    if (sumValueDic.containsKey(i)) {
                        tempTdValue = sumValueDic.get(i);
                    }
                    try {
                        float resultTdTemp = 0;
                        resultTdTemp = Float.parseFloat(tdValue);
                        // float.TryParse(tdValue, out resultTdTemp);
                        tempTdValue += resultTdTemp;
                    } catch (Exception e) {
                        tdValue = "0";
                    }
                    if (sumValueDic.containsKey(i)) {
                        sumValueDic.subMap(i, (int) tempTdValue);
                    } else {
                        sumValueDic.put(i, tempTdValue);
                    }
                    // endregion

                }

                // 最后一列不显示
                if (i == tdCount - 1)
                {
                    if ("view".equals(action)) {
                        continue;
                    }
                    // tr += "<td></td>";
                    else {
                        tr += "<td><a href=\"javascript:void(0);\" class=\"delrow \">删除</a></td>";
                    }
                    // tr +=
                    // string.Format("<td><a href=\"javascript:void(0);\" class=\"delrow {0}\">删除</a></td>",
                    // dataValue != null ? "" : "hide");
                } else {
                    if ("view".equals(action)) {
                        tr += MessageFormat.format("<td>{0}</td>", tdValue);
                    } else {
                        if ("text".equals(type)) {
                            tr += MessageFormat
                                    .format(
                                            "<td><input class=\"input-medium\" type=\"text\" value=\"{0}\" name=\"{1}[]\" {2}></td>",
                                            tdValue, tdName, sum);
                        } else if ("int".equals(type)) {
                            tr += MessageFormat
                                    .format(
                                            "<td><input class=\"input-medium\" type=\"text\" value=\"{0}\" name=\"{1}[]\" {2}></td>",
                                            tdValue, tdName, sum);
                        } else if ("textarea".equals(type)) {
                            tr += MessageFormat
                                    .format(
                                            "<td><textarea class=\"input-medium\" name=\"{0}\" >{1}</textarea></td>",
                                            tdName, tdValue, sum);
                        } else if ("calc".equals(type)) {
                            tr += MessageFormat
                                    .format(
                                            "<td><input class=\"input-medium\" type=\"text\" value=\"{0}\" name=\"{1}[]\" {2}></td>",
                                            tdValue, tdName, sum);
                        }
                    }
                }

                // 统计的行只有一行
                if (row == 0)
                {
                    // region
                    if (!"".equals(sum)) {
                        if ("view".equals(action)) {
                            tdSum += MessageFormat.format(
                                    "<td>合计：value{0}{1}</td>", i, listUnit
                                            .get(i));
                        } else {
                            tdSum += MessageFormat
                                    .format(
                                            "<td>合计：<input class=\"input-small\" type=\"text\" value=\"value{0}\" name=\"{1}[total]\" {2}\">{3}</td>",
                                            i, tdName, sum, listUnit.get(i));
                        }
                    } else {
                        tdSum += "<td></td>";
                    }
                    // endregion

                }

            }
            sbTr.append(MessageFormat.format("<tr class=\"template\">{0}</tr>",
                    tr));

        }
        /*
         * if(!StringUtils.isBlank(tdSum)){
         *
         * }
         */

        if (!StringUtils.isBlank(tdSum)) {
            for (Integer i : sumValueDic.keySet()) {
                tdSum = tdSum.replace("value" + i, sumValueDic.get(i)
                        .toString());
                tdSum = MessageFormat.format(
                        "<tbody class=\"sum\"><tr>{0}</tr></tbody>", tdSum);
            }
        }
        if ("view".equals(action)) {
            theader = MessageFormat
                    .format(theader, tdCount, title, "", trTitle);
        } else {
            theader = MessageFormat.format(theader, tdCount, title, btnAdd,
                    trTitle);
        }

        tempHtml = MessageFormat.format(temp, theader, sbTr.toString(), tdSum);

        return tempHtml;
    }
}
