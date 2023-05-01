package com.github.javakira.whattoeat.model.containers;

import com.github.javakira.whattoeat.model.ProductType;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ProductTypes implements Serializable {
    private final List<ProductType> list = new ArrayList<>();

    public List<ProductType> list() {
        return list;
    }
}
