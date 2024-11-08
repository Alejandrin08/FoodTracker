package com.fei.solveithomeapi.model;

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
@Table(name = "Category", schema = "solveithome", indexes = {
        @Index(name = "idx_category_name", columnList = "name")
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

    @ManyToMany
    @JoinTable(name = "Professional_Category",
            joinColumns = @JoinColumn(name = "category_id"),
            inverseJoinColumns = @JoinColumn(name = "professional_id"))
    private Set<com.fei.solveithomeapi.model.Professional> professionals = new LinkedHashSet<>();

    @OneToMany(mappedBy = "category")
    private Set<com.fei.solveithomeapi.model.Service> services = new LinkedHashSet<>();

}