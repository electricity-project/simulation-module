package com.electricity.project.simulationmodule.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfiguration {

    @Bean
    public ObjectMapper getMapper() {
        return JsonMapper.builder()
                //TODO FIX deserialization for WEATHER_API
//                .configure(DeserializationFeature.UNWRAP_ROOT_VALUE, true)
                .addModule(new JavaTimeModule())
                .addModule(new Jdk8Module())
                .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                .build();
    }
}
