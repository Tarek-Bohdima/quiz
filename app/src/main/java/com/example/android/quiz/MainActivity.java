package com.example.android.quiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.android.quiz.R;

public class MainActivity extends AppCompatActivity {

    //Declaring global variables
    int score;
    EditText nameField;
    CheckBox india;
    CheckBox unitedStates;
    CheckBox china;
    CheckBox unitedKingdom;
    RadioGroup answers1;
    RadioGroup answers2;
    RadioGroup answers3;
    RadioButton answer1;
    RadioButton answer2;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initializing varaibles
        score = 0;
        nameField = findViewById(R.id.name_field);
        india = findViewById(R.id.checkbox_answer1);
        unitedStates = findViewById(R.id.checkbox_answer2);
        china = findViewById(R.id.checkbox_answer3);
        unitedKingdom = findViewById(R.id.checkbox_answer4);
        //RadioGroups initializations
        answers1 = findViewById(R.id.Radio_Answers1);
        answers2 = findViewById(R.id.Radio_Answers2);
        answers3 = findViewById(R.id.Radio_Answers3);
        //RadioButtoons initializations
        answer1 = findViewById(R.id.radio_answer1);
        answer2 = findViewById(R.id.radio_answer2);

    }

    // method called for incrementing score
    private void increment_score() {
        score +=1;
    }


    //method called to calculate final score
    public void score(View view) {

        String name = nameField.getText().toString();   //stores user's entered name
        Button btn = (Button) findViewById(R.id.score_button);
        btn.setEnabled(false);


        question1();
        question2();
        question3();
        question4();

        //Toast - Shows player's name and score
        Toast.makeText(this, "Your Score is: " + score +" of 4", Toast.LENGTH_LONG).show();

    }

    //method called to calculate 1st question
    public void question1() {
        switch (answers1.getCheckedRadioButtonId()) {
            case R.id.radio_answer2:
                increment_score();
                break;
        }
    }

    //method called to calculate 2nd question
    public void question2() {
        switch (answers2.getCheckedRadioButtonId()){
            case R.id.radio_answer3:
                increment_score();
                break;
        }
    }

    //method called to calculate 3rd question
    public void question3(){
        switch (answers3.getCheckedRadioButtonId()){
            case R.id.radio_answer8:
                increment_score();
                break;
        }
    }

    //method called to calculate 4th question
    public void question4(){

        Boolean question4a = india.isChecked();
        Boolean question4b = unitedStates.isChecked();
        Boolean question4c = china.isChecked();
        Boolean question4d = unitedKingdom.isChecked();

        if (question4a && question4b && question4c && !question4d){
            increment_score();
        }
    }

    //method to share score by email or other social media on user's phone
    public void onSharing(View view) {
        String name = nameField.getText().toString();

        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_SUBJECT, "Final Score");
        sendIntent.putExtra(Intent.EXTRA_TEXT, name + "'s score in the Global Warming Quiz is " + score + " of 4");
        sendIntent.setType("text/plain");
        startActivity(sendIntent);
    }

    //method to exit application
    public void onExit(View view) {
        finish();
    }
}




