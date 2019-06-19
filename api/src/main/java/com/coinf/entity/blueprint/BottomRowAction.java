package com.coinf.entity.blueprint;

import com.coinf.entity.enums.BottomRowActionType;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
public class BottomRowAction {

    @Id
    private Long id;

    @OneToOne(fetch = FetchType.LAZY,
            optional = false)
    @MapsId
    private PlayerMatSectionParent playerMatSection;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BottomRowActionType bottomRowActionType;

    private Integer fixedPayment;
    private Integer reducablePayment;
    private Integer gainableCoins;

}
