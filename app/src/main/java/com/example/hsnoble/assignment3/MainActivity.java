package com.example.hsnoble.assignment3;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private DBHandler db = new DBHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //ADD TEAM BUTTON
        Button add = (Button) findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Open Add Team Activity
                Intent i = new Intent(MainActivity.this, AddTeam.class);
                startActivity(i);
            }
        });

        //EXIT BUTTON
        Button exit = (Button) findViewById(R.id.exit);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                System.exit(0);
            }
        });

        //List of teams
        LinearLayout teams = (LinearLayout) findViewById(R.id.teams);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.bottomMargin = 10;
        List[] DBpackage = db.viewAll();

        List teamNameList = DBpackage[0];
        List PKPackage = DBpackage[1];

        Button[] teamButtons = new Button[teamNameList.size()];
        for(int x = 0; x < teamNameList.size(); x++)
        {
            teamButtons[x] = new Button(getApplicationContext());
            teamButtons[x].setLayoutParams(params);
            teamButtons[x].setText(teamNameList.get(x).toString());
            teamButtons[x].setBackgroundColor(Color.RED);
            teamButtons[x].setTextSize(18);
            teams.addView(teamButtons[x]);
        }
        for(int x= 0; x < teamButtons.length; x++)
        {
            final int idxFinal = (Integer) PKPackage.get(x);
            //Make the button go to the updata page with fields already filled up
            teamButtons[x].setOnClickListener(new View.OnClickListener(){
                public void onClick (View view) {
                    updatePage(idxFinal);
                }
            });
        }
    }
    //Goes to the update activity with the profile already queried
    public void updatePage(int PK)
    {
        Team t;
        t = db.find(PK);
        Intent i = new Intent(MainActivity.this, UpdateTeam.class);
        i.putExtra("AUTHOR", t.getAuthorName());
        i.putExtra("TEAM_NAME", t.getTeamName());
        i.putExtra("CITY", t.getCity());
        i.putExtra("SPORT", t.getSport());
        i.putExtra("MVP", t.getMvp());
        i.putExtra("STADIUM", t.getStadium());
        startActivity(i);
    }
}
