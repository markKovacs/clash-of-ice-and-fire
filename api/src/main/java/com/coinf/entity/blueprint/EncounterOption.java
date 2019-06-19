package com.coinf.entity.blueprint;

import com.coinf.entity.enums.GainType;
import com.coinf.entity.enums.PaymentType;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
public class EncounterOption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false,
            fetch = FetchType.LAZY)
    @JoinColumn(name = "encounter_card_id")
    private EncounterCard encounterCard;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentType paymentType;
    @Column(nullable = false)
    private Integer paymentAmount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private GainType gainTypeOne;
    @Column(nullable = false)
    private Integer gainAmountOne;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private GainType gainTypeTwo;
    @Column(nullable = false)
    private Integer gainAmountTwo;

}
