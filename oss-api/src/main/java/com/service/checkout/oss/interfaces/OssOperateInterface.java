package com.service.checkout.oss.interfaces;

import com.service.checkout.oss.model.OssFlowUploadRequest;
import com.service.checkout.oss.model.OssGetUrlRequest;
import com.service.checkout.oss.model.OssUploadRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * @Auther: yuepan
 * @Description: yuepan
 * <p>
 * ------------------------------------------------------
 * @Date: 2018/9/18 14:27
 * ------------------------------------------------------
 */
@Api(tags = "oss", description = "oss文件管理服务接口抽象")
@RequestMapping("/oss" + OssApiDef.VERSION+"/resouce")
public interface OssOperateInterface extends OssApiDef{
    @ApiOperation(value = "上传文件到oss bsae64上传 返回资源url 有效期1分钟",notes = "上传文件 返回oss url")
    @PostMapping("/")
    String uploadFile(@RequestBody @Valid OssUploadRequest request) throws Exception;
    @ApiOperation(value = "获取下载url 有效期1分钟",notes = "获取下载url")
    @GetMapping("/")
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(name = "expireDate", value = "有效期", paramType = "query"),
            @ApiImplicitParam(name = "bucketName", value = "空间名", paramType = "query"),
            @ApiImplicitParam(name = "objectName", value = "对象名", paramType = "query")})
    String getDownloadUrl(OssGetUrlRequest request);
    @ApiOperation(value = "上传文件到oss 流的形式 返回资源url 有效期1分钟",notes = "上传流文件")
    @PostMapping("/flow")
    String uploadFileFlow(@RequestBody @Valid OssFlowUploadRequest ossFlowUploadRequest) throws Exception;
    @ApiOperation(value = "上传文件到oss 流的形式 返回资源url 有效期1分钟",notes = "上传流文件")
    @GetMapping("/flow")
    void downloadFileFlow(HttpServletRequest request, HttpServletResponse response) throws Exception;
}
