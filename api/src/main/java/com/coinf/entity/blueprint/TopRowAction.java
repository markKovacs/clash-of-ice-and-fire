package com.coinf.entity.blueprint;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@DiscriminatorValue("REGULAR")
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Data
@Entity
public class TopRowAction extends TopRowActionParent {

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private PlayerMatSection playerMatSection;

}
