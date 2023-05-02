package com.github.javakira.whattoeat.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class ProductType implements Serializable {
    public final String title;
    public final Date spoil;

    public ProductType(String title, Date spoil) {
        this.title = title;
        this.spoil = spoil;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductType that = (ProductType) o;
        return Objects.equals(title, that.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title);
    }
}
