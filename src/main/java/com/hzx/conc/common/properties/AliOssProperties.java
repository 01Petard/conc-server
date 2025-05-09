package com.hzx.conc.common.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "aliyun.oss")
@Data
public class AliOssProperties {

    private String accessKeyId;
    private String accessKeySecret;
    private String endpoint;
    private String bucketName;

}
