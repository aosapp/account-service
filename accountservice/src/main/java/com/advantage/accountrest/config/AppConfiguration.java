package com.advantage.accountrest.config;

import com.advantage.common.Constants;
import org.springframework.context.annotation.*;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by kubany on 10/11/2015.
 */

@Configuration
@EnableAspectJAutoProxy
@ComponentScan(
        //basePackageClasses = {Constants.class},
        basePackages = {"com.advantage.accountrest"},
        excludeFilters = {
                @Filter(
                        type = FilterType.ANNOTATION,
                        value = {
                                RestController.class,
                                ControllerAdvice.class,
                                Configuration.class
                        }
                )
        }
)
@PropertySources(value = {
//        @PropertySource("classpath:/app.properties"),
        @PropertySource(Constants.FILE_PROPERTIES_VER_TXT)})
public class AppConfiguration {

}
