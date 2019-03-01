package my.spotit.asset.config;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Servlet;

import my.spotit.asset.report.JasperServlet;

@Configuration
public class ReportConfig {

    @Bean
    public ServletRegistrationBean servletRegistrationBean() {
        return new ServletRegistrationBean(jasperServlet(), "/servlet/report");
    }

    @Bean
    public Servlet jasperServlet() {
        return new JasperServlet();
    }
}
