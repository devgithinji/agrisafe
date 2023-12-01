package com.agrisafe.authservice.dto.res;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CreateUserRes<T> {
    private String message;
    private String userType;
    private Token token;
    private T user;

}
