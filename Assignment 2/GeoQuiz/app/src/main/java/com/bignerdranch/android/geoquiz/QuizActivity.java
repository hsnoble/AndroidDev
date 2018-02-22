package com.bignerdranch.android.geoquiz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity {

    private static final String TAG = "QuizActivity";
    private static final String KEY_INDEX = "index";
    private static final int REQUEST_CODE_CHEAT = 0;
    private static final int REQUEST_CODE_HINT = 1;

    private Button mTrueButton;
    private Button mFalseButton;
    private Button mNextButton;
    private Button mCheatButton;
    //NEW
    private Button mPrevButton;
    private Button mHintButton;

    private TextView mQuestionTextView;

    //Added
    private TextView score;
    private TextView questions_complete;
    private Button hint;

    //points and other data
    private int point = 0;

    private Question[] mQuestionBank = new Question[] {
            new Question(R.string.question_australia, true),
            new Question(R.string.question_oceans, true),
            new Question(R.string.question_mideast, false),
            new Question(R.string.question_africa, false),
            new Question(R.string.question_americas, true),
            new Question(R.string.question_asia, true),
    };

    private int mCurrentIndex = 0;
    private boolean mIsCheater;
    //Cheater bank - an array of booleans that determine which question the user cheated on
    private int sz = mQuestionBank.length;
    private boolean [] cheaterBank = new boolean [sz];
    private boolean [] completedQuestions = new boolean [sz];
    private int questionCompleteCount;
    //hint
    private boolean mHint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate(Bundle) called");
        setContentView(R.layout.activity_quiz);

        //initialize cheat bank array
        for(int x = 0; x < sz; x++)
        {
            cheaterBank[x] = false;
            completedQuestions[x] = false;
        }
        questionCompleteCount = 0;

        if (savedInstanceState != null) {
            mCurrentIndex = savedInstanceState.getInt(KEY_INDEX, 0);
        }

        mQuestionTextView = (TextView) findViewById(R.id.question_text_view);

        mTrueButton = (Button) findViewById(R.id.true_button);
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(true);
            }
        });

        mFalseButton = (Button) findViewById(R.id.false_button);
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(false);
            }
        });

        mNextButton = (Button) findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
                mIsCheater = false;
                mHint = false;
                updateQuestion();
            }
        });

        //Prev button
        mPrevButton = (Button) findViewById(R.id.prev);
        mPrevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //reverse wrap around
                mCurrentIndex = ((mCurrentIndex - 1) + mQuestionBank.length) % mQuestionBank.length;
                mIsCheater = false;
                mHint = false;
                updateQuestion();
            }
        });

        mCheatButton = (Button) findViewById(R.id.cheat_button);
        mCheatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean answerIsTrue = mQuestionBank[mCurrentIndex].isAnswerTrue();
                //open cheat window
                Intent intent = CheatActivity.newIntent(QuizActivity.this, answerIsTrue);
                startActivityForResult(intent, REQUEST_CODE_CHEAT);
            }
        });

        //hint button
        mHintButton = (Button) findViewById(R.id.hint);
        mHintButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //open hint window
                Intent intent = HintActivity.newIntent(QuizActivity.this, mCurrentIndex);
                startActivityForResult(intent, REQUEST_CODE_HINT);
            }
        });

        //Score and progress
        score = (TextView) findViewById(R.id.score);
        questions_complete = (TextView) findViewById(R.id.q_completed);

        updateQuestion();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            return;
        }

        if (requestCode == REQUEST_CODE_CHEAT) {
            if (data == null) {
                return;
            }
            //get boolean
            mIsCheater = CheatActivity.wasAnswerShown(data);
            if(mIsCheater)
            {
                //cheater bank add
                cheaterBank[mCurrentIndex] = true;
            }
        }
        if(requestCode == REQUEST_CODE_HINT){
            if (data == null) {
                return;
            }
            //get boolean
            mHint = HintActivity.wasHintShown(data);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart() called");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume() called");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause() called");
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        Log.i(TAG, "onSaveInstanceState");
        savedInstanceState.putInt(KEY_INDEX, mCurrentIndex);
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop() called");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy() called");
    }

    private void updateQuestion() {
        int question = mQuestionBank[mCurrentIndex].getTextResId();
        mQuestionTextView.setText(question);
    }

    private boolean questionCompleted(int questionIndex)
    {
        return completedQuestions[questionIndex];
    }
    private void setQuestionCompleted(int questionIndex)
    {
        completedQuestions[questionIndex] = true;
        questionCompleteCount++;
    }
    private void checkAnswer(boolean userPressedTrue) {
        boolean answerIsTrue = mQuestionBank[mCurrentIndex].isAnswerTrue();

        int messageResId = 0;
        boolean qComplete = questionCompleted(mCurrentIndex);
        //get info from cheat bank
        if(!qComplete) {
            mIsCheater = cheaterBank[mCurrentIndex];
            //check if question has been completed
            //check cheater
            if (mIsCheater) {
                messageResId = R.string.judgment_toast;
            } else if (mHint) {
                messageResId = R.string.correct_toast_hint;
                point++;
                //Add score
                score.setText(Integer.toString(point));
            } else {
                if (userPressedTrue == answerIsTrue) {
                    messageResId = R.string.correct_toast;
                    point += 2;
                } else {
                    messageResId = R.string.incorrect_toast;
                    point--;
                }
                //Add score
                score.setText(Integer.toString(point));
            }
            setQuestionCompleted(mCurrentIndex);
            questions_complete.setText(Integer.toString(questionCompleteCount));
        }
        else
        {
            messageResId = R.string.question_completed;
        }
        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT)
                .show();
    }
}
