package com.ambika.brainteaser;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button goButton;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    Button playAgain;
    int correctAnswerIndex;
    TextView correctTextView;
    TextView questionTextView;
    TextView scoreTextView;
    TextView timerTextView;
    ConstraintLayout gameLayout;
    ArrayList<Integer> answers= new ArrayList<Integer>();
    int score=0;
    int noOfQuestions=0;

    public void go(View view)
    {
        goButton.setVisibility(View.INVISIBLE);
        /*scoreTextView.setVisibility(View.VISIBLE);
        questionTextView.setVisibility(View.VISIBLE);
        timerTextView.setVisibility(View.VISIBLE);
        button0.setVisibility(View.VISIBLE);
        button1.setVisibility(View.VISIBLE);
        button2.setVisibility(View.VISIBLE);
        button3.setVisibility(View.VISIBLE);
        correctTextView.setVisibility(View.VISIBLE);*/
        gameLayout.setVisibility(View.VISIBLE);
        playAgain(findViewById(R.id.button4));

    }

    public void playAgain(View view)
    {
        playAgain.setVisibility(View.INVISIBLE);
        newQuestion();
        score=0;
        noOfQuestions=0;
        timerTextView.setText("30 s");
        scoreTextView.setText("0/0");
        correctTextView.setText("....");
        new CountDownTimer(30100,1000)
        {

            @Override
            public void onTick(long millisUntilFinished) {
                timerTextView.setText(millisUntilFinished/1000+" s");
            }

            @Override
            public void onFinish() {
                correctTextView.setText("TIME UP!");
                playAgain.setVisibility(View.VISIBLE);
            }
        }.start();
    }

    public void chooseAnswer(View view)
    {
        if(Integer.toString(correctAnswerIndex).equals(view.getTag().toString()))
        {
            correctTextView.setText("Correct!");
            score++;
        }
        else
        {
            correctTextView.setText("Incorrect!");
        }
        noOfQuestions++;
        scoreTextView.setText(score+"/"+noOfQuestions);
        newQuestion();
    }

    public void newQuestion()
    {
        Random rand= new Random();
        int a= rand.nextInt(21);
        int b= rand.nextInt(21);
        questionTextView.setText(a+" + "+b);
        correctAnswerIndex= rand.nextInt(4);
        answers.clear();
        for(int i=0;i<4;i++)
        {
            if(i==correctAnswerIndex)
            {
                answers.add(a+b);
            }
            else
            {
                int wrongAnswer = rand.nextInt(41);
                while(wrongAnswer == a+b)
                {
                    wrongAnswer = rand.nextInt(41);
                }
                answers.add(wrongAnswer);
            }
        }

        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        goButton=findViewById(R.id.goButton);
        correctTextView= findViewById(R.id.correcttextView);
        scoreTextView=findViewById(R.id.scoreTextView);
        timerTextView=findViewById(R.id.countDownTextView);
        button0= findViewById(R.id.button1);
        button1= findViewById(R.id.button2);
        button2= findViewById(R.id.button3);
        button3= findViewById(R.id.button4);
        gameLayout=findViewById(R.id.gameLayout);
        playAgain=findViewById(R.id.playAgainButton);
        questionTextView= findViewById(R.id.questionTextView);



    }
}
