package com.xbz.vpase.response;

public class ResultJson<T>{
    public ResultJson(){
    }

    /**
     * 错误状态码
     */
    private int code;
    /**
     * 错误信息
     */
    private String message;
    /**
     * 返回数据
     */
    private T data;

    public void ResultJson(RetCode retCode){
        this.code = retCode.code();
        this.message = retCode.msg();
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}