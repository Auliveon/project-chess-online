package com.ChessOnline.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.h2.H2ConsoleAutoConfiguration;
import org.springframework.boot.autoconfigure.h2.H2ConsoleProperties;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.h2.server.web.WebServlet;

import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.Connection;

@Configuration
public class H2Config {

    @Bean
    public ServletRegistrationBean<WebServlet> h2Console() {
        H2ConsoleProperties properties = new H2ConsoleProperties();
        properties.setEnabled(true);
        properties.setPath("/h2");
        String path = properties.getPath();
        String urlMapping = path + (path.endsWith("/") ? "*" : "/*");
        ServletRegistrationBean<WebServlet> registration = new ServletRegistrationBean(new WebServlet(), urlMapping);

        if (properties.getSettings().isTrace()) {
            registration.addInitParameter("trace", "");
        }

        if (properties.getSettings().isWebAllowOthers()) {
            registration.addInitParameter("webAllowOthers", "");
        }

        if (properties.getSettings().getWebAdminPassword() != null) {
            registration.addInitParameter("webAdminPassword", properties.getSettings().getWebAdminPassword());
        }
        return registration;
    }

}
    


