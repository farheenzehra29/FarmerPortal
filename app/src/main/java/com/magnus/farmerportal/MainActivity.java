package com.magnus.farmerportal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void LoginConsumer(View view) {
        Intent intent=new Intent(getApplicationContext(), LoginConsumer.class);
        startActivity(intent);
    }

    public void farmerLogIn(View view) {
        Intent intent=new Intent(getApplicationContext(), LoginFarmer.class);
        startActivity(intent);
    }
}
