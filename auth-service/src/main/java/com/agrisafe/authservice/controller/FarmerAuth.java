package com.agrisafe.authservice.controller;

import com.agrisafe.authservice.dto.req.LoginReq;
import com.agrisafe.authservice.dto.req.RegisterReq;
import com.agrisafe.authservice.dto.res.LoginRes;
import com.agrisafe.authservice.dto.res.RegisterRes;
import com.agrisafe.authservice.service.AuthService;
import com.agrisafe.authservice.service.impl.JWTService;
import com.agrisafe.common.dto.response.UserResponseDTO;
import com.agrisafe.common.model.auth.Farmer;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/farmer")
public class FarmerAuth {

    private final AuthService authService;
    public final JWTService jwtService;

    @PostMapping("/login")
    public LoginRes<UserResponseDTO> login(@Valid @RequestBody LoginReq loginReq) {
        return authService.login(loginReq);
    }

    @PostMapping("/register")
    public RegisterRes register(@Valid @RequestBody RegisterReq registerReq) {
        return authService.register(registerReq);
    }


    @PostMapping("/validate")
    public String validateFarmer(@RequestBody String token) {
        return jwtService.getFarmer(token);
    }

    @GetMapping("/{email}")
    public Farmer getFarmer(@PathVariable("email") String email) {
        return authService.getFarmer(email);
    }
}
