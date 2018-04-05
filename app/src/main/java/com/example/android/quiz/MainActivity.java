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
    private int score;
    private EditText nameField;
    private CheckBox india;
    private CheckBox unitedStates;
    private CheckBox china;
    private CheckBox unitedKingdom;
    private RadioGroup answers1;
    private RadioGroup answers2;
    private RadioGroup answers3;
    private RadioButton answer1;
    private RadioButton answer2;




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
        Toast.makeText(this, getString(R.string.Toast_Message) + score +" "+getString(R.string.total_score_toast), Toast.LENGTH_LONG).show();

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
        sendIntent.putExtra(Intent.EXTRA_TEXT, name + getString(R.string.sharing_message) + score +" "+ getString(R.string.total_score));
        sendIntent.setType("text/plain");
        startActivity(sendIntent);
    }

    //method to exit application
    public void onExit(View view) {
        finish();
    }
}




