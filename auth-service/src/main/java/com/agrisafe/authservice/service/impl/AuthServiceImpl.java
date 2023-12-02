package com.agrisafe.authservice.service.impl;

import com.agrisafe.authservice.dto.req.LoginReq;
import com.agrisafe.authservice.dto.req.RegisterReq;
import com.agrisafe.authservice.dto.res.LoginRes;
import com.agrisafe.authservice.dto.res.RegisterRes;
import com.agrisafe.authservice.dto.res.Token;
import com.agrisafe.authservice.repository.FarmerRepository;
import com.agrisafe.authservice.service.AuthService;
import com.agrisafe.common.dto.response.UserResponseDTO;
import com.agrisafe.common.exception.APIException;
import com.agrisafe.common.exception.ResourceNotFoundException;
import com.agrisafe.common.model.Farmer;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final JWTService jwtService;
    private final FarmerRepository farmerRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public LoginRes<UserResponseDTO> login(LoginReq loginReq) {
        Farmer farmer = farmerRepository.findFarmerByEmail(
                loginReq.getEmail()
        ).orElseThrow(() -> new ResourceNotFoundException("farmer", "email", loginReq.getEmail()));


        if (!passwordEncoder.matches(loginReq.getPassword(), farmer.getPassword()))
            throw new APIException("invalid email or password");


        String token = jwtService.generateToken(farmer.getEmail(), "farmer");

        UserResponseDTO userResponseDTO = UserResponseDTO.builder()
                .email(farmer.getEmail())
                .firstName(farmer.getFirstName())
                .lastName(farmer.getLastName())
                .phoneNumber(farmer.getPhoneNumber())
                .build();


        return new LoginRes<>(
                "logged in successfully",
                "farmer",
                new Token(token, jwtService.getExpirationDate(token)),
                userResponseDTO
        );
    }

    @Override
    public RegisterRes register(RegisterReq registerReq) {
        Optional<Farmer> optionalFarmer = farmerRepository.findFarmerByEmailOrIdNOOrPhoneNumber(
                registerReq.getEmail(),
                registerReq.getIdNo(),
                registerReq.getPhoneNumber()
        );

        if (optionalFarmer.isPresent()) throw new APIException("farmer with similar details found");

        farmerRepository.save(mapFarmerFromReq(registerReq));

        return new RegisterRes("farmer created successfully");
    }

    @Override
    public Farmer getFarmer(String email) {
        return farmerRepository.findFarmerByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("farmer", "email", email));
    }


    private Farmer mapFarmerFromReq(RegisterReq registerReq) {
        return Farmer.builder()
                .firstName(registerReq.getFirstName())
                .lastName(registerReq.getLastName())
                .email(registerReq.getEmail())
                .idNO(registerReq.getIdNo())
                .phoneNumber(registerReq.getPhoneNumber())
                .address(registerReq.getAddress())
                .password(passwordEncoder.encode(registerReq.getPassword()))
                .build();

    }
}
