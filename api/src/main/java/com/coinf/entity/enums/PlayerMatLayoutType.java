package com.coinf.entity.enums;

public enum PlayerMatLayoutType {

    AGRICULTURAL(7, 2, 4, 7),
    PATRIOTIC(4, 2, 4, 6),
    MECHANICAL(6, 2, 3, 6),
    ENGINEERING(2, 2, 2, 5),
    INDUSTRIAL(1, 2, 2, 4),
    MILITANT(3, 2, 3, 4),
    INNOVATIVE(5, 2, 3, 5);

    public final int order;
    public final int objectives;
    public final int popularity;
    public final int coins;

    PlayerMatLayoutType(int order, int objectives, int popularity, int coins) {
        this.order = order;
        this.objectives = objectives;
        this.popularity = popularity;
        this.coins = coins;
    }

}
