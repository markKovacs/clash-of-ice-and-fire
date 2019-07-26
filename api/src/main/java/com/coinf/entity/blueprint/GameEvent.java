package com.coinf.entity.blueprint;

import com.coinf.entity.enums.GameEventType;
import com.coinf.entity.instance.Game;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class GameEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private GameEventType type;

    @ManyToMany(mappedBy = "eventsThisTurn")
    private List<Game> games = new ArrayList<>();

}
