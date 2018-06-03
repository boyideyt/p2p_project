package com.itheima.domain;

/**
 * 用于封装错误结果和信息,直接在网页端js调用
 */
public class JsonResult {
    private int type;//0表示失败 1表示成功
    private String errorMsg;//错误信息
    private Object content;//成功时返回的任意对象或信息

    public int getType() {
        return type;
    }

    /**
     * 0表示失败 1表示成功
     * @param type
     */
    public void setType(int type) {
        this.type = type;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }
}
