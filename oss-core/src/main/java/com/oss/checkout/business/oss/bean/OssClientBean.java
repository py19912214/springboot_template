package com.oss.checkout.business.oss.bean;

import lombok.Data;
import org.springframework.context.annotation.Configuration;

/**
 * TODO
 *
 * @author: yuepan
 * @date: 2018/9/19
 */
@Configuration
@Data
//@ConfigurationProperties(prefix = "api.jpush.klight",exceptionIfInvalid=false)
public class OssClientBean {
    private String accessKeyId="LTAIsSASKarWY2Xu";
    private String accessKeySecret="Nt6iZzrmq5TWMWBcS5Dhj7VJ3MLHoY";
    private String endpoint="http://oss-cn-qingdao.aliyuncs.com";
}
