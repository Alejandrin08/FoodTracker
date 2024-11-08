package com.fei.solveithomeapi.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.util.Objects;

@Getter
@Setter
@Embeddable
public class ProfessionalCategoryId implements java.io.Serializable {
    private static final long serialVersionUID = -2771557675542978297L;
    @NotNull
    @Column(name = "professional_id", nullable = false)
    private Integer professionalId;

    @NotNull
    @Column(name = "category_id", nullable = false)
    private Integer categoryId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ProfessionalCategoryId entity = (ProfessionalCategoryId) o;
        return Objects.equals(this.professionalId, entity.professionalId) &&
                Objects.equals(this.categoryId, entity.categoryId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(professionalId, categoryId);
    }

}