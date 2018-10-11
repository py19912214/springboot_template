package com.oss.checkout.business.oss.utils;

import com.aliyun.oss.OSSClient;
import com.oss.checkout.business.oss.bean.OssClientBean;
import com.oss.checkout.config.exception.BusinessException;
import com.oss.checkout.config.exception.ErrorCode;
import org.apache.commons.lang3.StringUtils;

import java.io.InputStream;
import java.util.Calendar;
import java.util.Date;

/**
 * TODO
 *
 * @author: yuepan
 * @date: 2018/9/19
 */
public class OssUtil {
    public static final int expireDateDefault=60;
    //获取下载连接
    public static String getOssDownloadUrl(OSSClient ossClient, String bucketName, String objectName,Boolean shareFlag){
        // 设置URL过期时间为1分钟。
        return getOssDownloadUrl(ossClient,bucketName,objectName,expireDateDefault,shareFlag);
    }
    //获取下载连接  判断资源权限 有点复杂 还不如直接让前台传参数过来 如果是公有的 直接返回url 即使乱传也没事  私有的文件 是直接不能访问的
    public static String getOssDownloadUrl(OSSClient ossClient, String bucketName, String objectName,Integer secondOfExpireTime,Boolean shareFlag){
        //判断文件是否存在
        if(StringUtils.isEmpty(bucketName)||StringUtils.isEmpty(objectName)){
            throw new BusinessException(ErrorCode.PARAM_ERROR,"空间名跟对象名不能为空");
        }
        //判断文件是否存在
        if(!ossClient.doesObjectExist(bucketName, objectName)){
            throw new BusinessException(ErrorCode.PARAM_ERROR,"根据对象名称无法获取文件");
        }
        //判断是否有可读权限  如果有的话 不需要时间
        if(shareFlag){
            return buildOssUrl(ossClient.getEndpoint().toString(),bucketName,objectName);
        }
        if(secondOfExpireTime==null||secondOfExpireTime<0){
            secondOfExpireTime=expireDateDefault;
        }
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.SECOND,secondOfExpireTime);
        // 生成以GET方法访问的签名URL，访客可以直接通过浏览器访问相关内容。
        return ossClient.generatePresignedUrl(bucketName, objectName, calendar.getTime()).toString();
    }

     /** 
      * @Description: 上传文件并解析数据 然后返回url
      * @Param: 
      * @return: 
      * @Author: yuepan
      * -------------------------------------------
      * @Date: 2018/9/20
      * -----------------------------------------
      */ 
    public static String uploadReturnUrl(InputStream inputStream, OssClientBean ossClientBean, boolean isCovered, String bucketName, String objectName,Boolean shareFlag){
        // 创建OSSClient实例。
        OSSClient ossClient = new OSSClient(ossClientBean.getEndpoint(), ossClientBean.getAccessKeyId(), ossClientBean.getAccessKeySecret());
        try{
            //是否覆盖  如果不覆盖的话 需要判断文件是否存在 如果覆盖 就直接忽略 进行保存
            if(!isCovered){
                //判断文件是否存在
                if(ossClient.doesObjectExist(bucketName, objectName)){
                    //存在的话 直接返回url信息
                    String url= OssUtil.getOssDownloadUrl(ossClient,bucketName,objectName,shareFlag);
                    // 关闭OSSClient。
                    ossClient.shutdown();
                    return url;
                }
            }
            ossClient.putObject(bucketName,objectName,inputStream);
            return OssUtil.getOssDownloadUrl(ossClient,bucketName,objectName,shareFlag);
        }catch (Exception e){
            throw e;
        }finally {
            // 关闭OSSClient。
            ossClient.shutdown();

        }
    }
     /**
      * @Description: 拼接静态文件url
      * @Param:
      * @return:
      * @Author: yuepan
      * -------------------------------------------
      * @Date: 2018/9/20
      * -----------------------------------------
      */
    public static String buildOssUrl(String endpoint,String bucketName,String objectName){
        return String.format("http://%s.%s/%s",bucketName,endpoint.replaceAll("http://",""),objectName);
    }
    public static void main(String[] args) {
        Date date=new Date(1537339082000L);
        System.out.println(date);
        String asdasd="yue_asd,data:image/jpeg;base64,pand_as";
        System.out.println(asdasd.replaceAll("data:image/[a-z]{0,10};base64,",""));
    }
}
