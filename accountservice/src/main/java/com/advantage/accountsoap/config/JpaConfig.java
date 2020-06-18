package com.advantage.accountsoap.config;

import com.advantage.common.SystemParameters;
import com.advantage.common.Constants;
import org.apache.log4j.Logger;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.inject.Inject;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
public class JpaConfig {
    //    @Inject
    @Autowired
    DataSource dataSource;

//    @Value("${db.driver}")
//    private String DB_DRIVER;

//    @Value("${db.password}")
//    private String DB_PASSWORD;

    //    @Value("${hibernate.dialect}")
    @Value("${" + Constants.ENV_HIBERNATE_DIALECT_PARAMNAME + "}")
    private String HIBERNATE_DIALECT_VALUE;

    //    @Value("${hibernate.show_sql}")
    @Value("${" + Constants.ENV_HIBERNATE_SHOW_SQL_PARAMNAME + "}")
    private String HIBERNATE_SHOW_SQL_VALUE;

    //    @Value("${hibernate.hbm2ddl.auto}")
//    @Value("${" + Constants.ENV_HIBERNATE_HBM2DDL_AUTO_PARAMNAME + "}")
    @Value("${account.hibernate.db.hbm2ddlAuto}")
    private String HIBERNATE_HBM2DDL_AUTO_VALUE;

//    @Value("${entitymanager.packagesToScan}")
//    private String ENTITYMANAGER_PACKAGES_TO_SCAN_VALUE;

    private static Logger logger = Logger.getLogger(JpaConfig.class);

    @Bean(name = "entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
        emf.setDataSource(dataSource);
        emf.setPackagesToScan("com.advantage.accountsoap");
        emf.setPersistenceProvider(new HibernatePersistenceProvider());
        emf.setJpaProperties(jpaProperties());
        return emf;
    }

    private Properties jpaProperties() {
        Properties jpaProperties = new Properties();
        String hibernateHbm2ddlAuto = SystemParameters.getHibernateHbm2ddlAuto(HIBERNATE_HBM2DDL_AUTO_VALUE);
        jpaProperties.put(Constants.ENV_HIBERNATE_HBM2DDL_AUTO_PARAMNAME, hibernateHbm2ddlAuto);
        jpaProperties.put(Constants.ENV_HIBERNATE_DIALECT_PARAMNAME, HIBERNATE_DIALECT_VALUE);
        jpaProperties.put("logging.level.org.hibernate.tool.hbm2ddl", "DEBUG");
        jpaProperties.put("hibernate.show.sql", "true");
        if (logger.isTraceEnabled()) {
            StringBuilder sb = new StringBuilder("JPA properties put: ").append(System.lineSeparator());
            sb.append(Constants.ENV_HIBERNATE_HBM2DDL_AUTO_PARAMNAME).append("=").append(hibernateHbm2ddlAuto).append(System.lineSeparator());
            sb.append(Constants.ENV_HIBERNATE_DIALECT_PARAMNAME).append("=").append(HIBERNATE_DIALECT_VALUE).append(System.lineSeparator());
            logger.trace(sb.toString());
        }

        return jpaProperties;
    }

    @Bean(name = "transactionManager")
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager tm = new JpaTransactionManager();
        tm.setEntityManagerFactory(entityManagerFactory().getObject());

        return tm;
    }

    @Override
    public String toString() {
        return "JpaConfig{" +
                "dataSource=" + dataSource +
                ", HIBERNATE_DIALECT_VALUE='" + HIBERNATE_DIALECT_VALUE + '\'' +
                ", HIBERNATE_SHOW_SQL_VALUE='" + HIBERNATE_SHOW_SQL_VALUE + '\'' +
                ", HIBERNATE_HBM2DDL_AUTO_VALUE='" + HIBERNATE_HBM2DDL_AUTO_VALUE + '\'' +
                '}';
    }
}
