package com.coinf.entity.instance;

import com.coinf.entity.enums.TriumphTrackSectionType;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

public class TriumphTrackSection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false,
            fetch = FetchType.LAZY)
    @JoinColumn(name = "triumph_track_id")
    private TriumphTrack triumphTrack;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private TriumphTrackSectionType type;

    @OneToMany(mappedBy = "section",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private List<Star> stars = new ArrayList<>();

    @Column(nullable = false)
    private Integer position;

}
