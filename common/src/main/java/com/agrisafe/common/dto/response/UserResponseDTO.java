package com.agrisafe.common.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Builder
@Setter
public class UserResponseDTO {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
}
