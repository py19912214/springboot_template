package com.service.checkout.oss.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * TODO 文件上传请求
 *
 * @author: yuepan
 * @date: 2018/9/19
 */
@Data
public class OssUploadRequest extends OssRequst{
    @ApiModelProperty("内容")
    @NotNull(message = "上传内容不能为空")
    private String content;
    @ApiModelProperty("是否覆盖")
    private boolean coveredFlag;
}
