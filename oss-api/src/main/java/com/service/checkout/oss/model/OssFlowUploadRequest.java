package com.service.checkout.oss.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;

/**
 * TODO 文件上传请求
 *
 * @author: yuepan
 * @date: 2018/9/19
 */
@Data
public class OssFlowUploadRequest extends OssRequst{
    @ApiModelProperty("是否覆盖")
    private boolean coveredFlag;
    @NotNull(message = "文件不能为空")
    @ApiModelProperty("上传的文件 流的形式上传")
    private MultipartFile file;
}
