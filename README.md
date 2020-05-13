# Easy-SMS

简化阿里云短信发送

## 简单开始

```java
    @Test
    public void aliYun() {
        // 签名
        String signName = "比地招标";
        // 手机号码
        String phoneNumbers = "13430741690";
        // 短信模板
        String templateCode = "SMS_63130288";
        // 短信模板
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
```

## 配置文件

easy-sms.yaml

```yaml
easySms:
  aliYun:
    regionId: cn-hangzhou
    accessKeyId: '******'
    accessSecret: '******'
    domain: dysmsapi.aliyuncs.com
    version: "2017-05-25"
  tenCent:
    endpoint: sms.tencentcloudapi.com
    region:
    secretId: '******'
    secretKey: '******'
    appIds:
      - '******'
    templateIds:
      - '******'
    signs:
      - '******'
```

## Maven

依赖配置

``仅限公司内部项目配置生效``

```xml
<dependency>
    <groupId>site.dunhanson.easy</groupId>
    <artifactId>easy-sms</artifactId>
    <version>2020.0513.1401</version>
</dependency>
```

关联依赖

```xml
<dependency>
    <groupId>org.slf4j</groupId>
    <artifactId>slf4j-api</artifactId>
    <version>1.7.30</version>
</dependency>
<dependency>
    <groupId>org.slf4j</groupId>
    <artifactId>slf4j-log4j12</artifactId>
    <version>1.7.30</version>
    <scope>test</scope>
</dependency>
<dependency>
    <groupId>org.yaml</groupId>
    <artifactId>snakeyaml</artifactId>
    <version>1.26</version>
</dependency>
<dependency>
    <groupId>org.apache.commons</groupId>
    <artifactId>commons-lang3</artifactId>
    <version>3.9</version>
</dependency>
<dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
    <version>1.18.12</version>
    <scope>provided</scope>
</dependency>
<dependency>
    <groupId>com.aliyun</groupId>
    <artifactId>aliyun-java-sdk-core</artifactId>
    <version>4.5.1</version>
</dependency>
```

## 参考资料

[阿里云-短信服务-API参考](https://help.aliyun.com/document_detail/101300.html)