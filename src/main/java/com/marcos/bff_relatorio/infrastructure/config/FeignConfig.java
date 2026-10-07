package com.marcos.bff_relatorio.infrastructure.config;

import feign.codec.Encoder;
import feign.form.spring.SpringFormEncoder;
import org.springframework.context.annotation.Bean;

public class FeignConfig {

    @Bean
    public Encoder multipartFormEncoder() {
        return new SpringFormEncoder();
    }
}
