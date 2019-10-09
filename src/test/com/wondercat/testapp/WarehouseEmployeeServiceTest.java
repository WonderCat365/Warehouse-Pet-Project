package com.wondercat.testapp;

import com.wondercat.testapp.configuration.AppConfiguration;
import com.wondercat.testapp.entity.WarehouseEmployee;
import com.wondercat.testapp.service.WarehouseEmployeeService;
import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {AppConfiguration.class})
public class WarehouseEmployeeServiceTest {

    private final long WAREHOUSEEMPLOYEE_ID = 1;
    private final String WAREHOUSEEMPLOYEE_NAME = String.valueOf((int)(0 +
            Math.random()*100));

    @Autowired
    private WarehouseEmployeeService warehouseEmployeeService;

    @Test
    public void checkActualClassIsWired_Test(){

        assertThat(warehouseEmployeeService.getClass().getName()).isEqualTo(
                "com.wondercat.testapp.service.WarehouseEmployeeService");
    }

    @Test
    public void addWarehouseEmployee_Test(){
        WarehouseEmployee warehouseEmployee = new WarehouseEmployee(
                WAREHOUSEEMPLOYEE_NAME + WAREHOUSEEMPLOYEE_NAME);
        warehouseEmployeeService.add(warehouseEmployee);

        assertThat(warehouseEmployeeService.getWarehouseEmployeeByName(
                WAREHOUSEEMPLOYEE_NAME+WAREHOUSEEMPLOYEE_NAME).getName()).
                isEqualTo(WAREHOUSEEMPLOYEE_NAME+WAREHOUSEEMPLOYEE_NAME);
    }

    @Test
    @AfterAll
    public void deleteAll_Test(){

        warehouseEmployeeService.deleteAll();
        assertThat(warehouseEmployeeService.listAll()).isEmpty();
    }

    @Test
    public void addAll_Test(){

        warehouseEmployeeService.addAll(Arrays.asList(new WarehouseEmployee(
                WAREHOUSEEMPLOYEE_NAME + WAREHOUSEEMPLOYEE_ID), new
                WarehouseEmployee(WAREHOUSEEMPLOYEE_ID +
                WAREHOUSEEMPLOYEE_NAME)));

        assertThat(warehouseEmployeeService.getWarehouseEmployeeByName(
                WAREHOUSEEMPLOYEE_NAME + WAREHOUSEEMPLOYEE_ID).getName()).
                isEqualTo(WAREHOUSEEMPLOYEE_NAME + WAREHOUSEEMPLOYEE_ID);

        assertThat(warehouseEmployeeService.getWarehouseEmployeeByName(
                WAREHOUSEEMPLOYEE_ID + WAREHOUSEEMPLOYEE_NAME).getName()).
                isEqualTo(WAREHOUSEEMPLOYEE_ID + WAREHOUSEEMPLOYEE_NAME);
    }

    @Test
    public void getWarehouseEmployeeById_Test(){

        warehouseEmployeeService.add(new WarehouseEmployee(
                WAREHOUSEEMPLOYEE_NAME));

        WarehouseEmployee warehouseEmployee =
                warehouseEmployeeService.getWarehouseEmployeeById(
                        WAREHOUSEEMPLOYEE_ID);
        assertThat(warehouseEmployee.getId()).isEqualTo(
                WAREHOUSEEMPLOYEE_ID);
    }

    @Test
    public void getWarehouseEmployeeByName_Test(){

        warehouseEmployeeService.add(
                new WarehouseEmployee(WAREHOUSEEMPLOYEE_NAME));

        assertThat(warehouseEmployeeService.getWarehouseEmployeeByName(
                WAREHOUSEEMPLOYEE_NAME).getName()).isEqualTo(
                WAREHOUSEEMPLOYEE_NAME);
    }

    @Test
    public void getAllCortege(){

        assertThat(warehouseEmployeeService.listAll()).isNotEmpty();
        for (WarehouseEmployee warehouseEmployee : warehouseEmployeeService.
                listAll()){
            System.out.println(warehouseEmployee);
        }
    }
}
