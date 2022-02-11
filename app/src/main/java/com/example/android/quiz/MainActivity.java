package com.example.android.quiz;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.android.quiz.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private static final int INCREMENT = 1;
    static final String SCORE = "score";
    //Declaring global variables
    private ActivityMainBinding binding;
    private String name;
    private int score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        name = binding.welcomePageLayout.nameField.getText().toString();
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        score = savedInstanceState.getInt(SCORE);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(SCORE, score);
    }


    // method called for incrementing score
    private void incrementScore() {
        score += INCREMENT;
    }

    //method called to calculate final score
    public void score(View view) {

        binding.scoreShareLayout.scoreButton.setEnabled(false);
        //check if one of the radio groups questions is not answered
        if (binding.firstQuestionLayout.RadioAnswers1.getCheckedRadioButtonId() == -1 ||
                binding.secondQuestionLayout.RadioAnswers2.getCheckedRadioButtonId() == -1 ||
                binding.thirdQuestionLayout.RadioAnswers3.getCheckedRadioButtonId() == -1) {
            // no radio buttons are checked in one of the first 3 questions
            binding.scoreShareLayout.scoreButton.setEnabled(true); //enable the score button
            Toast.makeText(this, R.string.toast_message_complete_test, Toast.LENGTH_SHORT).show();
        } else {
            // one of the radio buttons is checked
            question1();
            question2();
            question3();
            question4();

            Toast.makeText(this,
                    getString(R.string.Toast_Message) + score + " " + getString(R.string.total_score_toast),
                    Toast.LENGTH_LONG).show();
        }
        score = 0;
    }

    //method called to calculate 1st question
    private void question1() {
        if (binding.firstQuestionLayout.radioAnswer2.isChecked()) {
            incrementScore();
        }
    }

    //method called to calculate 2nd question
    private void question2() {
        if (binding.secondQuestionLayout.radioAnswer3.isChecked()) {
            incrementScore();
        }
    }

    //method called to calculate 3rd question
    private void question3() {
        if (binding.thirdQuestionLayout.radioAnswer8.isChecked()) {
            incrementScore();
        }
    }

    //method called to calculate 4th question
    private void question4() {
        boolean question4a = binding.fourthQuestionLayout.checkboxAnswer1.isChecked();
        boolean question4b = binding.fourthQuestionLayout.checkboxAnswer2.isChecked();
        boolean question4c = binding.fourthQuestionLayout.checkboxAnswer3.isChecked();
        boolean question4d = binding.fourthQuestionLayout.checkboxAnswer4.isChecked();

        if (question4a && question4b && question4c && !question4d) {
            binding.fourthQuestionLayout.checkboxAnswer4.setEnabled(false);
            binding.fourthQuestionLayout.checkboxAnswer2.setEnabled(false);
            binding.fourthQuestionLayout.checkboxAnswer1.setEnabled(false);
            binding.fourthQuestionLayout.checkboxAnswer3.setEnabled(false);
            incrementScore();
        } else if (!question4a && !question4b && !question4c && !question4d) {

            // in case no Check buttons are checked
            score = 0;
            binding.scoreShareLayout.scoreButton.setEnabled(true);
            Toast.makeText(this, R.string.toast_message_complete_4, Toast.LENGTH_SHORT).show();
        }
    }

    //method to share score by email or other social media on user's phone
    public void onSharing(View view) {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.intent_message_subject));
        sendIntent.putExtra(Intent.EXTRA_TEXT, name + getString(R.string.sharing_message) + score + " " + getString(R.string.total_score));
        sendIntent.setType("text/plain");

        // credit https://stackoverflow.com/a/64945724/8899344
        try {
            startActivity(sendIntent);
        } catch (ActivityNotFoundException exception) {
            Toast.makeText(this, "Activity not found!", Toast.LENGTH_SHORT).show();
            Log.d(TAG, "onSharing: }" + exception);
        }
    }

    //method to exit application
    public void onExit(View view) {
        finish();
    }
}
