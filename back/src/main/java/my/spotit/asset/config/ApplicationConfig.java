package my.spotit.asset.config;

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
        "my.spotit.asset.config",
        "my.spotit.asset.identity",
        "my.spotit.asset.security",
        "my.spotit.asset.system",
        "my.spotit.asset.common",
        "my.spotit.asset.asset",
        "my.spotit.asset.workorder",
        "my.spotit.asset.maintenance",
        "my.spotit.asset.inventory",

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
public class ApplicationConfig {

}
