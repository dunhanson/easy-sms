package site.dunhanson.aliyun.sms.utils;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.extern.slf4j.Slf4j;
import site.dunhanson.aliyun.sms.entity.BasicInfo;
import site.dunhanson.aliyun.sms.entity.Result;
import java.lang.reflect.Type;
import java.util.Map;

/**
 * @author dunhanson
 * @date 2020.04.16
 * @description 短信工具类
 */
@Slf4j
public class SmsUtils {
    private static final String path = "aliyun.yaml";
    private static final String[] keyArr = new String[]{"aliyun", "sms"};
    private static final BasicInfo basicInfo = YamlUtils.getEntity(BasicInfo.class, path, keyArr);
    private static final IAcsClient iAcsClient = getIAcsClient();

    /**
     * 获取IAcsClient
     * @return
     */
    public static IAcsClient getIAcsClient() {
        DefaultProfile profile = DefaultProfile.getProfile(
                basicInfo.getRegionId(),
                basicInfo.getAccessKeyId(),
                basicInfo.getAccessSecret());
        return new DefaultAcsClient(profile);
    }

    /**
     * 发起请求
     * @param request
     * @return
     */
    public static CommonResponse getCommonResponse(CommonRequest request) {
        //发送请求
        CommonResponse response = null;
        try {
            response = iAcsClient.getCommonResponse(request);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return response;
    }

    /**
     * 发送短信
     * @param signName 签名
     * @param phoneNumbers 支持对多个手机号码发送短信，手机号码之间以英文逗号（,）分隔。上限为1000个手机号码。
     * @param templateCode 模板代码
     * @param templateParam 模板内容
     * @return
     */
    public static CommonResponse sendSms(String signName, String phoneNumbers, String templateCode,
                                         String templateParam) {
        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain(basicInfo.getDomain());
        request.setSysVersion(basicInfo.getVersion());
        request.setSysAction("SendSms");
        request.putQueryParameter("SignName", signName);
        request.putQueryParameter("PhoneNumbers", phoneNumbers);
        request.putQueryParameter("TemplateCode", templateCode);
        request.putQueryParameter("TemplateParam", templateParam);
        return getCommonResponse(request);
    }

    /**
     * 发送短信
     * @param signName 签名
     * @param phoneNumbers 支持对多个手机号码发送短信，手机号码之间以英文逗号（,）分隔。上限为1000个手机号码。
     * @param templateCode 模板代码
     * @param templateParam 模板内容
     * @return
     */
    public static Result sendSmsGetResult(String signName, String phoneNumbers, String templateCode,
                                          String templateParam) {
        CommonResponse response = sendSms(signName, phoneNumbers, templateCode, templateParam);
        String json = response.getData();
        Type type = new TypeToken<Map<String, String>>(){}.getType();
        Map<String, String> map = new Gson().fromJson(json, type);
        String message = map.get("Message");
        String code = map.get("Code");
        if(code.equals("OK")) {
            return new Result(Boolean.TRUE, code, message);
        };
        return new Result(Boolean.FALSE, code, message);
    }

}
