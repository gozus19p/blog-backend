package it.manuelgozzi.blog.configuration;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @author Manuel Gozzi
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "app")
public class ManuelGozziBlogConfiguration {

    private String name;
}
