package com.example.hsnoble.assignment1;

import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioGroup;

public class MainActivity_Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__home);
        //Prepare buttons
        final Button hi = findViewById(R.id.say_hi);
        final Button hello = findViewById(R.id.say_hello);
        //Prepare Radio Groups
        RadioGroup hi_group = (RadioGroup) findViewById(R.id.hi_radio);
        RadioGroup hello_group = (RadioGroup) findViewById(R.id.hello_radio);

        hi_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                //for hi
            }
        });
        hello_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedID) {
                //for hello
            }
        });
    }
}
