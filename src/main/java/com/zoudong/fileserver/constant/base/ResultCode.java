package com.zoudong.fileserver.constant.base;

/**
 * @author zd
 * @description class
 * @date 2018/6/14 17:14
 */
public enum  ResultCode {

    succes("succes","业务成功"),
    error("error","未知错误");

    private String msg;
    private String code;
    private ResultCode(String code,String msg)
    {
        this.code=code;
        this.msg=msg;
    }

    public String getMsg()
    {
        return this.msg;
    }
    public String getCode() {
        return this.code;
    }
}
