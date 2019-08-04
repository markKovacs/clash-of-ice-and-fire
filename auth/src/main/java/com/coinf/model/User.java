package com.coinf.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.*;

@Data
@NoArgsConstructor
@EqualsAndHashCode(of = "email")
@Entity
@Table(name = "users")
public class User {

    @Id
    private String email;

    @Column(nullable = false, length = 60)
    private String pwHash;

    private Boolean enabled;
    private String activation;
    private String firstName;
    private String surName;

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(
            name = "users_roles",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id")}
    )
    private Set<Role> roles = new HashSet<>();

    @Temporal(TemporalType.TIMESTAMP)
    private Date registeredAt = new Date();

    public User(String email, String firstName, String surName, String pwHash, Role... roles) {
        this.email = email;
        this.firstName = firstName;
        this.surName = surName;
        this.pwHash = pwHash;
        this.enabled = false;

        this.roles = new HashSet<>();
        this.roles.addAll(Arrays.asList(roles));
    }

    public void addRole(Role role) {
        this.roles.add(role);
        role.getUsers().add(this);
    }

    public void removeRole(Role role) {
        this.roles.remove(role);
        role.getUsers().remove(this);
    }

    public void removeAllRoles() {
        Iterator<Role> itr = this.roles.iterator();
        while (itr.hasNext()) {
            Role role = itr.next();
            role.getUsers().remove(this);
            itr.remove();
        }
    }

}
