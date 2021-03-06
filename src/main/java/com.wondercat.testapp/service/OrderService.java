package com.wondercat.testapp.service;

import com.wondercat.testapp.entity.Orders;
import com.wondercat.testapp.repository.OrderRepository;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Component
@Getter
public class OrderService {

    private final OrderRepository repository;

    @Autowired
    public OrderService(OrderRepository repository) {
        this.repository = repository;
    }

    public void add(Orders orders){
        getRepository().save(orders);
    }

    public void addAll(Collection<Orders> collection){
        getRepository().saveAll(collection);
    }

    public List<Orders> listAll(){
        return getRepository().findAll();
    }

    public Optional<Orders> getOrderByWarehouseEmployeeId(long id){
        return getRepository().findByWarehouseEmployeeId(id);
    }

    public Optional<Orders> getOrderByWarehouseEmployeeName(String name){
        return getRepository().findByWarehouseEmployeeName(name);
    }

}
