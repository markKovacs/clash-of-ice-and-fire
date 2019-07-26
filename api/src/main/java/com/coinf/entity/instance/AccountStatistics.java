package com.coinf.entity.instance;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

@Data
@NoArgsConstructor
@Entity
public class AccountStatistics {

    @Id
    private Long id;

    @OneToOne(fetch = FetchType.LAZY,
            optional = false)
    @MapsId
    private Account account;

    @ElementCollection
    @MapKeyColumn(name="faction")
    @Column(name="played")
    @CollectionTable(name="faction_played", joinColumns=@JoinColumn(name="faction_played_id"))
    private Map<String, Integer> playedByFaction = new HashMap<>();

    @ElementCollection
    @MapKeyColumn(name="faction")
    @Column(name="won")
    @CollectionTable(name="faction_won", joinColumns=@JoinColumn(name="faction_won_id"))
    private Map<String, Integer> wonByFaction = new HashMap<>();

    private int goldTotal;

}
