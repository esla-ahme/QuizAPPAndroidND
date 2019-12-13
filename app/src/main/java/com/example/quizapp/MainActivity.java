package com.example.quizapp;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private ScrollView scrollView;
    private EditText a1;
    private EditText a3;
    String question1;
    String question3;
    private CheckBox dog;
    private CheckBox cat;
    private CheckBox apple;
    private RadioGroup radioGroup;
    TextView timer;
    CountDownTimer countDownTimer;
    private Button button;

    int score = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        scrollView = findViewById(R.id.scroll);

        a1 = findViewById(R.id.a1);

        radioGroup = findViewById(R.id.radioGroup);

        a3 = findViewById(R.id.a3);

        dog = findViewById(R.id.a43);
        apple = findViewById(R.id.apple);
        cat = findViewById(R.id.a41);

        timer = findViewById(R.id.timer);

        countDownTimer = new CountDownTimer(60000, 1000) {

            @SuppressLint("SetTextI18n")
            public void onTick(long millisUntilFinished) {
                timer.setText("seconds remaining: " + millisUntilFinished / 1000);
            }

            @SuppressLint("SetTextI18n")
            public void onFinish() {
                timer.setText("done!");
                checkAll();
            }

        };

        countDownTimer.start();
        button = findViewById(R.id.button);

    }



    public void submit(View view) {

        checkAll();
        countDownTimer.cancel();
    }
    private void checkAll(){
        scrollView.fullScroll(ScrollView.FOCUS_UP);
        checkQ1();
        checkQ2();
        checkQ3();
        checkQ4();

        updateScore();
        Toast.makeText(getApplicationContext(),"You Scored "+ score + " of 4",Toast.LENGTH_LONG).show();
        score = 0;

        a1.setEnabled(false);
        a3.setEnabled(false);
        dog.setEnabled(false);
        cat.setEnabled(false);
        apple.setEnabled(false);
        radioGroup.getChildAt(0).setEnabled(false);
        radioGroup.getChildAt(1).setEnabled(false);

        button.setEnabled(false);
    }
    private void resetAll(){
        scrollView.fullScroll(ScrollView.FOCUS_UP);
        score = 0;
        updateScore();
        countDownTimer.start();
        TextView scoreView = findViewById(R.id.score);
        scoreView.setTextColor(Color.BLACK);
        a1.setTextColor(Color.BLACK);
        a1.setText("");
        a1.setEnabled(true);

        a3.setTextColor(Color.BLACK);
        a3.setText("");
        a3.setEnabled(true);

        RadioButton radioButton1 = (RadioButton) radioGroup.getChildAt(0);
        radioButton1.setTextColor(Color.BLACK);
        radioButton1.setChecked(true);
        radioButton1.setEnabled(true);
        RadioButton radioButton2 = (RadioButton) radioGroup.getChildAt(1);
        radioButton2.setTextColor(Color.BLACK);
        radioButton2.setChecked(false);
        radioButton2.setEnabled(true);

        dog.setEnabled(true);
        cat.setEnabled(true);
        apple.setEnabled(true);

        dog.setTextColor(Color.BLACK);
        dog.setChecked(true);
        cat.setTextColor(Color.BLACK);
        cat.setChecked(false);
        apple.setTextColor(Color.BLACK);
        apple.setChecked(false);

        button.setEnabled(true);
    }

    private void checkQ1(){
        question1 = a1.getText().toString();

        if (question1.toLowerCase().equals("cold"))
        {
            score++;
            a1.setTextColor(Color.parseColor("#32CD32"));
        }
        else
        {
            a1.setTextColor(Color.RED);
        }

    }

   private void checkQ2(){
       RadioButton selectedRadioButton = findViewById(radioGroup.getCheckedRadioButtonId());
       String answer = selectedRadioButton.getText().toString();
       if (answer.toLowerCase().equals("true"))
       {
           score++;
           selectedRadioButton.setTextColor(Color.parseColor("#32CD32"));
       }
       else
       {
           selectedRadioButton.setTextColor(Color.RED);

       }

   }

    private void checkQ3(){
        question3 = a3.getText().toString();
        if (question3.toLowerCase().equals("eggs"))
        {
            score++;
            a3.setTextColor(Color.parseColor("#32CD32"));
        }
        else
        {
            a3.setTextColor(Color.RED);

        }

    }
    private void checkQ4(){


        if (dog.isChecked() && cat.isChecked() && !apple.isChecked())
        {
            score++;
            dog.setTextColor(Color.parseColor("#32CD32"));
            cat.setTextColor(Color.parseColor("#32CD32"));
        }
        else
        {
            dog.setTextColor(Color.RED);
            cat.setTextColor(Color.RED);
            apple.setTextColor(Color.RED);
        }
    }

    @SuppressLint("SetTextI18n")
    private void updateScore(){
        TextView scoreView = findViewById(R.id.score);
        scoreView.setText("You scored " + score + " of 4");
         if (score < 2 ){
            scoreView.setTextColor(Color.RED);
         }
         else {scoreView.setTextColor(Color.parseColor("#32CD32")); }

    }

    public void reset(View view) {
        resetAll();
    }
}
