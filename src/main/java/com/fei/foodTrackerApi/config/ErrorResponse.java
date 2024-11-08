package com.fei.foodTrackerApi.config;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorResponse {

    private long timestamp;
    private int status;
    private String error;
    private String message;
    private String path;
}