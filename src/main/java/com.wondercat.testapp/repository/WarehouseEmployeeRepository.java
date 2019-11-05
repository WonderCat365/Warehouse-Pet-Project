package com.wondercat.testapp.repository;

import com.wondercat.testapp.entity.WarehouseEmployee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface WarehouseEmployeeRepository extends
        JpaRepository<WarehouseEmployee, Long> {

    List<WarehouseEmployee> findAll();

    Optional<WarehouseEmployee> findByName(String name);

    Optional<WarehouseEmployee> findById(Long id);

    void deleteById(Long id);

    Optional<WarehouseEmployee> findFirstByOrderByIdDesc();

}
