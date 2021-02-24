package com.zenghui.easywrite.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Data
public class LoginDTO {

    @NotBlank(message = "用户名不能为空")
    @Size(min = 2, max = 15, message = "登录用户名长度在2-15")
    private String username;

//    @NotBlank(message = "昵称不能为空")
//    @Size(min = 2, max = 15, message = "昵称长度在2-15")
//    private String nickName;


    @NotBlank(message = "密码不能为空")
    @Size(min = 6, max = 20, message = "登录密码长度在6-20")
    private String password;

    @NotBlank(message = "账户等级不能为空")
    private String level;

    private Boolean rememberMe;
}