package com.coinf.entity.instance;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
public class Star {

    @Id
    @ManyToOne(optional = false,
            fetch = FetchType.LAZY)
    @JoinColumn(name = "player_id")
    private Player player;

    @Id
    @ManyToOne(optional = false,
            fetch = FetchType.LAZY)
    @JoinColumn(name = "section_id")
    private TriumphTrackSection section;

}
