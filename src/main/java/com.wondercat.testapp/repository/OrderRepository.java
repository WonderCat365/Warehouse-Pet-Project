package com.wondercat.testapp.repository;

import com.wondercat.testapp.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Orders, Long> {

    public List<Orders> findAll();
}
