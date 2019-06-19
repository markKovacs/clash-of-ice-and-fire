package com.coinf.entity.blueprint;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Data
@Entity
@DiscriminatorValue("FACTORY")
public class FactoryCard extends PlayerMatSectionParent {

    @OneToOne(mappedBy = "playerMatSection",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private FactoryTopRowAction topRowAction;

    @Column(nullable = false)
    private Integer cardNum;

}
