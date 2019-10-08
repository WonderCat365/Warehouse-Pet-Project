package com.wondercat.testapp.repository;

import com.wondercat.testapp.entity.WarehouseEmployee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface WarehouseEmployeeRepository extends
        JpaRepository<WarehouseEmployee, Long> {

    List<WarehouseEmployee> findAll();

    WarehouseEmployee findByName(String name);

//    void deleteAll();

}
