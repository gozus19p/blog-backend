package it.manuelgozzi.blog.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author Manuel Gozzi
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "app")
public class ManuelGozziBlogConfiguration {

    private Auth auth;

    @Data
    public static class Auth {

        private BasicAuthorization basic;
    }

    @Data
    public static class BasicAuthorization {

        private String username;
        private String password;
    }
}
