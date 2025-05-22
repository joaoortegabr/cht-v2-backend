package com.marpe.cht.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.marpe.cht.entities.OSColab;

@Repository
public interface OSColabRepository extends JpaRepository<OSColab, Long> {

}