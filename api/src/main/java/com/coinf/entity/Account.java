package com.coinf.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * An in-game only registration, independent from the authorization server.
 * After the user authenticates himself through Authorization Server, he should
 * create a separate in-game account with a custom userName, then join/create game.
 */
@Data
@NoArgsConstructor
@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(unique = true, nullable = false)
    private String userName;

    @Column
    private Long goldTotal;

    @OneToOne(mappedBy = "account",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private Player player;

}
