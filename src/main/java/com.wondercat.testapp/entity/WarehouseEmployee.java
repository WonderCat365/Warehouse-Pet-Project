package com.wondercat.testapp.entity;

import org.springframework.context.annotation.Bean;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity

@Table(name = "WarehouseEmployee")
public class WarehouseEmployee {

    @Id
    @Column(name = "ID_WarehouseEmployee")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "Name_WarehouseEmployee")
    private String name;

    @OneToMany(mappedBy = "warehouseEmployee", cascade = CascadeType.ALL)
    private List<Orders> orders = new ArrayList<Orders>();

    public WarehouseEmployee(String name) {
        this.name = name;
    }


    public WarehouseEmployee() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "WarehouseEmployee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
