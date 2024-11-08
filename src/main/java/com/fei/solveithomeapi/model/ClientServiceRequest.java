package com.fei.solveithomeapi.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "Client_Service_Request", schema = "solveithome", indexes = {
        @Index(name = "client_id", columnList = "client_id"),
        @Index(name = "service_id", columnList = "service_id"),
        @Index(name = "idx_client_service_request_status", columnList = "status")
})
public class ClientServiceRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "request_id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "service_id")
    private Service service;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "request_date")
    private Instant requestDate;

    @Column(name = "status")
    private String status;

}