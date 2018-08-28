package com.zoudong.fileserver.result.base;

import com.zoudong.fileserver.constant.base.ResultCode;

import java.io.Serializable;

/**
 * @author zd
 * @description class
 * @date 2018/6/14 17:28
 */
public class ResultUtil<T> implements Serializable{
    /**
     * 成功
     * @return
     */
    public static BaseResult succes() {
        return new BaseResult();
    }
    /**
     * 失败
     * @return
     */
    public static BaseResult error() {
        BaseResult baseResult=new BaseResult();
        baseResult.setCode(ResultCode.error.getCode());
        baseResult.setMsg(ResultCode.error.getMsg());
        return baseResult;
    }
    /**
     * 成功带数据
     * @return
     */
    public static <T>BaseResult fillSuccesData(T data) {
       BaseResult<T> baseResult=new BaseResult<T>();
       baseResult.setCode(ResultCode.succes.getCode());
       baseResult.setMsg(ResultCode.succes.getMsg());
       baseResult.setData(data);
       return baseResult;
    }
    /**
     * 失败带自定义错误码
     * @return
     */
    public static BaseResult fillErrorMsg(String code,String msg) {
        BaseResult baseResult=new BaseResult();
        baseResult.setCode(code);
        baseResult.setMsg(msg);
        return baseResult;
    }

    /**
     * 失败带自定义错误码和数据
     * @return
     */
    public static <T>BaseResult fillErrorMsgData(String code,String msg,T data) {
        BaseResult<T> baseResult=new BaseResult<T>();
        baseResult.setCode(code);
        baseResult.setMsg(msg);
        baseResult.setData(data);
        return baseResult;
    }

}
