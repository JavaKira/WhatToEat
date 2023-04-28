package com.github.javakira.whattoeat.model;

import java.util.Date;
import java.util.Objects;

public class Eat {
    public final String title;
    public final Date spoil;
    public final int count;

    public Eat(String title, Date spoil, int count) {
        this.title = title;
        this.spoil = spoil;
        this.count = count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Eat eat = (Eat) o;
        return count == eat.count && Objects.equals(title, eat.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, count);
    }
}
