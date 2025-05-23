package com.marpe.cht.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.marpe.cht.entities.DadosBancarios;

@Repository
public interface DadosBancariosRepository extends JpaRepository<DadosBancarios, Long> {

}
