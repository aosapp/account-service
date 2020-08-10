package com.advantage.accountsoap.config;

import com.advantage.accountsoap.init.DataSourceInitByCsv;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class URL_ResourcesConfig extends com.advantage.common.Url_resources {

    public URL_ResourcesConfig() {
        super();
    }

    @Bean(initMethod = "init")
    public URL_ResourcesConfig init() {
        return new URL_ResourcesConfig();
    }

}