package com.coinf.entity.blueprint;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Data
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="section_type", discriminatorType = DiscriminatorType.STRING)
public abstract class PlayerMatSectionParent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "player_mat_layout_id")
    private PlayerMatLayout playerMatLayout;

    private Integer position;

    @OneToOne(mappedBy = "playerMatSection",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private BottomRowAction bottomRowAction;

}
