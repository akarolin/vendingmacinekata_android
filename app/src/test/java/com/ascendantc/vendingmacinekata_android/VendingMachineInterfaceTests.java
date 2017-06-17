package com.ascendantc.vendingmacinekata_android;


import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by andykarolin on 6/17/17.
 *
 */

public class VendingMachineInterfaceTests {

    private VendingMachineInterface vendingMachineInterface = new VendingMachineInterface();

    @Test
    public void testDisplay() {

        assertEquals(vendingMachineInterface.getDisplayText(), VendingMachineInterface.INSERT_COIN);
        vendingMachineInterface.setDisplayText(5);
        assertEquals(vendingMachineInterface.getDisplayText(), "$0.05");
        vendingMachineInterface.setDisplayText(0);
        assertEquals(vendingMachineInterface.getDisplayText(), VendingMachineInterface.INSERT_COIN);
    }

    @Test
    public void testInsertCoins() {
        assertEquals(vendingMachineInterface.getDisplayText(), VendingMachineInterface.INSERT_COIN);
        vendingMachineInterface.insertCoin(Coin.Nickel);
        assertEquals(vendingMachineInterface.getDisplayText(), "$0.05");
        vendingMachineInterface.insertCoin(Coin.Dime);
        assertEquals(vendingMachineInterface.getDisplayText(), "$0.15");
        vendingMachineInterface.insertCoin(Coin.Quarter);
        assertEquals(vendingMachineInterface.getDisplayText(), "$0.40");
        vendingMachineInterface.insertCoin(Coin.Penny);
        assertEquals(vendingMachineInterface.getDisplayText(), "$0.40");
    }
}


