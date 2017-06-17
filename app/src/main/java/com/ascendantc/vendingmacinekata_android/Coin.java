package com.ascendantc.vendingmacinekata_android;

/**
 * Created by andykarolin on 6/17/17.
 *
 */

enum Coin {
    Penny(1),
    Nickel(5),
    Dime(10),
    Quater(25);

    private final int value;
    Coin(int value) { this.value = value; }

    public int value() {return this.value; }
}
