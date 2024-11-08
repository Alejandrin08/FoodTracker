package com.fei.solveithomeapi.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter
@Setter
@Entity
@Table(name = "Professional_Category", schema = "solveithome", indexes = {
        @Index(name = "category_id", columnList = "category_id")
})
public class ProfessionalCategory {
    @EmbeddedId
    private ProfessionalCategoryId id;

    @MapsId("professionalId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "professional_id", nullable = false)
    private Professional professional;

    @MapsId("categoryId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

}