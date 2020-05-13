import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.sms.v20190711.SmsClient;
import com.tencentcloudapi.sms.v20190711.models.SendSmsRequest;
import com.tencentcloudapi.sms.v20190711.models.SendSmsResponse;
import org.junit.Test;
import site.dunhanson.aliyun.sms.entity.SmsResponse;
import site.dunhanson.aliyun.sms.entity.TenCentBasicInfo;
import site.dunhanson.aliyun.sms.utils.AliYunSmsUtils;
import site.dunhanson.aliyun.sms.utils.TenCentSmsUtils;
import site.dunhanson.aliyun.sms.utils.YamlUtils;

import java.util.List;

public class Start {

    @Test
    public void aliYun() {
        // 签名
        String signName = "比地招标";
        String phoneNumbers = "134****1690";
        String templateCode = "SMS_******";
        String templateParam = "{\"code\":\"789456\"}";
        // 发送短信
        SmsResponse smsResponse = AliYunSmsUtils.sendSms(signName, phoneNumbers, templateCode, templateParam);
        // 打印结果
        System.out.println(smsResponse);
    }

    @Test
    public void tenCentSingle() {
        // 模板的索引
        int templateIdIndex = 0;
        // 下发手机号码，采用 e.164 标准，+[国家或地区码][手机号]
        String phoneNumber = "+8613650933659";
        // 模板参数
        String templateParam = "123456";
        // 发送短信
        SmsResponse smsResponse = TenCentSmsUtils.sendSms(templateIdIndex, phoneNumber, templateParam);
        // 打印结果
        System.out.println(smsResponse);
    }

}
