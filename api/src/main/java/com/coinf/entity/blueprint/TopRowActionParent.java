package com.coinf.entity.blueprint;

import com.coinf.entity.enums.TopRowActionType;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name="action_type", discriminatorType = DiscriminatorType.STRING)
@Entity
public abstract class TopRowActionParent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private TopRowActionType topRowActionType;

}
