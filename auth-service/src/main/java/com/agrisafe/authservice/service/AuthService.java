package com.agrisafe.authservice.service;

import com.agrisafe.authservice.dto.req.CreateFarmerReq;
import com.agrisafe.authservice.dto.res.CreateUserRes;
import com.agrisafe.common.dto.response.UserResponseDTO;

public interface AuthService {
    CreateUserRes<UserResponseDTO> createFarmer(CreateFarmerReq createFarmerReq);
}
