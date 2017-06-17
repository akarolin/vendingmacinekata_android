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
        assertEquals(vendingMachineManager.getCentsReturned(),1);
    }

    @Test
    public void ProductPriceTest() {
        int price = vendingMachineManager.getProductPrice(VendingMachineManager.CHIPS);
        assertEquals(price, 50);
        price = vendingMachineManager.getProductPrice(VendingMachineManager.COLA);
        assertEquals(price, 100);
        price = vendingMachineManager.getProductPrice(VendingMachineManager.CANDY);
        assertEquals(price, 65);
    }

    @Test
    public void BuyProductTest() {
        vendingMachineManager.insertCoin(Coin.Quarter);
        assertFalse(vendingMachineManager.buyProduct(VendingMachineManager.CHIPS));
        vendingMachineManager.insertCoin(Coin.Quarter);
        assertTrue(vendingMachineManager.buyProduct(VendingMachineManager.CHIPS));

        vendingMachineManager.insertCoin(Coin.Quarter);
        vendingMachineManager.insertCoin(Coin.Quarter);
        vendingMachineManager.insertCoin(Coin.Quarter);
        assertTrue(vendingMachineManager.buyProduct(VendingMachineManager.CANDY));
        assertEquals(vendingMachineManager.getCentsReturned(),10);
    }

    @Test
    public void CoinReturnTest() {
        vendingMachineManager.insertCoin(Coin.Quarter);
        assertEquals(vendingMachineManager.getCentsInserted(),25);
        assertEquals(vendingMachineManager.getCentsReturned(), 0);

        vendingMachineManager.returnCoins();
        assertEquals(vendingMachineManager.getCentsInserted(), 0);
        assertEquals(vendingMachineManager.getCentsReturned(),25);
    }

    @Test
    public void SoldOutTest() {
        vendingMachineManager.setSoldOut(true);
        vendingMachineManager.insertCoin(Coin.Quarter);
        vendingMachineManager.insertCoin(Coin.Quarter);
        assertFalse(vendingMachineManager.buyProduct(VendingMachineManager.CHIPS));

        vendingMachineManager.setSoldOut(false);
        assertTrue(vendingMachineManager.buyProduct(VendingMachineManager.CHIPS));
    }

    @Test
    public void ExactChangeTest() {
        vendingMachineManager.setExactChange(true);
        vendingMachineManager.insertCoin(Coin.Quarter);
        vendingMachineManager.insertCoin(Coin.Quarter);
        vendingMachineManager.insertCoin(Coin.Quarter);
        assertFalse(vendingMachineManager.buyProduct(VendingMachineManager.CANDY));

        vendingMachineManager.setExactChange(false);
        assertTrue(vendingMachineManager.buyProduct(VendingMachineManager.CANDY));
        assertEquals(vendingMachineManager.getCentsReturned(),10);
    }
}