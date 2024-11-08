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
@Table(name = "Client", schema = "solveithome", indexes = {
        @Index(name = "account_id", columnList = "account_id")
})
public class Client {
    @Id
    @Column(name = "client_id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "account_id")
    private Account account;

    @Size(max = 100)
    @Column(name = "client_name", length = 100)
    private String clientName;

    @Size(max = 20)
    @Column(name = "phone_number", length = 20)
    private String phoneNumber;

    @OneToMany(mappedBy = "client")
    private Set<com.fei.solveithomeapi.model.ClientServiceRequest> clientServiceRequests = new LinkedHashSet<>();

}