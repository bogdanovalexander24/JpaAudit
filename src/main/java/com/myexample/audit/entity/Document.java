package com.myexample.audit.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table
@Getter
@Setter
@ToString
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
