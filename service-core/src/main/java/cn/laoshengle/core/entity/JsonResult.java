package cn.laoshengle.core.entity;

import cn.laoshengle.core.constant.CommonConstant;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @description: 返回给前端的对象
 * @author: 龙逸
 * @createDate: 2020/04/29 17:58:04
 **/
public class JsonResult implements Serializable {
    private static final long serialVersionUID = 6832430666348874051L;

    /**
     * 结果Boolean类型
     */
    private Boolean success;

    /**
     * 结果信息
     */
    private String message;

    /**
     * 结果编码
     */
    private String code;

    /**
     * 返回对象
     */
    private Map<String, Object> objectMap;

    public JsonResult() {
    }

    public JsonResult(Boolean success, String message, String code, Map<String, Object> objectMap) {
        this.success = success;
        this.message = message;
        this.code = code;
        this.objectMap = objectMap;
    }

    public static JsonResult build(Boolean success, String message) {
        return new JsonResult(success, message, null, null);
    }

    public static JsonResult buildFail() {
        return buildFailMsg(CommonConstant.FAIL_MSG);
    }

    public static JsonResult buildFailMsg(String message) {
        return build(Boolean.FALSE, message, null, null);
    }

    public static JsonResult buildSuccess() {
        return buildSuccess(null);
    }

    public static JsonResult buildSuccess(Map<String, Object> objectMap) {
        return build(Boolean.TRUE, null, null, objectMap);
    }

    public static JsonResult buildSuccess(String key, Object value) {
        Map<String, Object> map = new HashMap<>();
        map.put(key, value);
        return build(Boolean.TRUE, CommonConstant.RESULT_SUCCESS_TEXT, CommonConstant.RESULT_SUCCESS_CODE, map);
    }

    public static JsonResult build(Boolean success, String message, String code, Map<String, Object> objectMap) {
        return new JsonResult(success, message, code, objectMap);
    }

    public static JsonResult buildSuccessMsg(String msg) {
        return build(Boolean.TRUE, msg, null, null);
    }

    public static JsonResult buildSuccessMsg(String code, String msg) {
        return build(Boolean.TRUE, msg, code, null);
    }

    public JsonResult addObject(String key, Object object) {
        if (this.objectMap == null) {
            this.objectMap = new HashMap<>();
        }
        this.objectMap.put(key, object);
        return this;
    }

    public <T> JsonResult concatObject(Map<String, T> map) {
        if (this.objectMap == null) {
            this.objectMap = new HashMap<>();
        }
        this.objectMap.putAll(map);
        return this;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Map<String, Object> getObjectMap() {
        return objectMap;
    }

    public void setObjectMap(Map<String, Object> objectMap) {
        this.objectMap = objectMap;
    }
}
