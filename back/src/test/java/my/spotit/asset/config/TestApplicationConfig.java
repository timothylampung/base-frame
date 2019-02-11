package my.spotit.asset.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@Configuration
@EnableWebSecurity
@EnableScheduling
@EnableCaching
@ComponentScan(basePackages = {
        // internals
        "my.spotit.asset.core",
        "my.spotit.asset.helper",
        "my.spotit.asset.config",
        "my.spotit.asset.identity",
        "my.spotit.asset.security",
        "my.spotit.asset.system",
        "my.spotit.asset.common",
        "my.spotit.asset.workflow",

        // modules todo: add new module
        //report vo
})
@EntityScan(basePackages = {
        "my.spotit.asset"
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