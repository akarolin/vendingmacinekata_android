package com.ascendantc.vendingmacinekata_android;


import junit.framework.Assert;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by andykarolin on 6/17/17.
 *
 */

public class VendingMachineInterfaceTests {

    private VendingMachineInterface vendingMachineInterface = new VendingMachineInterface();

    @Test
    public void DisplayTest() {

        assertEquals(vendingMachineInterface.getDisplayText(), VendingMachineInterface.INSERT_COIN);
        vendingMachineInterface.setDisplayText(5);
        assertEquals(vendingMachineInterface.getDisplayText(), "$0.05");
        vendingMachineInterface.setDisplayText(0);
        assertEquals(vendingMachineInterface.getDisplayText(), VendingMachineInterface.INSERT_COIN);
    }

    @Test
    public void InsertCoinsTest() {
        assertEquals(vendingMachineInterface.getDisplayText(), VendingMachineInterface.INSERT_COIN);
        vendingMachineInterface.insertCoin(Coin.Nickel);
        assertEquals(vendingMachineInterface.getDisplayText(), "$0.05");
        vendingMachineInterface.insertCoin(Coin.Dime);
        assertEquals(vendingMachineInterface.getDisplayText(), "$0.15");
        vendingMachineInterface.insertCoin(Coin.Quarter);
        assertEquals(vendingMachineInterface.getDisplayText(), "$0.40");
        vendingMachineInterface.insertCoin(Coin.Penny);
        assertEquals(vendingMachineInterface.getDisplayText(), "$0.40");
        Assert.assertEquals(vendingMachineInterface.takeChange(),1);
    }

    @Test
    public void BuyProductTest() {

        Assert.assertEquals(vendingMachineInterface.checkDisplay(),VendingMachineInterface.INSERT_COIN);

        vendingMachineInterface.insertCoin(Coin.Quarter);
        Assert.assertEquals(vendingMachineInterface.checkDisplay(),"$0.25");

        vendingMachineInterface.buyChips();
        Assert.assertEquals(vendingMachineInterface.checkDisplay(),String.format("%s $0.50",VendingMachineInterface.PRICE));
        Assert.assertEquals(vendingMachineInterface.checkDisplay(),"$0.25");

        vendingMachineInterface.insertCoin(Coin.Quarter);
        Assert.assertEquals(vendingMachineInterface.checkDisplay(),"$0.50");

        vendingMachineInterface.buyChips();
        Assert.assertEquals(vendingMachineInterface.checkDisplay(),VendingMachineInterface.THANK_YOU);
        Assert.assertEquals(vendingMachineInterface.checkDisplay(),VendingMachineInterface.INSERT_COIN);

        vendingMachineInterface.insertCoin(Coin.Quarter);
        vendingMachineInterface.insertCoin(Coin.Quarter);
        vendingMachineInterface.insertCoin(Coin.Quarter);
        vendingMachineInterface.insertCoin(Coin.Quarter);
        vendingMachineInterface.buyCola();
        Assert.assertEquals(vendingMachineInterface.checkDisplay(),VendingMachineInterface.THANK_YOU);
        Assert.assertEquals(vendingMachineInterface.checkDisplay(),VendingMachineInterface.INSERT_COIN);

        vendingMachineInterface.insertCoin(Coin.Quarter);
        vendingMachineInterface.insertCoin(Coin.Quarter);
        vendingMachineInterface.insertCoin(Coin.Quarter);
        vendingMachineInterface.buyCandy();
        Assert.assertEquals(vendingMachineInterface.checkDisplay(),VendingMachineInterface.THANK_YOU);
        Assert.assertEquals(vendingMachineInterface.checkDisplay(),VendingMachineInterface.INSERT_COIN);
        Assert.assertEquals(vendingMachineInterface.takeChange(),10);
    }

    @Test
    public void CoinReturnTest() {
        vendingMachineInterface.insertCoin(Coin.Quarter);
        Assert.assertEquals(vendingMachineInterface.checkDisplay(), "$0.25");

        vendingMachineInterface.returnCoins();
        Assert.assertEquals(vendingMachineInterface.checkDisplay(), VendingMachineInterface.INSERT_COIN);
        Assert.assertEquals(vendingMachineInterface.displayChange(), "$0.25");
    }

    @Test
    public void SoldOutTest() {

        vendingMachineInterface.setSoldOut(true);
        vendingMachineInterface.insertCoin(Coin.Quarter);
        vendingMachineInterface.insertCoin(Coin.Quarter);
        vendingMachineInterface.buyChips();
        Assert.assertEquals(vendingMachineInterface.checkDisplay(), VendingMachineInterface.SOLD_OUT);
        Assert.assertEquals(vendingMachineInterface.checkDisplay(), "$0.50");

        vendingMachineInterface.setSoldOut(false);
        vendingMachineInterface.buyChips();
        Assert.assertEquals(vendingMachineInterface.checkDisplay(),VendingMachineInterface.THANK_YOU);
        Assert.assertEquals(vendingMachineInterface.checkDisplay(),VendingMachineInterface.INSERT_COIN);
    }

    @Test
    public void ExactChangeTest () {
        vendingMachineInterface.setExactChange(true);
        Assert.assertEquals(vendingMachineInterface.checkDisplay(),VendingMachineInterface.EXACT_CHANGE);

        vendingMachineInterface.insertCoin(Coin.Quarter);
        vendingMachineInterface.insertCoin(Coin.Quarter);
        vendingMachineInterface.insertCoin(Coin.Quarter);
        vendingMachineInterface.buyCandy();
        Assert.assertEquals(vendingMachineInterface.checkDisplay(),String.format("%s $0.65",VendingMachineInterface.PRICE));

        vendingMachineInterface.returnCoins();
        Assert.assertEquals(vendingMachineInterface.checkDisplay(),VendingMachineInterface.EXACT_CHANGE);

        vendingMachineInterface.setExactChange(false);
        Assert.assertEquals(vendingMachineInterface.checkDisplay(),VendingMachineInterface.INSERT_COIN);
    }

    @Test
    public void TakeChangeTest() {
        vendingMachineInterface.insertCoin(Coin.Penny);
        vendingMachineInterface.insertCoin(Coin.Penny);
        Assert.assertEquals(vendingMachineInterface.takeChange(), 2);
        Assert.assertEquals(vendingMachineInterface.displayChange(), "$0.00");
    }
}


