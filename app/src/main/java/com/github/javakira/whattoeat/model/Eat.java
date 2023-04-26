package com.github.javakira.whattoeat.model;

import java.util.Date;

public class Eat {
    public final String title;
    public final Date spoil;
    public final int count;

    public Eat(String title, Date spoil, int count) {
        this.title = title;
        this.spoil = spoil;
        this.count = count;
    }
}
