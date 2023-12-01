package com.agrisafe.famerservice.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/farmer")
@Slf4j
public class FarmerController {

    @GetMapping
    public String hello(@RequestHeader("UserId") String userId) {
        log.info("userid: {}", userId);
        return "hello farmer service";
    }

}
