package com.example.hsnoble.assignment2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class HelloScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello_screen);
        Bundle b = getIntent().getExtras();
        //Standard
        final TextView  datType = findViewById(R.id.DatTypeSpace);
        final TextView  data = findViewById(R.id.DataSpace);
        //If password field is needed
        final TextView  datType2 = findViewById(R.id.DatTypeSpace2);
        final TextView  data2 = findViewById(R.id.DataSpace2);
        //Get Count
        int count = b.getInt("count");
        if(count == 1)
        {
            //GetData
            String dataIn = b.getString("data");
            String type = b.getString("type");
            datType.setText(type);
            data.setText(dataIn);
        }
        else if (count == 2)
        {
            //GetData
            String data_user = b.getString("dataUser");
            String type_user = b.getString("typeUser");

            String data_pass = b.getString("dataPass");
            String type_pass = b.getString("typePass");

            datType.setText(type_user);
            data.setText(data_user);
            datType2.setText(type_pass);
            data2.setText(data_pass);
        }

    }
}
