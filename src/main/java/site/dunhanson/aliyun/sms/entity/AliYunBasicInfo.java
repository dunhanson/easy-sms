package site.dunhanson.aliyun.sms.entity;

import lombok.Data;

/**
 * @author dunhanson
 * @date 2020.04.16
 * @description 阿里云-短信基本信息
 */
@Data
public class AliYunBasicInfo {
    /**regionId**/
    private String regionId;
    /**accessKeyId**/
    private String accessKeyId;
    /**accessSecret**/
    private String accessSecret;
    /**domain**/
    private String domain;
    /**版本**/
    private String version;
}
