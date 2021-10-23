package com.example.musicshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class OrderActivity extends AppCompatActivity {
String[] addresses = {"serafim.kruykov@gmail.com"};
String subject = "Order from Music Shop";
String emailText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        setTitle("Your order");

        Intent receivedOrderIntent = getIntent();
        String userName = receivedOrderIntent.getStringExtra("userName");
        String goodsName = receivedOrderIntent.getStringExtra("goodsName");
        int quantity = receivedOrderIntent.getIntExtra("quantity",0);
        Double orderPrice = receivedOrderIntent.getDoubleExtra("orderPrice",0);
        Double price = receivedOrderIntent.getDoubleExtra("price",0);
        TextView orderTextView = findViewById(R.id.orderTextView);
        orderTextView.setText("Costomer name: "+userName +"\n"+"Item: "+ goodsName +"\n"+"Price: "+ price+"\n"+"Quantity: "+ quantity +"\n"+"OrderPrice: "+ orderPrice);


        emailText ="Costomer name: "+userName +"\n"+"Item: "+ goodsName +"\n"+"Price: "+ price+"\n"+"Quantity: "+ quantity +"\n"+"OrderPrice: "+ orderPrice ;
    }

    public void submitOrder(View view) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL, addresses);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, emailText);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}