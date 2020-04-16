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

依赖配置

``仅限公司内部项目配置生效``

```xml
<dependency>
    <groupId>site.dunhanson.aliyun</groupId>
    <artifactId>easy-sms</artifactId>
    <version>2020.0416.1658</version>
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