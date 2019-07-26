package com.coinf.entity.blueprint;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class EncounterCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true,
            nullable = false)
    private Integer cardNumber;

    @OneToMany(mappedBy = "encounterCard",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER)
    private List<EncounterOption> options = new ArrayList<>();

}
