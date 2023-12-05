package com.agrisafe.common.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ProductCatCreateReq {

    @NotBlank(message = "name required")
    private String name;
}
