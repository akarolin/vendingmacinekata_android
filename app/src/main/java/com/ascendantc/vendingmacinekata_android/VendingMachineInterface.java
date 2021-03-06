package com.ascendantc.vendingmacinekata_android;

import java.util.Locale;

/**
 * Created by andykarolin on 6/17/17.
 *
 */

class VendingMachineInterface {

    static final String INSERT_COIN = "INSERT COIN";
    static final String PRICE = "PRICE:";
    static final String THANK_YOU = "THANK YOU";
    static final String SOLD_OUT = "SOLD OUT";
    static final String EXACT_CHANGE = "EXACT CHANGE ONLY";

    private VendingMachineManager vendingMachineManager = new VendingMachineManager();

    private String message = "";

    private String displayText = INSERT_COIN;
    String getDisplayText() {
        return displayText;
    }

    void setDisplayText(int cents) {
        if (BuildConfig.DEBUG && cents < 0) throw new AssertionError("cents must be > 0");
        String zeroText = vendingMachineManager.getExactChangeRequired() ? EXACT_CHANGE : INSERT_COIN;
        displayText = cents > 0 ? formatCents(cents): zeroText;
    }

    private String formatCents(int cents) {
        return String.format(Locale.US,"$%d.%02d",cents/100,cents%100);
    }

    private void setDisplayText(String message) {
        displayText = message;
    }
        void insertCoin(Coin coin) {

        int inserted = vendingMachineManager.insertCoin(coin);
        setDisplayText(inserted);
    }

    void buyChips() {
        buyProduct(VendingMachineManager.CHIPS);
    }

    void buyCola() {
        buyProduct(VendingMachineManager.COLA);
    }

    void buyCandy() {
        buyProduct(VendingMachineManager.CANDY);
    }

    private void buyProduct(String productName) {
        if (vendingMachineManager.buyProduct(productName))
            message = THANK_YOU;
        else {
            if (vendingMachineManager.isSoldOut()) {
                message = SOLD_OUT;
            } else {
                int price = vendingMachineManager.getProductPrice(productName);
                message = String.format(Locale.US, "%s %s", PRICE, formatCents(price));
            }
        }
    }

    String checkDisplay() {
        if (message.length() > 0) {
            setDisplayText(message);
            message = "";
        } else {
            setDisplayText(vendingMachineManager.getCentsInserted());
        }
        return displayText;
    }

    String displayChange() {return formatCents(vendingMachineManager.getCentsReturned());}

    int takeChange() {
        return vendingMachineManager.removeChange();
    }

    void returnCoins() {
        vendingMachineManager.returnCoins();
    }

    void setSoldOut(boolean soldOut) {
        vendingMachineManager.setSoldOut(soldOut);
    }

    void setExactChange(boolean exactChange) {
        vendingMachineManager.setExactChange(exactChange);
    }
}
