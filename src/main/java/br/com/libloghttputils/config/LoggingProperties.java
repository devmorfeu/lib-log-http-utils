package br.com.libloghttputils.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Configuration
@ConfigurationProperties(prefix = "lib.log.http")
public class LoggingProperties {

    @Value("${message-request}")
    private String messageRequest;

    @Value("${message-response}")
    private String messageResponse;
}
