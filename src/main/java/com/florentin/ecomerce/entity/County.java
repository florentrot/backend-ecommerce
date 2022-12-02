package com.florentin.ecomerce.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "county")
@Data
public class County {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @ManyToOne(cascade = CascadeType.ALL) //aici
    @JoinColumn(name = "country_id")
    private Country country;

}
