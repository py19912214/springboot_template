package com.service.checkout.oss.model;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * TODO
 *
 * @author: yuepan
 * @date: 2018/9/19
 */
@Data
public class OssRequst {
    @NotNull(message = "空间名不能为空")
    @ApiModelProperty("空间名")
    private String bucketName;
    @NotNull(message = "对象名不能为空")
    @ApiModelProperty("对象名")
    private String objectName;
    @ApiModelProperty("文件是否是共享的 默认不是 如果是共享的文件不会调用oss获取url直接服务器根据参数拼一个url出来")
    private boolean shareFlag;
}
