package com.advantage.accountsoap.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.inject.Inject;
@Configuration
@ConditionalOnProperty(
        value="dynamic.port.bean",
        havingValue = "true",
        matchIfMissing = false)
public class ServletConfig {

    @Inject
    Environment env;

    @Bean
    public WebServerFactoryCustomizer<ConfigurableServletWebServerFactory> webServerFactoryCustomizer() {
        return (container -> {
            container.setPort(Integer.parseInt(env.getProperty("account.soapservice.url.port")));
        });
    }
}
