package com.myexample.audit.entity;

import lombok.*;

import javax.persistence.*;

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
