package com.jungdam;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Api("Health")
@RestController
public class AppController {

    @ApiOperation("Health Checking")
    @GetMapping("/health")
    public String getHealthCheck() {
        return "ok";
    }
}