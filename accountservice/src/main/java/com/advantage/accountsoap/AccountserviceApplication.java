package com.advantage.accountsoap;

import com.advantage.common.Constants;
import com.advantage.common.Url_resources;
import com.sun.xml.messaging.saaj.soap.AttachmentPartImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.*;

import javax.inject.Inject;

@SpringBootApplication(scanBasePackageClasses = {Url_resources.class})
@Configuration
@EnableAspectJAutoProxy(proxyTargetClass=true)
@ComponentScan
@PropertySources({
        @PropertySource(Constants.FILE_PROPERTIES_DEMO_APP),
        @PropertySource(Constants.FILE_PROPERTIES_INTERNAL),
        @PropertySource(Constants.FILE_PROPERTIES_EXTERNAL),
        @PropertySource(Constants.FILE_PROPERTIES_GLOBAL)})
public class AccountserviceApplication {


    public static void main(String[] args) {
        AttachmentPartImpl a;
        SpringApplication.run(AccountserviceApplication.class, args);
    }
}
