package com.wondercat.testapp.service;

import com.wondercat.testapp.entity.WarehouseEmployee;
import com.wondercat.testapp.repository.WarehouseEmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Component
public class WarehouseEmployeeService {

    @Autowired
    private WarehouseEmployeeRepository repository;

    public WarehouseEmployeeRepository getRepository() {
        return repository;
    }

    public void add(WarehouseEmployee employee){
        getRepository().save(employee);
    }

    public WarehouseEmployee getWarehouseEmployeeById(long id){
        return getRepository().getOne(id);
    }

    public Optional<WarehouseEmployee> getWarehouseEmployeeByName(String name){
        return getRepository().findByName(name);
    }

    public void deleteAll(){
        getRepository().deleteAll();
    }

    public void addAll(Collection<WarehouseEmployee> employees){
        getRepository().saveAll(employees);
    }

    public List<WarehouseEmployee> listAll(){
        return getRepository().findAll();
    }

    public void deleteWarehouseEmployee(long id){
        getRepository().deleteById(id);
    }

    public Optional<WarehouseEmployee> getLast(){
        return getRepository().findFirstByOrderByIdDesc();
    }

    public Optional<WarehouseEmployee> updateWarehouseEmployeeName(Long id, String name){
        WarehouseEmployee warehouseEmployeeToUpdate = getRepository().findById(id).isPresent()
                ? getRepository().findById(id).get()
                : new WarehouseEmployee("EMPTY");
        warehouseEmployeeToUpdate.setName(name);
        getRepository().save(warehouseEmployeeToUpdate);
        return Optional.ofNullable(warehouseEmployeeToUpdate);
    }
}
