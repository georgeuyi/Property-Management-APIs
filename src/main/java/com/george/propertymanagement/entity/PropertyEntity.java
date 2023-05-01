package com.george.propertymanagement.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "PROPERTY_TABLE")
@Getter
@Setter
@NoArgsConstructor
public class PropertyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "Title", nullable = false)
    private String title;
    private String description;
    private double price;
    private String address;
    @ManyToOne(fetch = FetchType.LAZY) // will not fetch user data while fetching property.
    @JoinColumn(name = "USER_ID", nullable = false)
    UserEntity userEntity;

}
