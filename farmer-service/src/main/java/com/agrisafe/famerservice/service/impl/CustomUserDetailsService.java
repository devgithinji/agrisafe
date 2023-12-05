package com.agrisafe.famerservice.service.impl;

import com.agrisafe.common.exception.ResourceNotFoundException;
import com.agrisafe.common.model.auth.Farmer;
import com.agrisafe.famerservice.config.WebclientConfig;
import com.agrisafe.famerservice.model.FarmerUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final WebclientConfig webclientConfig;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Farmer farmer = webclientConfig.getFarmer(username).orElseThrow(() -> new ResourceNotFoundException("farmer", "email", username));

        return new FarmerUserDetails(farmer);
    }
}
