package com.coinf.entity.blueprint;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class EncounterCard {

    @Id
    @Column(unique = true,
            nullable = false)
    private Integer cardNumber;

    @OneToMany(mappedBy = "encounterCard",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER)
    private List<EncounterOption> options = new ArrayList<>();

    public EncounterCard(Integer cardNumber, EncounterOption... options) {
        this.cardNumber = cardNumber;
        this.options = Arrays.asList(options);

        // BIDIRECTIONAL SETTING
        for (EncounterOption option : options) {
            option.setEncounterCard(this);
        }
    }

    public void mergeOptionsLogic(List<EncounterOption> encounterOptions) {
        for (int i = 0; i < options.size(); i++) {
            EncounterOption option = options.get(i);
            EncounterOption logic = encounterOptions.get(i);

            option.setGainTypeOne(logic.getGainTypeOne());
            option.setGainAmountOne(logic.getGainAmountOne());
            option.setGainTypeTwo(logic.getGainTypeTwo());
            option.setGainAmountTwo(logic.getGainAmountTwo());
            option.setPaymentType(logic.getPaymentType());
            option.setPaymentAmount(logic.getPaymentAmount());
        }
    }
}
