package com.myexample.audit.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Document {
    @Id
    @GeneratedValue
    @Column
    private Long id;

    @Column
    private String type;

    @OneToOne(cascade = CascadeType.ALL)
    private Status status;
}
