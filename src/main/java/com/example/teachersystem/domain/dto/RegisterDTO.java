package com.example.teachersystem.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class RegisterDTO {
    @Length(min = 4, max = 16, message = "用户名长度必须在4-16位")
    @Schema(description = "用户名", required = true)
    private String username;
    @Email
    @NotBlank
    @Schema(description = "邮箱", required = true)
    private String email;
    @Length(min = 8, max = 20, message = "密码长度必须在8-20位")
    @Schema(description = "密码", required = true)
    private String password;
    @Length(min = 6, max = 6, message = "令牌长度6位")
    @Schema(description = "令牌", required = true)
    private String token;
    @Schema(description = "性别", required = true)
    private String gender;
}
