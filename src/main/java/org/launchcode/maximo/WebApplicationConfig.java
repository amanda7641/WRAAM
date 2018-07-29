package org.launchcode.maximo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebApplicationConfig extends WebMvcConfigurerAdapter {

    //Allow autowiring
    @Bean
    public AuthenticationInterceptor authenticationInterceptor(){
        return new AuthenticationInterceptor();
    }

    //Register filter with Spring container
    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(authenticationInterceptor());
    }


}
