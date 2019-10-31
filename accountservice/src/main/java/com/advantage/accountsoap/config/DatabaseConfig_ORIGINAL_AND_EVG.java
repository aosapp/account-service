/*package com.advantage.accountsoap.config;

import com.advantage.accountsoap.init.DataSourceInitByCsv;
import com.advantage.common.Constants;
import liquibase.integration.spring.SpringLiquibase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class DatabaseConfig_ORIGINAL_AND_EVG {

    //    @Value("${db.driver}")
    @Value("${" + Constants.ENV_HIBERNATE_DB_DRIVER_CLASSNAME_PARAMNAME + "}")
    private String DB_DRIVER_CLASSNAME_VALUE;

    //    @Value("${db.password}")
    @Value("${account.hibernate.db.password}")
    private String DB_PASSWORD;

    //    @Value("${db.url}")
//    private String DB_URL;
    @Value("${db.url.prefix}")
    private String DB_URL_PREFIX;
    @Value("${account.hibernate.db.url.host}")
    private String DB_URL_HOST;
    @Value("${account.hibernate.db.url.port}")
    private String DB_URL_PORT;
    @Value("${account.hibernate.db.name}")
    private String DB_URL_NAME;

    //    @Value("${db.username}")
    @Value("${account.hibernate.db.login}")
    private String DB_USERNAME;

//    @Value("${hibernate.dialect}")
//    private String HIBERNATE_DIALECT;
//
//    @Value("${hibernate.show_sql}")
//    private String HIBERNATE_SHOW_SQL;
//
//    @Value("${hibernate.hbm2ddl.auto}")
//    private String HIBERNATE_HBM2DDL_AUTO;

//    @Value("${entitymanager.packagesToScan}")
//    private String ENTITYMANAGER_PACKAGES_TO_SCAN;

    @Autowired
    private Environment env;

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(DB_DRIVER_CLASSNAME_VALUE);
        dataSource.setUrl(String.format("%s://%s:%s/%s", DB_URL_PREFIX, DB_URL_HOST, DB_URL_PORT, DB_URL_NAME));
        dataSource.setUsername(DB_USERNAME);
        dataSource.setPassword(DB_PASSWORD);
        return dataSource;
    }

    @Bean(initMethod = "init")
    public DataSourceInitByCsv init() {
        return new DataSourceInitByCsv();
    }

    @Bean
    public SpringLiquibase liquibase() {
        SpringLiquibase liquibase = new SpringLiquibase();

        liquibase.setDataSource(dataSource());
        liquibase.setChangeLog("classpath:db.changelog-master-test.xml");

        return liquibase;
    }
}
*/