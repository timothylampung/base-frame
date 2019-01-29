package com.assettagging.spotit.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebSecurity
@EnableWebMvc
@ComponentScan(basePackages = {
        // internals
        "com.assettagging.spotit.config",
        "com.assettagging.spotit.identity",
        "com.assettagging.spotit.security",
        "com.assettagging.spotit.system",
        "com.assettagging.spotit.common",

        // modules todo: add new module
        "com.assettagging.spotit.asset",
        "com.assettagging.spotit.workorder",
        "com.assettagging.spotit.maintenance",
        "com.assettagging.spotit.inventory",

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
public class ApplicationConfig {

}
