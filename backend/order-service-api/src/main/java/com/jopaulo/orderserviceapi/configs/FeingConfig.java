package com.jopaulo.orderserviceapi.configs;

import com.jopaulo.orderserviceapi.decoders.RetrieveMessageErrorDecoder;
import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeingConfig {

    @Bean
    public ErrorDecoder errorDecoder(){
        return new RetrieveMessageErrorDecoder();
    }
}
