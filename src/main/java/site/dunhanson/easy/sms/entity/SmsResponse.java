package site.dunhanson.easy.sms.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author dunhanson
 * @date 2020.04.16
 * @description 短信返回对象
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SmsResponse {
    public Boolean success;
    public String code;
    public String message;
}
