package com.coinf.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Foo {

    private long id;

    private String name;

    public Foo(final long id, final String name) {
        super();
        this.id = id;
        this.name = name;
    }

}
