package com.oss.checkout.business.oss.service.impl;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.OSSObject;
import com.oss.checkout.business.oss.bean.OssClientBean;
import com.oss.checkout.business.oss.consts.OssConst;
import com.oss.checkout.business.oss.service.interfaces.OssOperateService;
import com.oss.checkout.business.oss.utils.OssUtil;
import com.oss.checkout.config.exception.BusinessException;
import com.oss.checkout.config.exception.ErrorCode;
import com.service.checkout.oss.model.OssFlowUploadRequest;
import com.service.checkout.oss.model.OssGetUrlRequest;
import com.service.checkout.oss.model.OssUploadRequest;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
 * TODO
 *
 * @author: yuepan
 * @date: 2018/9/18
 */
@Service
public class OssOperateServiceImpl implements OssOperateService {
    @Autowired
    OssClientBean ossClientBean;
    @Override
    public String uploadFile(OssUploadRequest request) throws Exception {
        // 解密
        if (request.getContent().startsWith(OssConst.FILE_BASE_CODE_START_STR)) {
            request.setContent(request.getContent().replaceAll(OssConst.FILE_BASE_CODE_REGX, OssConst.EMPPTY_STR));
        }
        Base64 base64 = new Base64();
        byte[] b = base64.decode(request.getContent());
        // 处理数据
        for (int i = 0; i < b.length; ++i) {
            if (b[i] < 0) {
                b[i] += 256;
            }
        }
        return OssUtil.uploadReturnUrl(new ByteArrayInputStream(b),
                ossClientBean,
                request.isCoveredFlag(),
                request.getBucketName(),
                request.getObjectName(),
                request.isShareFlag());

    }

    @Override
    public String uploadFileFlow(OssFlowUploadRequest request) throws Exception {
        return OssUtil.uploadReturnUrl(request.getFile().getInputStream(),
                ossClientBean,
                request.isCoveredFlag(),
                request.getBucketName(),
                request.getObjectName(),
                request.isShareFlag());
    }

    @Override
    public String getDownloadUrl(OssGetUrlRequest request) {
        if(StringUtils.isEmpty(request.getBucketName())||StringUtils.isEmpty(request.getObjectName())){
            throw new BusinessException(ErrorCode.PARAM_ERROR,"空间名跟对象名不能为空");
        }
        // 创建OSSClient实例。
        OSSClient ossClient = new OSSClient(ossClientBean.getEndpoint(), ossClientBean.getAccessKeyId(), ossClientBean.getAccessKeySecret());
        try{
            return OssUtil.getOssDownloadUrl(ossClient,
                    request.getBucketName(),
                    request.getObjectName(),
                    request.getExpireDate(),
                    request.isShareFlag());
        }catch (Exception e){
            throw e;
        }finally {
            ossClient.shutdown();

        }
    }

    @Override
    public void downloadFileFlow(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String bucketName=request.getParameter("bucketName");
        String objectName=request.getParameter("objectName");
//        //判断文件是否存在
        if(StringUtils.isEmpty(bucketName)||StringUtils.isEmpty(objectName)){
            throw new BusinessException(ErrorCode.PARAM_ERROR,"空间名跟对象名不能为空");
        }
        OSSClient ossClient = new OSSClient(ossClientBean.getEndpoint(), ossClientBean.getAccessKeyId(), ossClientBean.getAccessKeySecret());
        try{
            //判断文件是否存在
            if(!ossClient.doesObjectExist(bucketName, objectName)){
                throw new BusinessException(ErrorCode.PARAM_ERROR,"根据对象名称无法获取文件");
            }
            // ossObject包含文件所在的存储空间名称、文件名称、文件元信息以及一个输入流。
            OSSObject ossObject = ossClient.getObject(bucketName,objectName);
            // 读取文件内容。
            response.setHeader("Content-Disposition", "attachment;filename="+objectName);
            response.setContentType("application/octet-stream"); //设置图片下载
            // 开始拷贝
            InputStream inputStream=new BufferedInputStream(ossObject.getObjectContent());
            byte[] buffer = new byte[inputStream.available()];
            int i = inputStream.read(buffer);
            while (i != -1) {
                response.getOutputStream().write(buffer, 0, i);
                i = inputStream.read(buffer);
            }
            response.getOutputStream().flush();
            // 关闭输入输出流
        }catch (Exception e){
            throw e;
        }finally {
            ossClient.shutdown();
        }
    }
}
