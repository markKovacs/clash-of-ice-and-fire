package com.coinf.entity.blueprint;

import com.coinf.entity.instance.Player;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class ObjectiveCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private Integer cardNumber;

    private String title;

    private String description;

    @ManyToMany(mappedBy = "objectives",
            cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Player> players;

}
