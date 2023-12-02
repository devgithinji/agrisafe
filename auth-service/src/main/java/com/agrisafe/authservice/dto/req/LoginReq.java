package com.agrisafe.authservice.dto.req;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginReq {
    @NotBlank(message = "email is required")
    @Email(message = "invalid email format")
    private String email;
    @NotBlank(message = "password is required")
    private String password;
}
