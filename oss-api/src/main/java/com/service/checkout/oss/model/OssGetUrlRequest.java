package com.service.checkout.oss.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * TODO 文件上传请求
 *
 * @author: yuepan
 * @date: 2018/9/19
 */
@Data
public class OssGetUrlRequest extends OssRequst{
    @ApiModelProperty("失效时间")
    private Integer expireDate;
}
