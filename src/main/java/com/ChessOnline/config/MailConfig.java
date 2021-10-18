package com.ChessOnline.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.configurationprocessor.MetadataStore;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
@ConfigurationProperties(prefix = "mail")
public class MailConfig {

    private String host;

    private int port;

    private String username = "test.webapp.noreply";

    private String password = "4CjtwJBb123";

    private String protocol;

    private String auth;

    private String enable;

    private String trust;

    @Bean
    public JavaMailSender getMailSender() {
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setHost(host);
        javaMailSender.setPort(port);
        javaMailSender.setUsername(username);
        javaMailSender.setPassword(password);
        Properties properties = javaMailSender.getJavaMailProperties();
        properties.setProperty("mail.protocol", protocol);
        properties.setProperty("mail.smtp.auth", auth);
        properties.setProperty("mail.smtp.starttls.enable", enable);
        properties.setProperty("mail.smtp.ssl.trust", trust);
        return javaMailSender;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getUsername() {
        return username;
    }

    public String getTrust() {
        return trust;
    }

    public void setTrust(String trust) {
        this.trust = trust;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }

    public String getEnable() {
        return enable;
    }

    public void setEnable(String enable) {
        this.enable = enable;
    }

}
