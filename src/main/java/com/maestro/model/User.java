package com.maestro.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(updatable = false, nullable = false)
    private UUID id;

    @Column(columnDefinition = "text")
    private String username;

    @Column(columnDefinition = "text")
    @JsonIgnore
    private String password;

    @Column(columnDefinition = "text")
    private String fullName;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_roles",
            joinColumns = { @JoinColumn(name = "user_id", referencedColumnName="id"), },
            inverseJoinColumns = { @JoinColumn(name = "role_id", referencedColumnName="id"), }
    )
    private Set<Role> roles;

}