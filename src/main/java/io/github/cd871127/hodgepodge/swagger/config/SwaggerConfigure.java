package io.github.cd871127.hodgepodge.swagger.config;

import io.github.cd871127.hodgepodge.swagger.filter.SwaggerFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.servlet.Filter;

@Configuration
//@ConditionalOnClass(SwaggerConfig.class)
@EnableSwagger2
public class SwaggerConfigure {

    @Bean
    @SuppressWarnings("unchecked")
    public FilterRegistrationBean someFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(swaggerFilter());
        registration.addUrlPatterns("/v2/api-docs");
//        registration.addInitParameter("paramName", "paramValue");
        registration.setName("swaggerFilter");
        return registration;
    }

    @Bean(name = "swaggerFilter")
    public Filter swaggerFilter() {
        return new SwaggerFilter();
    }


}
