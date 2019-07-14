package com.coinf.entity.blueprint;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Data
@Entity
@DiscriminatorValue("REGULAR")
public class PlayerMatSection extends PlayerMatSectionParent {

    @OneToOne(mappedBy = "playerMatSection",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private TopRowAction topRowAction;


}
