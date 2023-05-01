package com.github.javakira.whattoeat.model.containers;

import com.github.javakira.whattoeat.model.Product;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Products implements Serializable {
    private final List<Product> list = new ArrayList<>();

    public List<Product> list() {
        return list;
    }
}
