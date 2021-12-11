package com.jungdam.common.config;

import com.jungdam.common.properties.AuthProperties;
import com.jungdam.common.properties.CorsProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties({
    CorsProperties.class,
    AuthProperties.class
})
public class PropertiesConfig {

}