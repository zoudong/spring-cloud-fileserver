package com.zoudong.fileserver.param.base;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class PageParam implements Serializable{

    /**
     * 第几页，从1开始
     */
    @NotNull(message = "页码不能为空!")
    @Min(value = 1)
    protected Integer pageNum;

    /**
     * 页尺寸
     */
    @NotNull(message = "页尺寸不能为空!")
    @Min(1)
    @Max(2000)
    protected Integer pageSize;

    /**
     * 排序 例如：id desc
     */
    protected String orderBy;

}