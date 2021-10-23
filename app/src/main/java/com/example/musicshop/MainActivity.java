package com.example.musicshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    int counter = 0;
    Spinner spinner;
    HashMap goodsMap;
    String goodName;
    Double goodPrice;
    EditText userNameEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userNameEditText = findViewById(R.id.nameEditText);


        createSpinner();
        createMap();
    }

    void createSpinner(){

        spinner = findViewById(R.id.spinner3);
        spinner.setOnItemSelectedListener(this);


        ArrayList spinnerArrayList;
        ArrayAdapter spinnerAdapter;
        spinnerArrayList = new ArrayList();
        spinnerArrayList.add("guitar");
        spinnerArrayList.add("drums");
        spinnerArrayList.add("keyboard");

        spinnerAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, spinnerArrayList);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinner.setAdapter(spinnerAdapter);


    }

    void createMap(){
        goodsMap = new HashMap();
        goodsMap.put("guitar", 69.0);
        goodsMap.put("drums", 420.0);
        goodsMap.put("keyboard", 351.0);
    }


    public void plusOne(View view) {
        TextView quantityCounter = findViewById(R.id.quantityNomberText);
        if(counter == 10){quantityCounter.setText(""+counter);}
        else {
            counter = counter + 1;
            quantityCounter.setText(""+counter);
        }
        goodPrice = (double)goodsMap.get(goodName);
        TextView priceTextView = findViewById(R.id.dollarText);
        priceTextView.setText(""+ counter*goodPrice);
    }

    public void minusOne(View view) {
        TextView quantityCounter = findViewById(R.id.quantityNomberText);
        if (counter==0){
            quantityCounter.setText(""+counter);
        }
        else{counter--;
            quantityCounter.setText(""+counter);}
        goodPrice = (double)goodsMap.get(goodName);
        TextView priceTextView = findViewById(R.id.dollarText);
        priceTextView.setText(""+ counter*goodPrice);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        goodName = spinner.getSelectedItem().toString();
        goodPrice = (double)goodsMap.get(goodName);
        TextView priceTextView = findViewById(R.id.dollarText);
        priceTextView.setText(""+ counter*goodPrice);
         ImageView GoodImage;
         GoodImage = findViewById(R.id.GoodImage);
        switch (spinner.getSelectedItem().toString()){
            case ("guitar"):
                GoodImage.setImageResource(R.drawable.bass);
                break;
            case ("drums"):
                GoodImage.setImageResource(R.drawable.drums);
                break;
            case ("keyboard"):
                GoodImage.setImageResource(R.drawable.keyboard);
                break;

        }


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void addToCart(View view) {
        Order order = new Order();

        order.userName = userNameEditText.getText().toString();

        order.goodsName = goodName;

        order.orderPrice = counter*goodPrice;

        order.price = goodPrice;

        order.quantity = counter;

        Intent orderIntent = new Intent(MainActivity.this, OrderActivity.class);
        orderIntent.putExtra("userName", order.userName);
        orderIntent.putExtra("goodsName", order.goodsName);
        orderIntent.putExtra("orderPrice", order.orderPrice);
        orderIntent.putExtra("price", order.price);
        orderIntent.putExtra("quantity", order.quantity);

        startActivity(orderIntent);
    }
}