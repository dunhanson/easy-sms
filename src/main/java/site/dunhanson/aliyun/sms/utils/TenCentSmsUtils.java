package site.dunhanson.aliyun.sms.utils;

import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.sms.v20190711.SmsClient;
import com.tencentcloudapi.sms.v20190711.models.SendSmsRequest;
import com.tencentcloudapi.sms.v20190711.models.SendSmsResponse;
import com.tencentcloudapi.sms.v20190711.models.SendStatus;
import lombok.extern.slf4j.Slf4j;
import site.dunhanson.aliyun.sms.entity.SmsResponse;
import site.dunhanson.aliyun.sms.entity.TenCentBasicInfo;
import java.util.ArrayList;
import java.util.List;

/**
 * @author dunhanson
 * @date 2020.05.13
 * @description 腾讯云-短信工具类
 */
@Slf4j
public class TenCentSmsUtils {
    public static final String path = "easy-sms.yaml";
    public static final String[] keyArr = new String[]{"easySms", "tenCent"};
    public static final TenCentBasicInfo basicInfo = YamlUtils.getEntity(TenCentBasicInfo.class, path, keyArr);
    private static final Credential credential = new Credential(basicInfo.getSecretId(), basicInfo.getSecretKey());

    /**
     * 发送单条短信，使用模板索引、默认的appId、sign
     * @param templateIdIndex
     * @param phoneNumber
     * @param templateParam
     * @return
     */
    public static SmsResponse sendSms(int templateIdIndex, String phoneNumber, String templateParam) {
        String templateId = basicInfo.getTemplateIds()[templateIdIndex];
        return sendSms(templateId, phoneNumber, templateParam);
    }

    /**
     * 发送多条短信，使用模板索引、默认的appId、sign
     * @param templateIdIndex
     * @param phoneNumbers
     * @param templateParams
     * @return
     */
    public static List<SmsResponse> sendSms(int templateIdIndex, String[] phoneNumbers, String[] templateParams) {
        String templateId = basicInfo.getTemplateIds()[templateIdIndex];
        return sendSms(templateId, phoneNumbers, templateParams);
    }

    /**
     * 发送单条短信，使用模板名称、默认的appId、sign
     * @param templateId
     * @param phoneNumber
     * @param templateParam
     * @return
     */
    public static SmsResponse sendSms(String templateId, String phoneNumber, String templateParam) {
        return sendSms(templateId, new String[]{phoneNumber}, new String[]{templateParam}).get(0);
    }

    /**
     * 发送多条短信,使用默认的appId、sign
     * @param templateId
     * @param phoneNumbers
     * @param templateParams
     * @return
     */
    public static List<SmsResponse> sendSms(String templateId, String[] phoneNumbers, String[] templateParams) {
        String appId = basicInfo.getAppIds()[0];
        String sign = basicInfo.getSigns()[0];
        return sendSms(appId, sign, templateId, phoneNumbers, templateParams);
    }

    /**
     * 发送单条短信
     * @param appId
     * @param sign
     * @param templateId
     * @param phoneNumber
     * @param templateParam
     * @return
     */
    public static List<SmsResponse> sendSms(String appId, String sign, String templateId, String phoneNumber, String templateParam) {
        return sendSms(appId, sign, templateId, new String[]{phoneNumber}, new String[]{templateParam});
    }

    /**
     * 发送多条短信
     * @param appId
     * @param sign
     * @param templateId
     * @param phoneNumbers
     * @param templateParams
     * @return
     */
    public static List<SmsResponse> sendSms(String appId, String sign, String templateId, String[] phoneNumbers, String[] templateParams) {
        List<SmsResponse> result = new ArrayList<>();
        HttpProfile httpProfile = new HttpProfile();
        httpProfile.setReqMethod("POST");
        //httpProfile.setConnTimeout(60);
        httpProfile.setEndpoint(basicInfo.getEndpoint());
        ClientProfile clientProfile = new ClientProfile();
        clientProfile.setSignMethod("HmacSHA256");
        clientProfile.setHttpProfile(httpProfile);
        // client
        SmsClient client = new SmsClient(credential, basicInfo.getRegion(),clientProfile);
        // request
        SendSmsRequest request = new SendSmsRequest();
        // 设置appId
        request.setSmsSdkAppid(appId);
        // 设置签名
        request.setSign(sign);
        // 设置模板ID
        request.setTemplateID(templateId);
        // 设置手机号码
        request.setPhoneNumberSet(phoneNumbers);
        // 设置模板参数
        request.setTemplateParamSet(templateParams);
        // 发送请求
        try {
            SendSmsResponse response = client.SendSms(request);
            SendStatus[] sendStatusSet = response.getSendStatusSet();
            for(int i = 0; i < sendStatusSet.length; i++) {
                SendStatus sendStatus = sendStatusSet[i];
                Long fee = sendStatus.getFee();
                boolean success = fee == 1 ? true : false;
                result.add(new SmsResponse(success, sendStatus.getCode(), sendStatus.getMessage()));
            }
        } catch (TencentCloudSDKException e) {
            log.error(e.getMessage());
        }
        return result;
    }
}
