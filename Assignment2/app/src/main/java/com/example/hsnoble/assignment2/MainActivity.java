package com.example.hsnoble.assignment2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    public void nextScreen(char type, String content)
    {
        Intent newScreen = new Intent(this, HelloScreen.class);
        if(type == 'u')
        {
            newScreen.putExtra("type", "Username:");
            newScreen.putExtra("data", content);
            newScreen.putExtra("count", 1);
        }
        else if (type == 'p')
        {
            newScreen.putExtra("type", "Password:");
            newScreen.putExtra("data", content);
            newScreen.putExtra("count", 1);
        }
        startActivity(newScreen);
    }
    public void nextScreen(char type, String user, String pass)
    {
        Intent newScr = new Intent(this, HelloScreen.class);
        if(type == 'b')
        {
            newScr.putExtra("typeUser", "Username:");
            newScr.putExtra("dataUser", user);

            newScr.putExtra("typePass", "Password:");
            newScr.putExtra("dataPass", pass);
            newScr.putExtra("count", 2);
        }
        startActivity(newScr);
    }
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
                nextScreen('u', usermame.getText().toString());
            }
        });
        //Make the Password Input work
        final Button buttonPass = findViewById(R.id.pass);
        buttonPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Take Password
                nextScreen('p', password.getText().toString());
            }
        });

        //Make the Password Input work
        final Button buttonLogIn = findViewById(R.id.log);
        buttonLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nextScreen('b', password.getText().toString(), usermame.getText().toString());
            }
        });
    }



}
