package com.github.javakira.whattoeat;

import android.content.Context;

import com.github.javakira.whattoeat.model.Eat;
import com.github.javakira.whattoeat.model.containers.ProductTypes;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

public class FileIO {
    private static final String propsName = "props.properties";
    private static final String productTypeName = "productTypes";

    private static final List<Runnable> propsStoreListeners = new LinkedList<>();

    public static void addPropsStoreListener(Runnable runnable) {
        propsStoreListeners.add(runnable);
    }

    public static void removePropsStoreListener(Runnable runnable) {
        propsStoreListeners.remove(runnable);
    }

    private static void invokePropsStoreListeners() {
        propsStoreListeners.forEach(Runnable::run);
    }

    private static Properties props(Context context) {
        Properties properties = new Properties();
        try {
            properties.load(context.openFileInput(propsName));
        } catch (IOException ignored) {

        }

        return properties;
    }

    private static void storeProps(Properties properties, Context context) {
        try {
            properties.store(context.openFileOutput(propsName, 0), "");
            invokePropsStoreListeners();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static ProductTypes productTypes(Context context) {
        ProductTypes productTypes;
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(context.openFileInput(productTypeName));
            productTypes = (ProductTypes) objectInputStream.readObject();
            objectInputStream.close();
        } catch (IOException e) {
            productTypes = new ProductTypes();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return productTypes;
    }

    public static void store(ProductTypes productTypes, Context context) {
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(context.openFileOutput(productTypeName, 0));
            objectOutputStream.writeObject(productTypes);
            objectOutputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Eat> getProducts(Context context) {
        Properties properties = props(context);
        int count = Integer.parseInt(properties.getProperty("productCount", "0"));
        List<Eat> products = new ArrayList<>(count);
        for (int i = 0; i < count; i++) {
            products.add(new Eat(properties.getProperty("product" + i + "Title"), new Date(), Integer.parseInt(properties.getProperty("product" + i + "Count"))));
        }

        return products;
    }

    public static void addProduct(Eat eat, Context context) {
        Properties properties = props(context);
        int index = Integer.parseInt(properties.getProperty("productCount", "0"));
        properties.setProperty("productCount", String.valueOf(index + 1));
        properties.setProperty("product" + index + "Title", eat.title);
        properties.setProperty("product" + index + "Count", String.valueOf(eat.count));
        storeProps(properties, context);
    }

    public static void remProduct(Eat eat, Context context) {
        Properties properties = props(context);
        List<Eat> products = getProducts(context);
        for (int i = 0; i < products.size(); i++) {
            properties.remove("product" + i + "Title");
            properties.remove("product" + i + "Count");
        }

        products.remove(eat);
        properties.setProperty("productCount", String.valueOf(products.size()));
        for (int i = 0; i < products.size(); i++) {
            properties.setProperty("product" + i + "Title", products.get(i).title);
            properties.setProperty("product" + i + "Count", String.valueOf(products.get(i).count));
        }

        storeProps(properties, context);
    }
}
