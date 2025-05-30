package com.marpe.cht.config;

import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.marpe.cht.entities.mappers.AtividadeMapper;
import com.marpe.cht.entities.mappers.ClienteMapper;
import com.marpe.cht.entities.mappers.ColaboradorMapper;
import com.marpe.cht.entities.mappers.DadosBancariosMapper;
import com.marpe.cht.entities.mappers.DadosPessoaisMapper;
import com.marpe.cht.entities.mappers.OrderMapper;
import com.marpe.cht.entities.mappers.RegionalMapper;

@Configuration
public class MapStructConfig {

	@Bean
    AtividadeMapper atividadeMapper() {
        return Mappers.getMapper(AtividadeMapper.class);
    }
	
	@Bean
    ClienteMapper clienteMapper() {
        return Mappers.getMapper(ClienteMapper.class);
    }
	
	@Bean
    ColaboradorMapper colaboradorMapper() {
        return Mappers.getMapper(ColaboradorMapper.class);
    }
	
	@Bean
    DadosBancariosMapper dadosBancariosMapper() {
        return Mappers.getMapper(DadosBancariosMapper.class);
    }
	
	@Bean
    DadosPessoaisMapper dadosPessoaisMapper() {
        return Mappers.getMapper(DadosPessoaisMapper.class);
    }
	
	@Bean
    OrderMapper orderMapper() {
        return Mappers.getMapper(OrderMapper.class);
    }
	
	@Bean
    RegionalMapper regionalMapper() {
        return Mappers.getMapper(RegionalMapper.class);
    }
	
}
