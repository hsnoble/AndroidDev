package com.example.hsnoble.assignment3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class UpdateTeam extends AppCompatActivity {

    private DBHandler db = new DBHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_team);

        Button exit = (Button) findViewById(R.id.exit_update);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                System.exit(0);
            }
        });


        //Initializing EditTexts
        final EditText cityText, teamNameText, sportText, mvpText, stadiumText;
        cityText = findViewById(R.id.city);
        teamNameText = findViewById(R.id.teamName);
        sportText = findViewById(R.id.sport);
        mvpText = findViewById(R.id.mvp);
        stadiumText = findViewById(R.id.stadium);

        //GETTING EXTRAS
        Bundle team = getIntent().getExtras();
        String teamName, city, sport, mvp, stadium;
        final String author;
        author = (String)team.get("AUTHOR");
        teamName = (String)team.get("TEAM_NAME");
        city = (String)team.get("CITY");
        sport = (String)team.get("TEAM_NAME");
        mvp = (String)team.get("MVP");
        stadium = (String)team.get("STADIUM");

        final int pk = (Integer) team.get("PK");

        System.out.println("PK: " + pk);

        //Set Texts
        cityText.setText(city);
        teamNameText.setText(teamName);
        sportText.setText(sport);
        mvpText.setText(mvp);
        stadiumText.setText(stadium);

        final Button update = (Button) findViewById(R.id.update);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //get Edit Texts
                String c = cityText.getText().toString();
                String team = teamNameText.getText().toString();
                String sp = sportText.getText().toString();
                String mvp_obj = mvpText.getText().toString();
                String loc = stadiumText.getText().toString();
                //Create a team object for submit
                Team t = new Team(author, team, c, sp, mvp_obj, loc);
                update(pk, t);
            }
        });

    }
    public void update(int pk, Team t)
    {
        db.update(pk, t);
    }
}
