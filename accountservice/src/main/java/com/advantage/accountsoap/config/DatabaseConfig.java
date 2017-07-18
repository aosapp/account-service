package com.advantage.accountsoap.config;

import com.advantage.accountsoap.init.DataSourceInitByCsv;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DatabaseConfig extends com.advantage.common.config.DataSourceCommonConfiguration {

    public DatabaseConfig() {
        super("account.");
    }


    @Bean(initMethod = "init")
    public DataSourceInitByCsv initData() {
        return new DataSourceInitByCsv();
    }

}