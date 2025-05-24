package com.marpe.cht.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.marpe.cht.entities.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {


}
