package org.jeecgframework.core.common.model.json;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.annotate.JsonIgnore;

import java.util.Map;

/**
 * $.ajax后需要接受的JSON
 *
 * @author
 */
public class AjaxJson {

    private boolean success = true;// 是否成功
    private String msg = "操作成功";// 提示信息
    private Object obj = null;// 其他信息
    private Map<String, Object> attributes;// 其他参数

    public Map<String, Object> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    @com.fasterxml.jackson.annotation.JsonIgnore
    @JsonIgnore
    public String getJsonStr() {
        JSONObject obj = new JSONObject();
        obj.put("success", this.isSuccess());
        obj.put("msg", this.getMsg());
        obj.put("obj", this.obj);
        obj.put("attributes", this.attributes);
        return obj.toJSONString();
    }

    public static AjaxJson success() {
        return success(null,null,null);
    }

    public static AjaxJson success(String msg) {
        return success(msg,null,null);
    }

    public static AjaxJson success(Map<String, Object> attributes) {
        return success(null,attributes,null);
    }

    public static AjaxJson success(Object obj) {
        return success(null,null,obj);
    }

    public static AjaxJson success(String msg, Map<String, Object> attributes) {
        return success(msg, attributes,null);
    }

    public static AjaxJson success(String msg,Object obj) {
        return success(msg, null,obj);
    }

    public static AjaxJson success(String msg, Map<String, Object> attributes, Object obj) {
        AjaxJson ajaxJson = new AjaxJson();
        if(StringUtils.isNotEmpty(msg)) {
            ajaxJson.setMsg(msg);
        }
        ajaxJson.setAttributes(attributes);
        ajaxJson.setObj(obj);
        return ajaxJson;
    }

    public static AjaxJson faild(String msg) {
        AjaxJson ajaxJson = new AjaxJson();
        ajaxJson.setSuccess(false);
        ajaxJson.setMsg(msg);
        return ajaxJson;
    }
}
