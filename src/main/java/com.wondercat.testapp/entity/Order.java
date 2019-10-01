package com.wondercat.testapp.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Order")
public class Order {

    @Id
    @Column(name = "ID_Order")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "ID_Customer_Order")
    private int idCustomer;

    @Column(name = "ID_Good_Order")
    private int idGood;

    @Column(name = "ID_WarehouseEmployee_Order")
    private int idWarehouseEmployee;

    @Column(name = "DateExecution_Order")
    @Temporal(TemporalType.DATE)
    private Date dateExecution;

    @Column(name = "DateCreation_Order")
    @Temporal(TemporalType.DATE)
    private Date dateCreation;

    public Order(int idCustomer, int idGood, int idWarehouseEmployee, Date dateExecution, Date dateCreation) {
        this.idCustomer = idCustomer;
        this.idGood = idGood;
        this.idWarehouseEmployee = idWarehouseEmployee;
        this.dateExecution = dateExecution;
        this.dateCreation = dateCreation;
    }

    public Order() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(int idCustomer) {
        this.idCustomer = idCustomer;
    }

    public int getIdGood() {
        return idGood;
    }

    public void setIdGood(int idGood) {
        this.idGood = idGood;
    }

    public int getIdWarehouseEmployee() {
        return idWarehouseEmployee;
    }

    public void setIdWarehouseEmployee(int idWarehouseEmployee) {
        this.idWarehouseEmployee = idWarehouseEmployee;
    }

    public Date getDateExecution() {
        return dateExecution;
    }

    public void setDateExecution(Date dateExecution) {
        this.dateExecution = dateExecution;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", idCustomer=" + idCustomer +
                ", idGood=" + idGood +
                ", idWarehouseEmployee=" + idWarehouseEmployee +
                ", dateExecution=" + dateExecution +
                ", dateCreation=" + dateCreation +
                '}';
    }
}
