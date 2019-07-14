package com.coinf.entity.instance;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@Entity
public class Star implements Serializable {

    private static final long serialVersionUID = 1L;

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
