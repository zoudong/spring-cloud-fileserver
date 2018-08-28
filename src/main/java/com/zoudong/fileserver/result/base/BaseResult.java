package com.zoudong.fileserver.result.base;

import com.zoudong.fileserver.constant.base.ResultCode;
import lombok.Data;

import java.io.Serializable;

@Data
public class BaseResult<T> implements Serializable {
    //状态码
    private String code= ResultCode.succes.getCode();
    private String msg=ResultCode.succes.getMsg();
    private T data=null;
    private long time;
    public BaseResult(){

    }


}
