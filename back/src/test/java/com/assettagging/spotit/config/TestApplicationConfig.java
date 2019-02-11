package com.assettagging.spotit.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import my.spotit.config.AuthorizationServerConfig;
import my.spotit.config.CacheConfig;
import my.spotit.config.CorsConfig;
import my.spotit.config.ResourceServerConfig;
import my.spotit.config.SecurityConfig;
import my.spotit.config.SwaggerConfig;
import my.spotit.config.WebConfig;
import my.spotit.config.WorkflowConfig;

@Configuration
@EnableWebSecurity
@EnableScheduling
@EnableCaching
@ComponentScan(basePackages = {
        // internals
        "com.assettagging.spotit.core",
        "com.assettagging.spotit.helper",
        "com.assettagging.spotit.config",
        "com.assettagging.spotit.identity",
        "com.assettagging.spotit.security",
        "com.assettagging.spotit.system",
        "com.assettagging.spotit.common",
        "com.assettagging.spotit.workflow",

        // modules todo: add new module
        //report vo
})
@EntityScan(basePackages = {
        "com.assettagging.spotit"
})

@Import({
        SecurityConfig.class,
        SwaggerConfig.class,
        AuthorizationServerConfig.class,
        ResourceServerConfig.class,
        CorsConfig.class,
        CacheConfig.class,
        WorkflowConfig.class,
        WebConfig.class
})
@PropertySource("classpath:application.properties")
@EnableAutoConfiguration
public class TestApplicationConfig {


}