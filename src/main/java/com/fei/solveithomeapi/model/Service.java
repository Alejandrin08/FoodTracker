package com.fei.solveithomeapi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "Service", schema = "solveithome", indexes = {
        @Index(name = "idx_service_name", columnList = "service_name"),
        @Index(name = "professional_id", columnList = "professional_id"),
        @Index(name = "category_id", columnList = "category_id")
})
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "service_id", nullable = false)
    private Integer id;

    @Size(max = 255)
    @NotNull
    @Column(name = "service_name", nullable = false)
    private String serviceName;

    @Lob
    @Column(name = "description")
    private String description;

    @Column(name = "price", precision = 10, scale = 2)
    private BigDecimal price;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "professional_id")
    private Professional professional;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(mappedBy = "service")
    private Set<ClientServiceRequest> clientServiceRequests = new LinkedHashSet<>();

}