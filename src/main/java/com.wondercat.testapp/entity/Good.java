package com.wondercat.testapp.entity;

import javax.persistence.*;

@Entity
@Table(name = "Good")
public class Good {

    @Id
    @Column(name = "ID_Good")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "Name_Good")
    private String name;

    @Column(name = "Unit_Good")
    private String unit;

    @Column(name = "Quantity_Good")
    private int quantity;

    public Good(String name, String unit, int quantity) {
        this.name = name;
        this.unit = unit;
        this.quantity = quantity;
    }

    public Good() {
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

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Good{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", unit='" + unit + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
