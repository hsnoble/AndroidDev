package com.example.hsnoble.assignment3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddTeam extends AppCompatActivity {

    private DBHandler db = new DBHandler(this);
    private String cty, team, sp, mvp_str, st, author;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_team);

        //TEXT FIELDS
        final EditText city = (EditText) findViewById(R.id.city);
        final EditText teamName = (EditText) findViewById(R.id.teamName);
        final EditText sport = (EditText) findViewById(R.id.sport);
        final EditText mvp = (EditText) findViewById(R.id.mvp);
        final EditText stadium = (EditText) findViewById(R.id.stadium);

        //Buttons
        Button exit = (Button) findViewById(R.id.exit_add);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                System.exit(0);
            }
        });

        //submit
        Button submit = (Button) findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Get Text
                cty = city.getText().toString();
                team = teamName.getText().toString();
                sp = sport.getText().toString();
                mvp_str = mvp.getText().toString();
                st = stadium.getText().toString();
                author = "Stephen Noble";
                //Create a team object
                Team t = new Team(author, team, cty, sp, mvp_str, st);
                db.insert(t);
                db.viewAll();
                //Open Main menu
                Intent i = new Intent(AddTeam.this, MainActivity.class);
                startActivity(i);
            }
        });

    }
}
