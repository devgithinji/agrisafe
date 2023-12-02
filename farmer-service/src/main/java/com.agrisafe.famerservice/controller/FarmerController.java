package com.agrisafe.famerservice.controller;

import com.agrisafe.famerservice.model.FarmerUserDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/farmer")
@Slf4j
public class FarmerController {

    @GetMapping
    public String hello(@RequestHeader("email") String email, @AuthenticationPrincipal FarmerUserDetails farmerUserDetails) {
        if (farmerUserDetails != null) {
            System.out.println(farmerUserDetails.getFarmer().getId());
            System.out.println(farmerUserDetails.getFarmer().getEmail());
        }
        log.info("email: {}", email);
        return "hello farmer service";
    }

}
