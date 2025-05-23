package com.marpe.cht.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.marpe.cht.entities.DadosPessoais;

@Repository
public interface DadosPessoaisRepository extends JpaRepository<DadosPessoais, Long> {

}
