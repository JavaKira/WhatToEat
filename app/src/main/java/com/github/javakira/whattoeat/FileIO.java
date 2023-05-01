package com.github.javakira.whattoeat;

import android.content.Context;

import com.github.javakira.whattoeat.model.Product;
import com.github.javakira.whattoeat.model.ProductType;
import com.github.javakira.whattoeat.model.containers.ProductTypes;
import com.github.javakira.whattoeat.model.containers.Products;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;

public class FileIO {
    private static final String productsName = "products";
    private static final String productTypeName = "productTypes";

    private static final List<Runnable> productStoreListener = new LinkedList<>();

    public static void addProductStoreListener(Runnable runnable) {
        productStoreListener.add(runnable);
    }

    public static void removeProductStoreListener(Runnable runnable) {
        productStoreListener.remove(runnable);
    }

    private static void invokeProductStoreListeners() {
        productStoreListener.forEach(Runnable::run);
    }

    private static void serialize(Object o, String filename, Context context) {
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(context.openFileOutput(filename, 0));
            objectOutputStream.writeObject(o);
            objectOutputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static <T> T deserialize(String filename, Context context) {
        T object = null;
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(context.openFileInput(filename));
            object = (T) objectInputStream.readObject();
            objectInputStream.close();
        } catch (Exception ignored) {

        }

        return object;
    }

    public static ProductTypes productTypes(Context context) {
        ProductTypes productTypes = deserialize(productTypeName, context);
        if (productTypes == null)
            productTypes = new ProductTypes();

        return productTypes;
    }

    public static Products products(Context context) {
        Products products = deserialize(productsName, context);
        if (products == null)
            products = new Products();

        return products;
    }

    public static void changeProducts(Context context, Consumer<Products> consumer) {
        Products products = products(context);
        consumer.accept(products);
        store(products, context);
    }

    public static void store(ProductTypes productTypes, Context context) {
        serialize(productTypes, productTypeName, context);
    }

    public static void store(Products products, Context context) {
        serialize(products, productsName, context);
        invokeProductStoreListeners();
    }
}