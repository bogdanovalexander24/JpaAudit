package com.myexample.audit.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.envers.AuditOverride;
import org.hibernate.envers.Audited;

import javax.persistence.*;

@Audited
@AuditOverride(forClass = Auditable.class)
@Entity
@Table
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Status extends Auditable {
    @Id
    @GeneratedValue
    @Column
    private Long id;

    @Column
    private Boolean verified;

    @Column(name = "updated_by")
    private String updateBy;
}
