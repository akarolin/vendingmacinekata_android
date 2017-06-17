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
    public void testAcceptCoin() {
        assertFalse(vendingMachineManager.acceptsCoin(Coin.Penny));
        assertTrue(vendingMachineManager.acceptsCoin(Coin.Nickel));
        assertTrue(vendingMachineManager.acceptsCoin(Coin.Dime));
        assertTrue(vendingMachineManager.acceptsCoin(Coin.Quater));
    }

    @Test
    public void testInsertCoin() {
        int total = vendingMachineManager.insertCoin(Coin.Nickel);
        assertEquals(total, 5);
        total = vendingMachineManager.insertCoin(Coin.Dime);
        assertEquals(total, 15);
        total = vendingMachineManager.insertCoin(Coin.Quater);
        assertEquals(total, 40);
        total = vendingMachineManager.insertCoin(Coin.Penny);
        assertEquals(total, 40);
    }
}