package com.leverx.odata.entity.jpa;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.Collection;

import static javax.persistence.FetchType.EAGER;
import static org.hibernate.annotations.CascadeType.SAVE_UPDATE;

@Data
@NoArgsConstructor
@SuperBuilder
@Entity(name = "Users")
public class User {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @OneToMany(fetch = EAGER, mappedBy = "user", orphanRemoval = true)
    @Cascade(value = SAVE_UPDATE)
    private Collection<Pet> pets;

}
