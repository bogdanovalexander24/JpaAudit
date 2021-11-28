package com.myexample.audit.entity;

import lombok.*;
import org.hibernate.envers.AuditOverride;
import org.hibernate.envers.Audited;

import javax.persistence.*;

@Audited
@Entity
@Table
@Getter
@Setter
@ToString
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
