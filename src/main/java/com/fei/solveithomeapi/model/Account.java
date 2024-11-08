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
@Table(name = "Account", schema = "solveithome", indexes = {
        @Index(name = "idx_account_email", columnList = "email")
})
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id", nullable = false)
    private Integer id;

    @Size(max = 255)
    @NotNull
    @Column(name = "email", nullable = false)
    private String email;

    @Size(max = 255)
    @NotNull
    @Column(name = "password", nullable = false)
    private String password;

    @NotNull
    @Lob
    @Column(name = "account_type", nullable = false)
    private String accountType;

    @OneToMany(mappedBy = "account")
    private Set<com.fei.solveithomeapi.model.Client> clients = new LinkedHashSet<>();

    @OneToMany(mappedBy = "account")
    private Set<com.fei.solveithomeapi.model.Professional> professionals = new LinkedHashSet<>();

}