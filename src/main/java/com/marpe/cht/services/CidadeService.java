package com.marpe.cht.services;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.marpe.cht.entities.dtos.CidadeIBGEResponse;
import com.marpe.cht.entities.dtos.CidadeIBGEResponse.Mesorregiao;
import com.marpe.cht.entities.dtos.CidadeIBGEResponse.Microrregiao;
import com.marpe.cht.entities.dtos.CidadeIBGEResponse.UF;
import com.marpe.cht.entities.dtos.CidadeResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;

@Service
public class CidadeService {

	private static final Logger log = LoggerFactory.getLogger(CidadeService.class);
    private final String URL_IBGE = "https://servicodados.ibge.gov.br/api/v1/localidades/municipios";
    
    private final RestTemplate restTemplate;

    public CidadeService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Cacheable("cidades")
    public List<CidadeResponse> getAllCidades() {
    	log.info("Receiving cities list from IBGE site.");
        ResponseEntity<CidadeIBGEResponse[]> response = restTemplate.getForEntity(
            URL_IBGE,
            CidadeIBGEResponse[].class
        );

        return Arrays.stream(response.getBody())
                .map(cidade -> new CidadeResponse(
                        cidade.getNome(),
                        extractSigla(cidade)
                ))
                .collect(Collectors.toList());
    }

    private String extractSigla(CidadeIBGEResponse cidade) {
        return Optional.ofNullable(cidade.getMicrorregiao())
            .map(Microrregiao::getMesorregiao)
            .map(Mesorregiao::getUf)
            .map(UF::getSigla)
            .orElse(null);
    }
    
    
}
