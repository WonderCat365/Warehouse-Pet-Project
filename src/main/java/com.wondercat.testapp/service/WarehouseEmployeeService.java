package com.wondercat.testapp.service;

import com.wondercat.testapp.entity.WarehouseEmployee;
import com.wondercat.testapp.repository.WarehouseEmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

@Component
public class WarehouseEmployeeService {

    @Autowired
    private WarehouseEmployeeRepository repository;

    public void add(WarehouseEmployee employee){
        repository.save(employee);
    }

    public WarehouseEmployee getWarehouseEmployeeById(long id){
        return repository.getOne(id);
    }

    public WarehouseEmployee getWarehouseEmployeeByName(String name){

        return repository.findByName(name);
    }

    public void deleteAll(){
        repository.deleteAll();
    }

    public void addAll(Collection<WarehouseEmployee> employees){
        repository.saveAll(employees);
    }

    public List<WarehouseEmployee> listAll(){
        return repository.findAll();
    }
}
