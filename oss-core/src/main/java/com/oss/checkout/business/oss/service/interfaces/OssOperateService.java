package com.oss.checkout.business.oss.service.interfaces;

import com.service.checkout.oss.model.OssFlowUploadRequest;
import com.service.checkout.oss.model.OssGetUrlRequest;
import com.service.checkout.oss.model.OssUploadRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * TODO
 *
 * @author: yuepan
 * @date: 2018/9/18
 */
public interface OssOperateService {
     /** 
      * @Description: 上传文件 base64
      * @Param: 
      * @return: 
      * @Author: yuepan
      * -------------------------------------------
      * @Date: 2018/9/18
      * -----------------------------------------
      */ 
    String uploadFile(OssUploadRequest request) throws Exception;
     /**
      * @Description: 上传文件 流的形式
      * @Param:
      * @return:
      * @Author: yuepan
      * -------------------------------------------
      * @Date: 2018/9/20
      * -----------------------------------------
      */
    String uploadFileFlow(OssFlowUploadRequest ossFlowUploadRequest) throws Exception;
     /**
      * @Description: 获取下载url
      * @Param: 
      * @return: 
      * @Author: yuepan
      * -------------------------------------------
      * @Date: 2018/9/19
      * -----------------------------------------
      */ 
    String getDownloadUrl(OssGetUrlRequest request);
    /**
      * @Description: 获取下载url
      * @Param:
      * @return:
      * @Author: yuepan
      * -------------------------------------------
      * @Date: 2018/9/19
      * -----------------------------------------
      */
    void downloadFileFlow(HttpServletRequest request, HttpServletResponse response) throws Exception;
}
