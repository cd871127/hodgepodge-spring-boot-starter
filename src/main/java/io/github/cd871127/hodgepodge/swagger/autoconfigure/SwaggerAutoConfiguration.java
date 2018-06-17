package io.github.cd871127.hodgepodge.swagger.autoconfigure;

import io.github.cd871127.hodgepodge.swagger.filter.SwaggerFilter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.servlet.Filter;

@Configuration
@ConditionalOnProperty(prefix = "hodgepodge.swagger", value = "enable", havingValue = "true", matchIfMissing = true)
@EnableSwagger2
public class SwaggerAutoConfiguration {
    @Bean
    @ConditionalOnMissingBean(SwaggerFilter.class)
    @SuppressWarnings("unchecked")
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(swaggerFilter());
        registration.addUrlPatterns("/v2/api-docs");
        registration.setName("swaggerFilter");
        return registration;
    }
    @Bean(name = "swaggerFilter")
    public Filter swaggerFilter() {
        return new SwaggerFilter();
    }
}
