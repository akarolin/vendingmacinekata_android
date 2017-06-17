package com.ascendantc.vendingmacinekata_android;

import java.util.Locale;

/**
 * Created by andykarolin on 6/17/17.
 *
 */

class VendingMachineInterface {

    static final String INSERT_COIN = "INSERT COIN";
    private VendingMachineManager vendingMachineManager = new VendingMachineManager();

    private String displayText = INSERT_COIN;

    String getDisplayText() {
        return displayText;
    }

    void setDisplayText(int cents) {
        if (BuildConfig.DEBUG && cents < 0) throw new AssertionError("cents must be > 0");
        displayText = cents > 0 ? String.format(Locale.US,"$%d.%02d",cents/100,cents%100) : INSERT_COIN;
    }

    void insertCoin(Coin coin) {

        int inserted = vendingMachineManager.insertCoin(coin);
        setDisplayText(inserted);
    }
}
