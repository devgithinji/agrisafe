package com.agrisafe.famerservice.config;

import com.agrisafe.common.model.auth.Farmer;
import com.agrisafe.famerservice.model.FarmerUserDetails;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AuthFilter extends OncePerRequestFilter {

    private final WebclientConfig webclientConfig;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String email = request.getHeader("email");

        Optional<Farmer> optionalFarmer = webclientConfig.getFarmer(email);


        if (optionalFarmer.isPresent()) {
            FarmerUserDetails farmerUserDetails = new FarmerUserDetails(optionalFarmer.get());
            Authentication authentication = new UsernamePasswordAuthenticationToken(farmerUserDetails, null, farmerUserDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }


        filterChain.doFilter(request, response);

    }
}
