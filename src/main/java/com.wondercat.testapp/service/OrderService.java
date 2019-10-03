package com.wondercat.testapp.service;

import com.wondercat.testapp.entity.Orders;
import com.wondercat.testapp.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

@Component
public class OrderService {

    @Autowired
    private OrderRepository repository;

    public void add(Orders orders){
        repository.save(orders);
    }

    public void addAll(Collection<Orders> collection){
        repository.saveAll(collection);
    }

    public List<Orders> listAll(){
        return repository.findAll();
    }

}
