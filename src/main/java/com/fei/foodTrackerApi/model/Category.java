package com.fei.foodTrackerApi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "Category", schema = "foodtracker", uniqueConstraints = {
        @UniqueConstraint(name = "name", columnNames = {"name"})
})
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id", nullable = false)
    private Integer id;

    @Size(max = 100)
    @NotNull
    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @OneToMany(mappedBy = "category")
    private Set<com.fei.foodTrackerApi.model.Menu> menus = new LinkedHashSet<>();

    @OneToMany(mappedBy = "category")
    private Set<com.fei.foodTrackerApi.model.Restaurant> restaurants = new LinkedHashSet<>();

}