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
    private PaymentType paymentType;
    private int paymentAmount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private GainType gainTypeOne;
    private int gainAmountOne;

    @Enumerated(EnumType.STRING)
    private GainType gainTypeTwo;
    private int gainAmountTwo;

    public EncounterOption(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public EncounterOption(PaymentType paymentType, int paymentAmount,
                           GainType gainTypeOne, int gainAmountOne,
                           GainType gainTypeTwo, int gainAmountTwo) {
        this.paymentType = paymentType;
        this.paymentAmount = paymentAmount;
        this.gainTypeOne = gainTypeOne;
        this.gainAmountOne = gainAmountOne;
        this.gainTypeTwo = gainTypeTwo;
        this.gainAmountTwo = gainAmountTwo;
    }

}
