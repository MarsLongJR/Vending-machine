package com.xbz.vpase.response;

public enum  RetCode {
    OK(0, "SUCCESS"),
    NOPERM(501,"无权限操作"),
    ERROR(500, "操作失败"),
    PARAM_LENGTH_ERROR(5001,"参数过长"),
    SEND_MESSAGE_ERROR(5007,"短信发送出现异常"),
    PASS_ERROR(5002,"密码错误"),
    USER_ERROR(5003,"用户不存在"),
    NO_CONTENT(5005,"暂无数据"),
    NO_INPUT_PHONE(5006,"用户没有输入手机号"),
    PARAM_NULL(5007,"参数空传"),
    USER_ID_ERROR(5008,"用户编号无效")
    ;
    private int code;
    private String msg;

    RetCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int code() {
        return code;
    }

    public String msg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
