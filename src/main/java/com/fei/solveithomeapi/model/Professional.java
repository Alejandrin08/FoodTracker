package com.fei.solveithomeapi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "Professional", schema = "solveithome", indexes = {
        @Index(name = "account_id", columnList = "account_id"),
        @Index(name = "idx_professional_specialty", columnList = "specialty")
})
public class Professional {
    @Id
    @Column(name = "professional_id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "account_id")
    private Account account;

    @Size(max = 100)
    @Column(name = "professional_name", length = 100)
    private String professionalName;

    @Size(max = 20)
    @Column(name = "phone_number", length = 20)
    private String phoneNumber;

    @Size(max = 100)
    @Column(name = "specialty", length = 100)
    private String specialty;

    @ManyToMany(mappedBy = "professionals")
    private Set<Category> categories = new LinkedHashSet<>();

    @OneToMany(mappedBy = "professional")
    private Set<com.fei.solveithomeapi.model.Service> services = new LinkedHashSet<>();

}