package com.example.hackaton1.Config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.*;

@Configuration
public class GlobalConfig {

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

}
