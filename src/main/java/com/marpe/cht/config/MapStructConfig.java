package com.marpe.cht.config;

import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.marpe.cht.entities.mappers.ClienteMapper;
import com.marpe.cht.entities.mappers.DadosBancariosMapper;
import com.marpe.cht.entities.mappers.DadosPessoaisMapper;

@Configuration
public class MapStructConfig {

	@Bean
    ClienteMapper clienteMapper() {
        return Mappers.getMapper(ClienteMapper.class);
    }
	
	@Bean
    DadosBancariosMapper dadosBancariosMapper() {
        return Mappers.getMapper(DadosBancariosMapper.class);
    }
	
	@Bean
    DadosPessoaisMapper dadosPessoaisMapper() {
        return Mappers.getMapper(DadosPessoaisMapper.class);
    }
	
	
}
