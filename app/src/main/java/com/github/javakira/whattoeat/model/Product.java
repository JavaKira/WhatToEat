package com.github.javakira.whattoeat.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Product implements Serializable {
    public final String title;
    public final Date spoil;
    public final int count;

    public Product(String title, Date spoil, int count) {
        this.title = title;
        this.spoil = spoil;
        this.count = count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return count == product.count && Objects.equals(title, product.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, count);
    }
}
