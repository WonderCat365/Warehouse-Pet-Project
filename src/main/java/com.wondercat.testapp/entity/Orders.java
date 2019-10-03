package com.wondercat.testapp.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Orders")
public class Orders {

    @Id
    @Column(name = "ID_Orders")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @JoinColumn(name = "ID_WarehouseEmployee", nullable = false)
    @ManyToOne(targetEntity = WarehouseEmployee.class)
    private WarehouseEmployee warehouseEmployee;

    @Column(name = "DateExecution_Orders")
    @Temporal(TemporalType.DATE)
    private Date dateExecution;

    @Column(name = "DateCreation_Orders")
    @Temporal(TemporalType.DATE)
    private Date dateCreation;

    public Orders(WarehouseEmployee warehouseEmployee, Date dateExecution, Date dateCreation) {
        this.warehouseEmployee = warehouseEmployee;
        this.dateExecution = dateExecution;
        this.dateCreation = dateCreation;
    }

    public Orders() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
        return "Orders{" +
                "id=" + id +
                ", warehouseEmployee=" + warehouseEmployee +
                ", dateExecution=" + dateExecution +
                ", dateCreation=" + dateCreation +
                '}';
    }
}
