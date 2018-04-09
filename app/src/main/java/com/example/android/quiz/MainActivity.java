package com.example.android.quiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final int INCREMENT = 1;
    //Declaring global variables
    private int score;
    private EditText nameField;
    private CheckBox india;
    private CheckBox unitedStates;
    private CheckBox china;
    private CheckBox unitedKingdom;
    private RadioGroup answers1;
    private RadioGroup answers2;
    private RadioGroup answers3;
    private Button btn;
    private int shareScore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bindViews();

        //initializing global variable score
        score = 0;

    }

    private void bindViews() {
        nameField = findViewById(R.id.name_field);
        india = findViewById(R.id.checkbox_answer1);
        unitedStates = findViewById(R.id.checkbox_answer2);
        china = findViewById(R.id.checkbox_answer3);
        unitedKingdom = findViewById(R.id.checkbox_answer4);
        answers1 = findViewById(R.id.Radio_Answers1);
        answers2 = findViewById(R.id.Radio_Answers2);
        answers3 = findViewById(R.id.Radio_Answers3);
        btn = (Button) findViewById(R.id.score_button);

    }

    private View bindView(int viewId) {
        return findViewById(viewId);
    }

    // method called for incrementing score
    private void incrementScore() {
        score += INCREMENT;
    }


    //method called to calculate final score
    public void score(View view) {


        btn.setEnabled(false);

        if (answers1.getCheckedRadioButtonId() == -1 || answers2.getCheckedRadioButtonId() == -1 || answers3.getCheckedRadioButtonId() == -1) {
            // no radio buttons are checked in one of the first 3 questions
            btn.setEnabled(true); //enable the score button
            Toast.makeText(this, R.string.toast_message_complete_test, Toast.LENGTH_SHORT).show();
        } else {
            // one of the radio buttons is checked
            question1();
            question2();
            question3();
            question4();
        }

        shareScore = score;

        Toast.makeText(this, getString(R.string.Toast_Message) + score + " " + getString(R.string.total_score_toast), Toast.LENGTH_LONG).show();
        score = 0;
    }

    //method called to calculate 1st question
    private void question1() {
        switch (answers1.getCheckedRadioButtonId()) {
            case R.id.radio_answer2:
                incrementScore();
                break;
        }

    }

    //method called to calculate 2nd question
    private void question2() {
        switch (answers2.getCheckedRadioButtonId()) {
            case R.id.radio_answer3:
                incrementScore();
                break;
        }
    }

    //method called to calculate 3rd question
    private void question3() {
        switch (answers3.getCheckedRadioButtonId()) {
            case R.id.radio_answer8:
                incrementScore();
                break;
        }
    }

    //method called to calculate 4th question
    private void question4() {

        Boolean question4a = india.isChecked();
        Boolean question4b = unitedStates.isChecked();
        Boolean question4c = china.isChecked();
        Boolean question4d = unitedKingdom.isChecked();

        if (question4a && question4b && question4c && !question4d) {
            unitedKingdom.setEnabled(false);
            unitedStates.setEnabled(false);
            india.setEnabled(false);
            china.setEnabled(false);
            incrementScore();
        } else if (!question4a && !question4b && !question4c && !question4d) {

            // in case no Check buttons are checked
            score = 0;
            btn.setEnabled(true);
            Toast.makeText(this, R.string.toast_message_complete_4, Toast.LENGTH_SHORT).show();

        }
    }

    //method to share score by email or other social media on user's phone
    public void onSharing(View view) {
        String name = nameField.getText().toString();

        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.intent_message_subject));
        sendIntent.putExtra(Intent.EXTRA_TEXT, name + getString(R.string.sharing_message) + shareScore + " " + getString(R.string.total_score));
        sendIntent.setType("text/plain");
        if (sendIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(sendIntent);
        }
    }

    //method to exit application
    public void onExit(View view) {
        finish();
    }
}




