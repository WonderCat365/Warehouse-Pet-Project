package com.wondercat.testapp.controller;

import com.wondercat.testapp.entity.WarehouseEmployee;
import com.wondercat.testapp.service.WarehouseEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class WarehouseEmployeeController {

    @Autowired
    private WarehouseEmployeeService warehouseEmployeeService;

    private WarehouseEmployeeService getWarehouseEmployeeService() {
        return warehouseEmployeeService;
    }

    public WarehouseEmployeeController(){}

    @RequestMapping("/WarehouseEmployees/{id}")
    public ResponseEntity<WarehouseEmployee> getWarehouseEmployeeTestById(@PathVariable long id){
        WarehouseEmployee warehouseEmployee = getWarehouseEmployeeService().
                getWarehouseEmployeeById(id);
        return new ResponseEntity<WarehouseEmployee>(warehouseEmployee, HttpStatus.OK);
    }

    @GetMapping("/WarehouseEmployees")
    public List<WarehouseEmployee> getAll(){
        return getWarehouseEmployeeService().listAll();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/WarehouseEmployee")
    public ResponseEntity<WarehouseEmployee> addWarehouseEmployee(
            @RequestBody WarehouseEmployee warehouseEmployee){

        getWarehouseEmployeeService().add(warehouseEmployee);
        return new ResponseEntity<>(warehouseEmployee, HttpStatus.CREATED);
    }

    @DeleteMapping("/WarehouseEmployee/{id}")
    public void deleteWarehouseEmployee(@PathVariable long id){
        getWarehouseEmployeeService().deleteWarehouseEmployee(id);
    }

}
