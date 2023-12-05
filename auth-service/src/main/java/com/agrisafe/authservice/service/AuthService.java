package com.agrisafe.authservice.service;

import com.agrisafe.authservice.dto.req.LoginReq;
import com.agrisafe.authservice.dto.req.RegisterReq;
import com.agrisafe.authservice.dto.res.LoginRes;
import com.agrisafe.authservice.dto.res.RegisterRes;
import com.agrisafe.common.dto.response.UserResponseDTO;
import com.agrisafe.common.model.auth.Farmer;

public interface AuthService {
    LoginRes<UserResponseDTO> login(LoginReq loginReq);

    RegisterRes register(RegisterReq registerReq);

    Farmer getFarmer(String email);

}
