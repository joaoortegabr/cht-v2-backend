package com.marpe.cht.repositories;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.marpe.cht.entities.Atividade;

@Repository
public interface ReportRepository extends JpaRepository<Atividade, Long> {

	@Query(value = "SELECT colaborador_id, SUM(totalareceber) FROM tb_oscolab INNER JOIN tb_os ON tb_oscolab.os_id = tb_os.id WHERE (tb_os.data_inicio BETWEEN :startDate AND :endDate) GROUP BY colaborador_id ORDER BY SUM(totalareceber) DESC LIMIT 5", nativeQuery = true)
	List<Object[]> topFiveOscolabSomadoPorPeriodo(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

}
