package com.ascendantc.vendingmacinekata_android;

import java.util.HashMap;

/**
 * Created by andykarolin on 6/17/17.
 *
 */

class VendingMachineManager {

    private int centsInserted = 0;

    private HashMap<String, Integer> products;

    boolean acceptsCoin(Coin coin) {
        return coin != Coin.Penny;
    }

    VendingMachineManager () {
        products = new HashMap<>();
        products.put("chips", 50);
        products.put("cola", 100);
        products.put("candy", 65);
    }

    int insertCoin(Coin coin) {
        if (acceptsCoin(coin))
            centsInserted += coin.value();
        return centsInserted;
    }


    int getProductPrice(String productName) {
        if (BuildConfig.DEBUG && ! products.containsKey(productName)) throw new AssertionError("Product Not Found");
        return products.get(productName);
    }

    boolean buyProduct(String productName) {
        if (BuildConfig.DEBUG && ! products.containsKey(productName)) throw new AssertionError("Product Not Found");
        int price = products.get(productName);
        boolean canBuy = price <= centsInserted;
        if (canBuy) {
            centsInserted = 0;
        }
        return canBuy;
    }
}
