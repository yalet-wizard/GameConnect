package com.example.braintrainerapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

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
   ArrayList<Integer> answers = new ArrayList<>();
   int locationOfAnswer;
   TextView resulttextView;
   TextView scoreTextView;
   TextView operationTextView;
   TextView timerTextView;
   int score = 0;
   int numberOfQuestions = 0;
   Button button1;
   Button button2;
   Button button3;
   Button button4;
   Button playAgainbutton;
   Button Gobutton;
   ConstraintLayout gameLayout;

   public void setPlayAgain(View view){
       score = 0;
       numberOfQuestions =0;
       timerTextView.setText("30s"); 
       resulttextView.setText("");
       scoreTextView.setText(Integer.toString(score)+ "/" + Integer.toString(numberOfQuestions));
       newQuestion();
       playAgainbutton.setVisibility(View.INVISIBLE);
       new CountDownTimer(30100, 1000){

           @Override
           public void onTick(long l) {
               timerTextView.setText(String.valueOf(l/1000) + "s");
           }

           @Override
           public void onFinish() {
               resulttextView.setText("DONEE!");
               playAgainbutton.setVisibility(View.VISIBLE);
           }
       }.start();
   }

    public void start(View view){
        Gobutton.setVisibility(View.INVISIBLE);
        gameLayout.setVisibility(View.VISIBLE);
        setPlayAgain(findViewById(R.id.timerTextView));
    }

   public void newQuestion(){
       Random rand = new Random();
       int a = rand.nextInt(21);
       int b = rand.nextInt(21);

       operationTextView.setText(Integer.toString(a) + " + "+ Integer.toString(b));

       locationOfAnswer = rand.nextInt(4);

       answers.clear();
       for(int i =0; i<4; i++){
           if(i == locationOfAnswer){
               answers.add(a+b);
           }else{
               int wrongAnswer = rand.nextInt(41);

               while(wrongAnswer == a+b){
                   wrongAnswer = rand.nextInt(41);
               }
               answers.add(wrongAnswer);
           }
       }
       button1.setText(Integer.toString(answers.get(0)));
       button2.setText(Integer.toString(answers.get(1)));
       button3.setText(Integer.toString(answers.get(2)));
       button4.setText(Integer.toString(answers.get(3)));
   }

    public void chooseAnswer(View view){
        if (Integer.toString(locationOfAnswer).equals(view.getTag().toString())){
            resulttextView.setText("CORRECT!!");
            score++;
        }else{
            resulttextView.setText("WRONG ANSWER!");
        }
        numberOfQuestions++;
        scoreTextView.setText(Integer.toString(score)+ "/" + Integer.toString(numberOfQuestions));
        newQuestion();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        operationTextView = findViewById(R.id.operationTextView);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        playAgainbutton = findViewById(R.id.playAgainbutton);
       resulttextView = findViewById(R.id.resulttextView);
       scoreTextView = findViewById(R.id.scoreTextView);
       timerTextView = findViewById(R.id.timerTextView);
        gameLayout = findViewById(R.id.gameLayout);
        Gobutton = findViewById(R.id.Gobutton);
        Gobutton.setVisibility(View.VISIBLE);
        gameLayout.setVisibility(View.INVISIBLE);


    }
}