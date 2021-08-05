package com.leverx.odata.entity.jpa;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

import static javax.persistence.FetchType.EAGER;
import static javax.persistence.GenerationType.IDENTITY;
import static javax.persistence.InheritanceType.SINGLE_TABLE;

@Data
@SuperBuilder
@NoArgsConstructor
@ToString
@Entity(name = "Pet")
@Inheritance(strategy = SINGLE_TABLE)
@DiscriminatorColumn(name = "Pet_Type")
public class Pet {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id")
    protected Long id;

    @Column(name = "name")
    protected String name;

    @Column(name = "age")
    protected Integer age;

    @ManyToOne(fetch = EAGER)
    @JoinColumn(name = "user_id", updatable = false)
    private User user;
}
