package com.agrisafe.authservice.service.impl;

import com.agrisafe.authservice.dto.req.CreateFarmerReq;
import com.agrisafe.authservice.dto.res.CreateUserRes;
import com.agrisafe.authservice.dto.res.Token;
import com.agrisafe.authservice.repository.FarmerRepository;
import com.agrisafe.authservice.service.AuthService;
import com.agrisafe.common.dto.response.UserResponseDTO;
import com.agrisafe.common.exception.APIException;
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
    public CreateUserRes<UserResponseDTO> createFarmer(CreateFarmerReq createFarmerReq) {
        Optional<Farmer> optionalFarmer = farmerRepository.findFarmerByEmailOrIdNOOrPhoneNumber(
                createFarmerReq.getEmail(),
                createFarmerReq.getIdNo(),
                createFarmerReq.getPhoneNumber()
        );

        if (optionalFarmer.isPresent()) throw new APIException("farmer with similar details found");

        Farmer savedFarmer = farmerRepository.save(mapFarmerFromReq(createFarmerReq));

        String token = jwtService.generateToken(savedFarmer.getEmail(), "farmer");

        UserResponseDTO userResponseDTO = UserResponseDTO.builder()
                .email(savedFarmer.getEmail())
                .firstName(savedFarmer.getFirstName())
                .lastName(savedFarmer.getLastName())
                .phoneNumber(savedFarmer.getPhoneNumber())
                .build();


        return new CreateUserRes<>(
                "farmer created successfully",
                "farmer",
                new Token(token, jwtService.getExpirationDate(token)),
                userResponseDTO
        );
    }


    private Farmer mapFarmerFromReq(CreateFarmerReq createFarmerReq) {
        return Farmer.builder()
                .firstName(createFarmerReq.getFirstName())
                .lastName(createFarmerReq.getLastName())
                .email(createFarmerReq.getEmail())
                .idNO(createFarmerReq.getIdNo())
                .phoneNumber(createFarmerReq.getPhoneNumber())
                .address(createFarmerReq.getAddress())
                .password(passwordEncoder.encode(createFarmerReq.getPassword()))
                .build();

    }
}
