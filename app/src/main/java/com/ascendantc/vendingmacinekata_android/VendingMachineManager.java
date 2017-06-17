package com.ascendantc.vendingmacinekata_android;

import java.util.HashMap;

/**
 * Created by andykarolin on 6/17/17.
 *
 */

class VendingMachineManager {

    static final String CHIPS = "Chips";
    static final String COLA = "Cola";
    static final String CANDY = "Candy";

    private int centsInserted = 0;
    int getCentsInserted() { return centsInserted; }

    private int centsReturned = 0;
    int getCentsReturned() {
        return centsReturned;
    }

    private HashMap<String, Integer> products;

    boolean acceptsCoin(Coin coin) {
        return coin != Coin.Penny;
    }

    VendingMachineManager () {
        products = new HashMap<>();
        products.put(CHIPS, 50);
        products.put(COLA, 100);
        products.put(CANDY, 65);
    }


    int insertCoin(Coin coin) {
        if (acceptsCoin(coin))
            centsInserted += coin.value();
        else
            centsReturned = coin.value();

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
            centsReturned += centsInserted - price;
            centsInserted = 0;
        }
        return canBuy;
    }
}
