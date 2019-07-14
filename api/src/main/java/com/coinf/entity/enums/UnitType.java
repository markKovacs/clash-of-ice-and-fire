package com.coinf.entity.enums;

import java.util.Arrays;
import java.util.List;

public enum UnitType {

    CHARACTER,
    MECH,
    WORKER;

    public static final List<UnitType> MECH_OR_CHAR = Arrays.asList(MECH, CHARACTER);

}
