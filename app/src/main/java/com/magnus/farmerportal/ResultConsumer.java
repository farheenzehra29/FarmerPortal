package com.magnus.farmerportal;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ResultConsumer extends AppCompatActivity {
   Button confirm;
   TextView name,phone,crop,priceF,quantityF;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consumer_result);

        name=(TextView)findViewById(R.id.nameF);
        phone=(TextView)findViewById(R.id.phoneF);
        crop=(TextView)findViewById(R.id.cropC);
        priceF=(TextView)findViewById(R.id.priceF);
        quantityF=(TextView)findViewById(R.id.quantityF);

        Intent intent = getIntent();
        String farmerName=intent.getStringExtra("farmer");
        String phoneNumber=intent.getStringExtra("number");
        String price=intent.getStringExtra("price");
        String quantity=intent.getStringExtra("quantity");
        String cropSelected=intent.getStringExtra("crop");

        name.setText(farmerName);
        phone.setText(phoneNumber);
        crop.setText(cropSelected);
        priceF.setText(price);
        quantityF.setText(quantity);


    }
}