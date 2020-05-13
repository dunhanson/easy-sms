package site.dunhanson.aliyun.sms.entity;

import lombok.Data;

/**
 * @author dunhanson
 * @date 2020.05.13
 * @description 腾讯云-短信基本信息
 */
@Data
public class TenCentBasicInfo {
    /**域名**/
    private String endpoint;
    /**地域**/
    private String region;
    /**secretId**/
    private String secretId;
    /**secretKey**/
    private String secretKey;
    /**appId**/
    private String[] appIds;
    /**签名**/
    private String[] signs;
    /**模板**/
    private String[] templateIds;
}
