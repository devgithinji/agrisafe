package com.agrisafe.authservice.dto.res;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@Getter
@Setter
public class Token {
    private final String token;
    private final Date expiryDate;

}
