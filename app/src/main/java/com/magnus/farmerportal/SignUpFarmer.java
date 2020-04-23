package com.magnus.farmerportal;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SignUpFarmer extends AppCompatActivity {

    EditText name, user1, pass, cnfrmpass,phone;
    Button signin;
    ImageView rback;
    Cursor cursor;
    SQLiteDatabase db;
    CheckBox rshow;
    DBHelperFarmer dbHelper;
    String userlocation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_consumer);
        dbHelper = new DBHelperFarmer(this);
        Intent intent1=getIntent();
        final String[] position=intent1.getStringArrayExtra("Location");

        name = (EditText) findViewById(R.id.name);
        pass = (EditText) findViewById(R.id.password);
        cnfrmpass = (EditText) findViewById(R.id.cnfrmpassword);
        user1 = (EditText) findViewById(R.id.user);
        rshow = (CheckBox) findViewById(R.id.rshowPass);
        signin = (Button) findViewById(R.id.signin);
        rback = (ImageView) findViewById(R.id.rback);
        phone=(EditText)findViewById(R.id.phone);
        userlocation=position[0]+position[1]+position[2]+position[3];
        showPass();


        rback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUpFarmer.this, LoginFarmer.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public void signinBtn(View view) {

        if(name.getText().toString().equals("")||
                user1.getText().toString().equals("")||
                pass.getText().toString().equals("")||cnfrmpass.getText().toString().equals(""))
        {
            Toast.makeText(getApplicationContext(), "Please Enter Your Details", Toast.LENGTH_LONG).show();
            return;
        }

        // check if both password matches
        if(!pass.getText().toString().equals(cnfrmpass.getText().toString()))
        {
            Toast.makeText(getApplicationContext(), "Password does not match", Toast.LENGTH_LONG).show();
        }

         if(phone.getText().toString().length()!=10)
        {
            Toast.makeText(getApplicationContext(), "Please enter a 10 digit Phone number", Toast.LENGTH_LONG).show();
        }

        else {
             dbHelper.addUser(name.getText().toString(),
                     user1.getText().toString(), pass.getText().toString(),
                     cnfrmpass.getText().toString(),phone.getText().toString(),userlocation);


             Toast.makeText(SignUpFarmer.this, "Data Inserted", Toast.LENGTH_LONG).show();

            Intent intent = new Intent(SignUpFarmer.this, LoginFarmer.class);
            startActivity(intent);
        }

    }

    public void showPass(){
        rshow.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    pass.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    cnfrmpass.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                } else {
                    pass.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    cnfrmpass.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
            }
        });
    }

    public void onBackPressed()
    {
        Intent intent = new Intent(SignUpFarmer.this, LoginFarmer.class);
        startActivity(intent);
        finish();
    }
}
