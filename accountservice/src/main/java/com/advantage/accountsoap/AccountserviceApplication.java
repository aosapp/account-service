package com.advantage.accountsoap;

import com.advantage.common.Constants;

import com.sun.xml.messaging.saaj.soap.AttachmentPartImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.*;

@SpringBootApplication(scanBasePackages = {"com.advantage.accountsoap", "com.advantage.accountrest"},
        exclude = { SecurityAutoConfiguration.class})
@Configuration
@EnableAspectJAutoProxy(proxyTargetClass=true)
@PropertySources({
        @PropertySource(Constants.FILE_PROPERTIES_DEMO_APP),
        @PropertySource(value = Constants.FILE_PROPERTIES_INTERNAL, ignoreResourceNotFound = true),
        @PropertySource(value = Constants.FILE_PROPERTIES_EXTERNAL, ignoreResourceNotFound = true),
        @PropertySource(value = Constants.FILE_PROPERTIES_GLOBAL, ignoreResourceNotFound = true)})

public class AccountserviceApplication {


    public static void main(String[] args) {
        AttachmentPartImpl a;
        SpringApplication.run(AccountserviceApplication.class, args);
    }
}
