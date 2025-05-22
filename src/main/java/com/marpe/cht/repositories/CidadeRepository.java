package com.marpe.cht.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.marpe.cht.entities.Cidade;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Long> {

}
