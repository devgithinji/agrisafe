package com.agrisafe.common.exception;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ErrorDetails {
    private final String path;
    private final String error;
    private final LocalDateTime timestamp;

    public ErrorDetails(String details, String error) {
        this.path = details;
        this.error = error;
        this.timestamp = LocalDateTime.now();
    }
}
