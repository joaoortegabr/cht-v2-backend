package com.marpe.cht.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.marpe.cht.entities.Regional;

@Repository
public interface RegionalRepository extends JpaRepository<Regional, Long> {

}
