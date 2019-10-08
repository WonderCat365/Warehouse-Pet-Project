package com.wondercat.testapp;

import com.wondercat.testapp.configuration.AppConfiguration;
import com.wondercat.testapp.entity.Orders;
import com.wondercat.testapp.entity.WarehouseEmployee;
import com.wondercat.testapp.service.OrderService;
import com.wondercat.testapp.service.WarehouseEmployeeService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Date;

public class App {

    public static void main(String[] args){

        ApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfiguration.class);

//        ClassPathXmlApplicationContext context =
//                new ClassPathXmlApplicationContext("Spring.xml");

        WarehouseEmployeeService warehouseEmployeeService = context.
                getBean(WarehouseEmployeeService.class);

        OrderService orderService = context.getBean(OrderService.class);

        try{

            WarehouseEmployee warehouseEmployee = new WarehouseEmployee("Pavel");
            warehouseEmployeeService.add(warehouseEmployee);

            orderService.add(new Orders(warehouseEmployee, new Date(),new Date()));
            orderService.add(new Orders(warehouseEmployee, new Date(),new Date()));
            orderService.add(new Orders(warehouseEmployee, new Date(),new Date()));
            orderService.add(new Orders(warehouseEmployee, new Date(),new Date()));
        }
        catch (Exception ex){
            System.err.println(ex.getMessage());
        }

        for(Orders orders : orderService.listAll()){
            System.out.println(orders);
        }
    }
}
