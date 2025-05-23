package com.marpe.cht.config;

import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.marpe.cht.entities.mappers.ClienteMapper;

@Configuration
public class MapStructConfig {

	@Bean
    ClienteMapper clienteMapper() {
        return Mappers.getMapper(ClienteMapper.class);
    }
	
}
