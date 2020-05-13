import org.junit.Test;
import site.dunhanson.aliyun.sms.entity.Result;
import site.dunhanson.aliyun.sms.utils.AliYunSmsUtils;

public class Start {

    @Test
    public void start() {
        String signName = "比地招标";
        String phoneNumbers = "134****1690";
        String templateCode = "SMS_******";
        String templateParam = "{\"code\":\"789456\"}";
        Result result = AliYunSmsUtils.sendSmsGetResult(signName, phoneNumbers, templateCode, templateParam);
        System.out.println(result);
    }

}
