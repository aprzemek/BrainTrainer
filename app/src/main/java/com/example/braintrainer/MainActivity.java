package com.example.braintrainer;

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
    TextView sumTextView;
    TextView resultTextView;
    TextView scoreTextView;
    TextView timerTextView;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    Button playAgainButton;
    ConstraintLayout gameLayout;



    ArrayList<Integer> answers = new ArrayList<>();
    int locationOfCorrectAnswer;
    int score = 0;
    int numberOfQuestions = 0;

    public void start(View view){
        goButton.setVisibility(View.INVISIBLE);
        gameLayout.setVisibility(View.VISIBLE);
        playAgain(findViewById(R.id.timerTextView));

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        goButton = findViewById(R.id.goButton);
        button0 = findViewById(R.id.button0);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        sumTextView = findViewById(R.id.sumTextView);
        resultTextView = findViewById(R.id.resultTextView);
        scoreTextView = findViewById(R.id.scoreTextView);
        timerTextView = findViewById(R.id.timerTextView);
        playAgainButton = findViewById(R.id.playAgainButton);
        gameLayout = findViewById(R.id.gameLayout);

        newQuestion();



}
        public void chooseAnswer(View view) {
            if (Integer.toString(locationOfCorrectAnswer).equals(view.getTag().toString())) {
                resultTextView.setText("Dobrze");
                score++;
            } else {

                resultTextView.setText("Źle");


            }
            numberOfQuestions++;
            scoreTextView.setText(Integer.toString(score)+"/"+ Integer.toString(numberOfQuestions));
            newQuestion();
        }
        public void newQuestion(){
            Random random = new Random();
            int a = random.nextInt(21);
            int b = random.nextInt(21);
            sumTextView.setText(Integer.toString(a)+" + "+ Integer.toString(b));


            locationOfCorrectAnswer = random.nextInt(4);

            answers.clear();
            for(int i = 0; i<4; i++){
                if(i ==locationOfCorrectAnswer){
                    answers.add(a+b);
                } else {
                    int wrongAnswer =random.nextInt(41);
                    while(wrongAnswer == a+b){
                        wrongAnswer =random.nextInt(41);
                    }
                    answers.add(wrongAnswer);
                }
            }
            button0.setText(Integer.toString(answers.get(0)));
            button1.setText(Integer.toString(answers.get(1)));
            button2.setText(Integer.toString(answers.get(2)));
            button3.setText(Integer.toString(answers.get(3)));

        }
        public void playAgain(View view){
        score = 0;
        numberOfQuestions = 0;
        timerTextView.setText("30s");
            new CountDownTimer(30000,1000){

                @Override
                public void onTick(long millisUntilFinished) {
                    timerTextView.setText(String.valueOf(millisUntilFinished/1000) + "s");
                }

                @Override
                public void onFinish() {
                    resultTextView.setText("Koniec!");
                    playAgainButton.setVisibility(View.VISIBLE);
                }
            }.start();
        }
}