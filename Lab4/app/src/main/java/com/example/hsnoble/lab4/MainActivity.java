package com.example.hsnoble.lab4;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.println("WORKING");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Populate with alphabet letters
        char[] alphabet = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
                'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
        int splitCount = alphabet.length / 2;
        char [] letterCol1 = new char [splitCount];
        char [] letterCol2 = new char [splitCount];
        //column 1
        for(int x = 0; x < splitCount; x++)
        {
            letterCol1[x] = alphabet[x];
        }
        //column 2
        for(int x = 0; x < splitCount; x++)
        {
            letterCol2[x] = alphabet[13 + x];
        }

        //Implement UI assets
        LinearLayout col1;
        LinearLayout col2;
        col1 = (LinearLayout)findViewById(R.id.col1);
        col2 = (LinearLayout) findViewById(R.id.col2);

        LinearLayout.LayoutParams param =new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        param.setMargins(0,0,0,3);
        //column 1
        TextView [] col1View = new TextView [letterCol1.length];
        for(int x = 0; x < letterCol1.length; x++)
        {
            col1View[x] = new TextView(this);
            col1View[x].setLayoutParams(param);
            col1View[x].setTextSize(TypedValue.COMPLEX_UNIT_DIP, 36);
            col1View[x].setPadding(100,10,100,10);
            col1View[x].setBackgroundColor(Color.YELLOW);
            col1View[x].setTextColor(Color.RED);
            col1View[x].setText(Character.toString(letterCol1[x]));
            col1View[x].setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            col1.addView(col1View[x]);
        }
        //column 2
        TextView [] col2View = new TextView [letterCol2.length];
        for(int x = 0; x < letterCol2.length; x++)
        {
            col2View[x] = new TextView(this);
            col2View[x].setLayoutParams(param);
            col2View[x].setTextSize(TypedValue.COMPLEX_UNIT_DIP, 36);
            col2View[x].setPadding(100,10,100,10);
            col2View[x].setBackgroundColor(Color.RED);
            col2View[x].setTextColor(Color.YELLOW);
            col2View[x].setText(Character.toString(letterCol2[x]));
            col2View[x].setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            col2.addView(col2View[x]);
        }
    }
}
