package com.ascendantc.vendingmacinekata_android;

/**
 * Created by andykarolin on 6/17/17.
 *
 */

class VendingMachineManager {

    private int centsInserted = 0;

    boolean acceptsCoin(Coin coin) {
        return coin != Coin.Penny;
    }

    int insertCoin(Coin coin) {
        if (acceptsCoin(coin))
            centsInserted += coin.value();
        return centsInserted;
    }
}
