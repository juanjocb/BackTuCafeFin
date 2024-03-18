package com.backtucafe.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_category;
    private String name;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_business", referencedColumnName = "id_business")
    private Business id_business;


}
