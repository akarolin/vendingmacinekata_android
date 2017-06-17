package com.ascendantc.vendingmacinekata_android;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by andykarolin on 6/17/17.
 *
 */

public class VendingMachineManagerTests {

    private VendingMachineManager vendingMachineManager = new VendingMachineManager();

    @Test
    public void AcceptCoinTest() {
        assertFalse(vendingMachineManager.acceptsCoin(Coin.Penny));
        assertTrue(vendingMachineManager.acceptsCoin(Coin.Nickel));
        assertTrue(vendingMachineManager.acceptsCoin(Coin.Dime));
        assertTrue(vendingMachineManager.acceptsCoin(Coin.Quarter));
    }

    @Test
    public void InsertCoinTest() {
        int total = vendingMachineManager.insertCoin(Coin.Nickel);
        assertEquals(total, 5);
        total = vendingMachineManager.insertCoin(Coin.Dime);
        assertEquals(total, 15);
        total = vendingMachineManager.insertCoin(Coin.Quarter);
        assertEquals(total, 40);
        total = vendingMachineManager.insertCoin(Coin.Penny);
        assertEquals(total, 40);
    }

    @Test
    public void ProductPriceTest() {
        int price = vendingMachineManager.getProductPrice("chips");
        assertEquals(price, 50);
        price = vendingMachineManager.getProductPrice("cola");
        assertEquals(price, 100);
        price = vendingMachineManager.getProductPrice("candy");
        assertEquals(price, 65);
    }

    @Test
    public void BuyProductTest() {
        vendingMachineManager.insertCoin(Coin.Quarter);
        assertFalse(vendingMachineManager.buyProduct("chips"));
        vendingMachineManager.insertCoin(Coin.Quarter);
        assertTrue(vendingMachineManager.buyProduct("chips"));

        vendingMachineManager.insertCoin(Coin.Quarter);
        vendingMachineManager.insertCoin(Coin.Quarter);
        vendingMachineManager.insertCoin(Coin.Quarter);
        assertTrue(vendingMachineManager.buyProduct("candy"));
    }
}