package com.oss.checkout.business.oss.controller;

import com.oss.checkout.business.oss.service.interfaces.OssOperateService;
import com.service.checkout.oss.interfaces.OssOperateInterface;
import com.service.checkout.oss.model.OssFlowUploadRequest;
import com.service.checkout.oss.model.OssGetUrlRequest;
import com.service.checkout.oss.model.OssUploadRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * TODO
 *
 * @author: yuepan
 * @date: 2018/9/18
 */
@Slf4j
@CrossOrigin
@RestController
class OssOperateController implements OssOperateInterface {
    @Autowired
    OssOperateService ossOperateService;
     @Override
    public String uploadFile(@RequestBody @Valid OssUploadRequest request) throws Exception {
        return ossOperateService.uploadFile(request);
    }

    @Override
    public String getDownloadUrl(OssGetUrlRequest request) {
        return ossOperateService.getDownloadUrl(request);
    }

    @Override
    public String uploadFileFlow(@Valid OssFlowUploadRequest ossFlowUploadRequest) throws Exception {
        return ossOperateService.uploadFileFlow(ossFlowUploadRequest);
    }

    @Override
    public void downloadFileFlow(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ossOperateService.downloadFileFlow(request,response);
    }
}
