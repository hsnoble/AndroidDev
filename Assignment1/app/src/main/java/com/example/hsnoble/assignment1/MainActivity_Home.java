package com.example.hsnoble.assignment1;

import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity_Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__home);

        //Prepare buttons
        final Button hi = findViewById(R.id.say_hi);
        final Button hello = findViewById(R.id.say_hello);

        //Prepare Radio Groups
        final RadioGroup hi_group = findViewById(R.id.hi_radioGroup);
        final RadioGroup hello_group = findViewById(R.id.hello_radioGroup);

        //Prepare Text view
        final TextView world = findViewById(R.id.World);
        final TextView seneca = findViewById(R.id.seneca);

        //Listens to radio group
        hi_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                //for hi
                //Gets radio ID
                final int checkedName = hi_group.getCheckedRadioButtonId();
                //Gets the radio button itself
                final RadioButton rad_button = (RadioButton) findViewById(checkedName);

                //Create toast
                Toast.makeText(MainActivity_Home.this, rad_button.getText(), Toast.LENGTH_SHORT).show();
                //Implement button functionality
                hi.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        world.setText(rad_button.getText());
                    }
                });
            }
        });
        hello_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedID) {
                //for hello
                //Get ID for radio button and gets the button itself
                final int checkedName = hello_group.getCheckedRadioButtonId();
                final RadioButton rad_button = (RadioButton) findViewById(checkedName);

                //Make toast
                Toast.makeText(MainActivity_Home.this, rad_button.getText(), Toast.LENGTH_SHORT).show();
                //Implement button functionality
                hello.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        seneca.setText(rad_button.getText());
                    }
                });
            }
        });
    }
}
