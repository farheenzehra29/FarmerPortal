package com.magnus.farmerportal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class FarmerActivity extends AppCompatActivity {
    TextView disp;
    Button done;
    DBHelperFarmer dbHelperFarmer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farmer);

        Intent intent = getIntent();
        //String cropSelected = intent.getStringExtra("cropSelected");
        final TextView crop=findViewById(R.id.textView2);
        String t1=intent.getStringExtra("CropSelected");
        crop.setText(t1);
        disp=(TextView)findViewById(R.id.display);
        done=(Button)findViewById(R.id.done);
        final EditText quantity=(EditText)findViewById(R.id.QuantityEditText);
        final EditText price=(EditText)findViewById(R.id.PriceeditText);
        dbHelperFarmer = new DBHelperFarmer(this);

        Button updateCrop=(Button)findViewById(R.id.updateCrop);
        updateCrop.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(quantity.getText().toString().equals("") && price.getText().toString().equals(""))
                {
                    Toast.makeText(getApplicationContext(), "Please enter valid credentials", Toast.LENGTH_SHORT).show();
                }
                else if(Integer.parseInt(quantity.getText().toString()) > 20 || Integer.parseInt(quantity.getText().toString()) < 0 )
                {
                    Toast.makeText(getApplicationContext(), "Please enter Quantity in the Range 1 and 20", Toast.LENGTH_SHORT).show();
                }

                else {
                    ArrayList<String> returnDetails = new ArrayList<String>();
                    returnDetails.add(quantity.getText().toString());
                    returnDetails.add(price.getText().toString());
                    setTextViewValues(returnDetails,disp);
                    Cursor res=dbHelperFarmer.getData();
                    dbHelperFarmer.addCropDetails(price.getText().toString(),quantity.getText().toString(),crop.getText().toString(),
                            res.getString(0));
                    Intent resultIntent = new Intent(FarmerActivity.this, CropsSelection.class);
                    //resultIntent.putExtra("returnedDetails", returnDetails);
                    //setResult(RESULT_OK, resultIntent);
                    startActivity(resultIntent);
                    //finishActivity(2);
                   // finish();
                }
            }

        });
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res=dbHelperFarmer.getData();
                dbHelperFarmer.addCropDetails(price.getText().toString(),quantity.getText().toString(),crop.getText().toString(),
                        res.getString(0));
                Intent resultIntent = new Intent(FarmerActivity.this, ResultFarmer.class);
                startActivity(resultIntent);
            }
        });
    }
    public void setTextViewValues (ArrayList<String> vals, TextView text) {
        //Variable to hold all the values
        String output = "";

        for (int i = 0; i < vals.size(); i++) {
            //Append all the values to a string
            output += vals.get(i)+ " ";
        }

        //Set the textview to the output string
        text.setText(output);
    }
}
/*
ArrayList<String> images = new ArrayList<>();
images.add("amit");
Intent i = new Intent(Firstactivity.this, Secondactivity.class);
i.putExtra("key", images);
 */