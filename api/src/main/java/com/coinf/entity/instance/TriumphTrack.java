package com.coinf.entity.instance;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

public class TriumphTrack {

    @Id
    private Long id;

    @OneToOne(fetch = FetchType.LAZY,
            optional = false)
    @MapsId
    private Game game;

    @OneToMany(mappedBy = "triumphTrack",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    @OrderColumn(name = "position")
    private List<TriumphTrackSection> sections = new ArrayList<>();

}
