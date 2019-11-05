package com.wondercat.testapp.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(
        name = "WarehouseEmployee",
        uniqueConstraints = @UniqueConstraint(
                columnNames = "Name_WarehouseEmployee")
)
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Builder(toBuilder = true)
public class WarehouseEmployee {

    @Id
    @Column(name = "ID_WarehouseEmployee")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(
            name = "Name_WarehouseEmployee",
            unique = true)
    private String name;

    @Column(name = "Orders_WarehouseEmployee")
    @OneToMany(
            fetch = FetchType.EAGER,
            mappedBy = "warehouseEmployee",
            cascade = CascadeType.ALL)
    private List<Orders> orders;
}
