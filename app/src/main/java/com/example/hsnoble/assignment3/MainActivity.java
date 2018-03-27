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
        List teamNameList = db.viewAll();
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
    }
}
