package com.myexample.audit.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Status {
    @Id
    @GeneratedValue
    @Column
    private Long id;

    @Column
    private Boolean verified;

    @Column(name = "updated_by")
    private String updateBy;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Status status = (Status) o;
        return id != null && Objects.equals(id, status.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
