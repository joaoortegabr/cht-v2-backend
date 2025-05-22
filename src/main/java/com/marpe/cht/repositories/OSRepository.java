package com.marpe.cht.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.marpe.cht.entities.OS;

@Repository
public interface OSRepository extends JpaRepository<OS, Long> {


}
