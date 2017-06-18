package com.ascendantc.vendingmacinekata_android;

import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.List;

public class VendingMachineActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    VendingMachineInterface vendingMachineInterface = new VendingMachineInterface();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vending_machine);

        Spinner selectCoins = (Spinner) findViewById(R.id.spinner);
        String[] items = new String[]{"Select Coin", "Nickel", "Dime", "Quarter", "Penny"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        selectCoins.setAdapter(adapter);
        selectCoins.setOnItemSelectedListener(this);

        TextView displayLabel = (TextView) findViewById(R.id.money_label);
        displayLabel.setText(vendingMachineInterface.getDisplayText());
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        // On selecting a spinner item
        String coinName = parent.getItemAtPosition(position).toString();
        if (position > 0) {
            vendingMachineInterface.insertCoin(Coin.valueOf(coinName));
            updateDisplays();
            parent.setSelection(0);
         }
    }
    public void onNothingSelected(AdapterView<?> arg0) {
        //  Auto-generated method stub
    }

    void updateDisplays() {
        TextView displayLabel = (TextView) findViewById(R.id.money_label);
        TextView changelabel = (TextView) findViewById(R.id.change_label);
        displayLabel.setText(vendingMachineInterface.checkDisplay());
        changelabel.setText(vendingMachineInterface.displayChange());

        // run display again after delay for next message
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                TextView displayLabel = (TextView) findViewById(R.id.money_label);
                displayLabel.setText(vendingMachineInterface.checkDisplay());
            }
        }, 1500);
    }

    public void buyChips(View view) {
        vendingMachineInterface.buyChips();
        updateDisplays();
    }

    public void buyCandy(View view) {
        vendingMachineInterface.buyCandy();
        updateDisplays();
    }

    public void buyCola(View view) {
        vendingMachineInterface.buyCola();
        updateDisplays();
    }

    public void takeChange(View view) {
        vendingMachineInterface.takeChange();
        updateDisplays();
    }

    public void exactChangeToggle(View view) {
        vendingMachineInterface.setExactChange(((ToggleButton) view).isChecked());
        updateDisplays();
    }

    public void soldOutToggle(View view) {
        vendingMachineInterface.setSoldOut(((ToggleButton) view).isChecked());
        updateDisplays();
    }

    public void coinReturnClick(View view) {
        vendingMachineInterface.returnCoins();
        updateDisplays();
    }
}
