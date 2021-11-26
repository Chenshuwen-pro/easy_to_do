package com.easytodo.common.dto;

import lombok.Data;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

@Data
public class LoginDto implements Serializable {

    @NotBlank(message = "昵称不能为空")
    private String username;

    @NotBlank(message = "密码不能为空")
    private String password;
}
