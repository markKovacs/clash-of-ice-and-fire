package com.coinf.entity.instance;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * An in-game only registration, independent from the authorization server.
 * After the user authenticates himself through Authorization Server, he should
 * create a separate in-game account with a custom userName.
 */
@Data
@NoArgsConstructor
@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true,
            nullable = false)
    private String email;

    @Column(unique = true,
            nullable = false)
    private String userName;

    @OneToOne(mappedBy = "account",
            optional = false,
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private AccountStatistics statistics;

    @OneToOne(mappedBy = "account",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private Player player;

    public Account(String email, String userName, AccountStatistics statistics) {
        this.email = email;
        this.userName = userName;
        this.statistics = statistics;

        // BIDIRECTIONAL SETTING
        statistics.setAccount(this);
    }

}
