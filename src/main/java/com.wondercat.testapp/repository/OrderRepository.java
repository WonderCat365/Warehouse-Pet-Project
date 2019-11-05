package com.wondercat.testapp.repository;

import com.wondercat.testapp.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Orders, Long> {

    List<Orders> findAll();

    Optional<Orders> findByWarehouseEmployeeId(long warehouseEmployee_id);

    Optional<Orders> findByWarehouseEmployeeName(String warehouseEmployee_name);
}
