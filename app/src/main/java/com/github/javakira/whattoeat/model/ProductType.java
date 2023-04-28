package com.github.javakira.whattoeat.model;

import java.util.Date;

public class ProductType {
    public final String title;
    public final Date spoil;

    public ProductType(String title, Date spoil) {
        this.title = title;
        this.spoil = spoil;
    }
}
