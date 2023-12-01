package com.agrisafe.authservice.controller;

import com.agrisafe.authservice.dto.req.CreateFarmerReq;
import com.agrisafe.authservice.dto.res.CreateUserRes;
import com.agrisafe.authservice.service.AuthService;
import com.agrisafe.authservice.service.impl.JWTService;
import com.agrisafe.common.dto.response.UserResponseDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/farmer")
public class FarmerAuth {

    private final AuthService authService;
    public final JWTService jwtService;

    @PostMapping("/register")
    public CreateUserRes<UserResponseDTO> register(@Valid @RequestBody CreateFarmerReq createFarmerReq) {
        return authService.createFarmer(createFarmerReq);
    }

    @PostMapping("/validate")
    public Long validateFarmer(@RequestBody String token) {
        return jwtService.getFarmer(token);
    }
}
