package com.bignerdranch.android.geoquiz;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HintActivity extends AppCompatActivity {

    private Button hintButton;
    private Button exit;
    private TextView hint;

    //private int hId;
    private boolean hintShown;
    private int[] mHintBank = new int[]{
            R.string.hint_australia, R.string.hint_oceans, R.string.hint_mideast,
            R.string.hint_africa, R.string.hint_americas, R.string.hint_asia};

    public static Intent newIntent (Context packageContext, int hintID)
    {
        Intent in = new Intent(packageContext, HintActivity.class);
        in.putExtra("hintPhrase", hintID);
        return in;
    }
    public static boolean wasHintShown (Intent result)
    {
        return result.getBooleanExtra("hint_shown", false);
    }
    private void setHintShown(boolean hintShown)
    {
        Intent data = new Intent();
        data.putExtra("hint_shown", hintShown);
        setResult(RESULT_OK, data);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hint);

        //get hint phrase
        final int hIndex = getIntent().getIntExtra("hintPhrase",0);

        hintButton = (Button) findViewById(R.id.show_hint_button);
        exit = (Button) findViewById(R.id.exit);

        hint = (TextView) findViewById(R.id.hint_textview);
        //implement listeners
        hintButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hint.setText(mHintBank[hIndex]);
                setHintShown(true);
            }
        });




    }
}
