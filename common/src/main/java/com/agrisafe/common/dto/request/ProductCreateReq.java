package com.agrisafe.common.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ProductCreateReq {
    @NotBlank(message = "name is required")
    private String name;
    @NotBlank(message = "productId is  required")
    private Integer productId;
}
