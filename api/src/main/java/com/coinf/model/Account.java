package com.coinf.model;

import javax.persistence.*;

/**
 * Use to pair email accounts with user scores and other exclusively in-game info.
 * After the user authenticates himself through Authorization Server, he should
 * create a separate in-game account with a custom userName.
 */
@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String userName;

    @Column
    private Long goldTotal;

}
