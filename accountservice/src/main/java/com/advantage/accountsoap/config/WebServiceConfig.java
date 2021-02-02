package com.advantage.accountsoap.config;

import com.advantage.common.Constants;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@EnableWs
@Configuration

public class WebServiceConfig extends WsConfigurerAdapter {
    public static final String NAMESPACE_URI = "com.advantage.online.store.accountservice";

    @Bean
    public ServletRegistrationBean messageDispatcherServlet(ApplicationContext applicationContext) {
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(applicationContext);
        servlet.setTransformWsdlLocations(true);
        return new ServletRegistrationBean(servlet, "/ws/*");
    }

    @Bean(name = "accountservice")
    public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema serviceSchema) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("AccountServicePort");
        wsdl11Definition.setLocationUri("/ws");
        wsdl11Definition.setTargetNamespace(NAMESPACE_URI);
        wsdl11Definition.setSchema(serviceSchema);
        return wsdl11Definition;
    }

    @Bean
    public XsdSchema serviceSchema() {
        return new SimpleXsdSchema(new ClassPathResource("accountservice.xsd"));
    }

//    @Bean
//    public ServletRegistrationBean mvcDispatcherServlet(ApplicationContext context){
//        DispatcherServlet servlet = new DispatcherServlet();
//        servlet.setApplicationContext(context);
//        return new ServletRegistrationBean(servlet, "/accountrest");
//    }
}
