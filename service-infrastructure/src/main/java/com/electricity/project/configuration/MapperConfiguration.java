package com.electricity.project.configuration;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfiguration {
    @Bean
    public ObjectMapper getMapper(){
        return JsonMapper.builder()
                .configure(DeserializationFeature.UNWRAP_ROOT_VALUE, true)
                .addModule(new JavaTimeModule())
                .build();
    }
}
