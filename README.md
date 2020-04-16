# Easy-SMS

简化阿里云短信发送

## 简单开始

```java
    @Test
    public void start() {
        String signName = "比地招标";
        String phoneNumbers = "134****1690";
        String templateCode = "SMS_******";
        String templateParam = "{\"code\":\"789456\"}";
        Result result = SmsUtils.sendSmsGetResult(signName, phoneNumbers, templateCode, templateParam);
        System.out.println(result);
    }
```

## 配置文件

aliyun.yaml

```yaml
aliyun:
  sms:
    regionId: cn-hangzhou
    accessKeyId: ******
    accessSecret: ******
    domain: dysmsapi.aliyuncs.com
    version: "2017-05-25"
```

## Maven

``仅限公司内部项目配置生效``

```xml
<dependency>
    <groupId>site.dunhanson.aliyun</groupId>
    <artifactId>easy-tablestore</artifactId>
    <version>2020.0326.1548</version>
</dependency>
```

## 参考资料

[阿里云-短信服务-API参考](https://help.aliyun.com/document_detail/101300.html)