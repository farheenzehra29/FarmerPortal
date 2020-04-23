package com.magnus.farmerportal;

/*import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.view.View;
import androidx.appcompat.app.AlertDialog;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;*/
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import androidx.appcompat.app.AppCompatActivity;

public class ConsumerActivity extends AppCompatActivity {
  /*  Button view, logo;
    int k = 0;
    DBHelper dbHelper;
    TextView show;
    EditText user, pass;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consumer);
        String[] crops={"Ragi","Rice","Onion","Carrot","Potato"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line, crops);
        final AutoCompleteTextView selectedCrop = (AutoCompleteTextView)findViewById(R.id.selectCrop);
        selectedCrop.setThreshold(2);

        selectedCrop.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent event){
                selectedCrop.showDropDown();
                return false;
            }
        });
        selectedCrop.setAdapter(adapter);

    }

    public void showFarmerList(View view) {

    }

}

     /*   dbHelper = new DBHelper(this);

        view = (Button)findViewById(R.id.view);
        logo = (Button) findViewById(R.id.logout);
        show = (TextView)findViewById(R.id.showAll);
        user = (EditText)findViewById(R.id.user);
        pass = (EditText)findViewById(R.id.password);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res = dbHelper.getData();
                StringBuilder stringB = new StringBuilder();
                if(res!=null && res.getCount()>0){
                    while (res.moveToNext()){
                        stringB.append("Name: "+res.getString(0)+"\n");
                        stringB.append("Username: "+res.getString(1)+"\n");
                        stringB.append("Password: "+res.getString(2)+"\n");
                    }
                    show.setText(stringB.toString());
                    Toast.makeText(ConsumerActivity.this, "Data showed", Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(ConsumerActivity.this, "Data N/A", Toast.LENGTH_LONG).show();
                }
            }
        });

        logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(ConsumerActivity.this);
                builder.setTitle("Info");
                builder.setMessage("Are you sure you want to logout ??");
                builder.setPositiveButton("Yes I'm sure", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        Intent intent = new Intent(ConsumerActivity.this, LoginConsumer.class);
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
            Toast.makeText(ConsumerActivity.this, "Press again to go previous activity.", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(ConsumerActivity.this, LoginConsumer.class);
            startActivity(intent);
        }
        else
        {
            finish();
        }*/
