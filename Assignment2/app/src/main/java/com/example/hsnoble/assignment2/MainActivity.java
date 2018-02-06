package com.example.hsnoble.assignment2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Get Data
        final EditText usermame = findViewById(R.id.userText);
        final EditText password = findViewById(R.id.passText);
        /* Make the Username Input Work */

        final Button buttonUser = findViewById(R.id.user);
        buttonUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Take username
                buttonUser.setText(usermame.getText());
            }
        });
        //Make the Password Input work
        final Button buttonPass = findViewById(R.id.pass);
        buttonPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Take Password
                buttonPass.setText(password.getText());
            }
        });
    }



}
