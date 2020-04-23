package com.magnus.farmerportal;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ResultFarmer extends AppCompatActivity {
    Button view, logo;
    int k = 0;
    DBHelperFarmer dbHelper;
    TextView show;
    TextView show1;
    EditText user, pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consumer_activity);

        Intent intent = getIntent();
        final int id=intent.getIntExtra("Id",0);


        Toast.makeText(this,Integer.toString(id), Toast.LENGTH_SHORT).show();
        dbHelper = new DBHelperFarmer(this);
        view = (Button)findViewById(R.id.view);
        logo = (Button) findViewById(R.id.logout);
        show = (TextView)findViewById(R.id.showAll);
        show1 = (TextView)findViewById(R.id.textView3);
        user = (EditText)findViewById(R.id.user);
        pass = (EditText)findViewById(R.id.password);
       // delete=(Button)findViewById(R.id.deleteCrop);

        Calendar calendar = Calendar.getInstance();
        long timeMilli2 = calendar.getTimeInMillis();
        Cursor r1 = dbHelper.getDate(id);
        if(r1!=null && r1.getCount()>0) {

            while (r1.moveToNext()) {
                long d = Long.parseLong(String.valueOf(r1.getInt(0)));
                Log.d("----","Entered");
                d += 60000;
                if (d> timeMilli2) {
                    Log.d("----","final entry");
                    dbHelper.deleteCrop(id);
                }
            }
        }
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res1 = dbHelper.getData1(id);
                StringBuilder stringB1 = new StringBuilder();
                if(res1!=null && res1.getCount()>0) {
                    while (res1.moveToNext()) {
                        // stringB1.append("COLUMN_NAME: "+res1.getString(1)+"\n");
                        //stringB1.append("COLUMN_USERNAME: "+res1.getString(2)+"\n");
                        stringB1.append("Location: " + res1.getString(6) + "\n");
                    }
                    show1.setText(stringB1.toString());
                }

                    Cursor res = dbHelper.getFarmerDetails(id);
                StringBuilder stringB = new StringBuilder();
                if(res!=null && res.getCount()>0){
                    while (res.moveToNext()){
                        stringB.append("Price: "+res.getString(1)+"\n");
                        stringB.append("Quantity: "+res.getString(2)+"\n");
                        stringB.append("Crop: "+res.getString(3)+"\n");
                        stringB.append("Farmer ID: "+res.getString(4)+"\n");
                        stringB.append("------------\n");
                    }
                    show.setText(stringB.toString());
                    Toast.makeText(ResultFarmer.this, "Data showed", Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(ResultFarmer.this, "Data N/A", Toast.LENGTH_LONG).show();
                }
            }
        });
        logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(ResultFarmer.this);
                builder.setTitle("Info");
                builder.setMessage("Are you sure you want to logout ??");

                builder.setPositiveButton("Yes I'm sure", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(ResultFarmer.this, LoginFarmer.class);
                        startActivity(intent);
                        finish();
                    }
                });
                builder.setNegativeButton("Not now", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }
    public void onBackPressed()
    {
        k++;
        if(k == 1)
        {
            Toast.makeText(ResultFarmer.this, "Press again to go previous activity.",
                    Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(ResultFarmer.this, FarmerActivity.class);
            startActivity(intent);
        }
        else
        {
            finish();
        }
    }
}