package com.wondercat.testapp.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Orders")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder(toBuilder = true)
public class Orders {

    @Id
    @Column(name = "ID_Orders")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ToString.Exclude
    @JoinColumn(name = "ID_WarehouseEmployee", nullable = false)
    @ManyToOne(targetEntity = WarehouseEmployee.class)
    private WarehouseEmployee warehouseEmployee;

    @Column(name = "DateExecution_Orders")
    @Temporal(TemporalType.DATE)
    private Date dateExecution;

    @Column(name = "DateCreation_Orders")
    @Temporal(TemporalType.DATE)
    private Date dateCreation;
}
