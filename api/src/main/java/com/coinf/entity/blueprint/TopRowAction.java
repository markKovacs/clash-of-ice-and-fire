package com.coinf.entity.blueprint;

import com.coinf.entity.enums.TopRowActionType;
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

    public TopRowAction(TopRowActionType topRowActionType) {
        super(topRowActionType);
    }

}
