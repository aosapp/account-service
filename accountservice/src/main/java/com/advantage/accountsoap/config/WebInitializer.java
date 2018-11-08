package com.advantage.accountsoap.config;

import com.advantage.accountsoap.AccountserviceApplication;
import com.advantage.common.Url_resources;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;

public class WebInitializer extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(AccountserviceApplication.class);
    }
}
