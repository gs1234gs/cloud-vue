package com.tianchang.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginUser {
    //用户名
    private String userName;
    //密码
    private String password;
    //验证码
    private String securityCode;
}
