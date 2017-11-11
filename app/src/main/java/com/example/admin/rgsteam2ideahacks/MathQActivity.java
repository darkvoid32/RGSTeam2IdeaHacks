package com.example.admin.rgsteam2ideahacks;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MathQActivity extends AppCompatActivity {
    Player player;
    Button btn_c1, btn_c2, btn_c3, btn_c4;
    String[] questions, choices, ans;
    TextView tv_question;
    int currentAns, inputAns, points, currentQ;
    public static String[] ntQuestions = {"Which of the following is not a prime number?"
            , "Which of the following is a prime number?"
            , "What is the largest prime factor of 12345?"
            , "Which of the following is not the factor of 142857?"
            , "Which of the following is the closest to 0.5?"
            , "Which of the following is equal to 1/4?"
            , "Which of the following is an improper fraction?"
            , "What is the remainder when 47 is divided by 6?"
            , "What is the remainder when 5^6 = 5x5x5x5x5x5 is divided by 4?"
            , "Given a = 9^10 = 9x9x9x9x9x9x9x9x9x9. Let b be the sum of digits of a. Let c be the sum of digits of b. What is c?"};
    public static String[] ntChoices = {"A) 71xxxB) 73xxxC) 77xxxD) 79", "A) 91xxxB) 93xxxC) 97xxxD) 99"
            , "A) 3xxxB) 5xxxC) 823xxxD) 12345", "A) 3xxxB) 11xxxC) 13xxxD) 47"
            , "A) 1/3xxxB) 2/3xxxC) 3/5xxxD) 4/7", "A) 3/12xxxB) 0.24xxxC) 1 - 0.7xxxD) 4/7"
            , "A) 2/3xxxB) 0.5xxxC) 1xxxD) 4/3", "A) 2xxxB) 5xxxC) 4xxxD) 3"
            , "A) 1xxxB) 2xxxC) 3xxxD) 4", "A) 1xxxB) 4xxxC) 8xxxD) 9"};
    public static String[] ntAns = {"C) 77", "C) 97", "C) 823", "D) 47", "D) 4/7"
            , "A) 3/12", "D) 4/3", "B) 5", "A) 1", "D) 9"};
    public static String[] agbQuestions = {"agbq1", "agbq2"};
    public static String[] agbChoices = {"agbc1xxxc2xxxc3xxxx4", "agbc5xxxc6xxxc7xxxc8"};
    public static String[] agbAns = {"agba1", "agba2"};
    public static String[] cbQuestions = {"cbq1", "cbq2"};
    public static String[] cbChoices = {"cbc1xxxc2xxxc3xxxx4", "cbc5xxxc6xxxc7xxxc8"};
    public static String[] cbAns = {"cba1", "cba2"};
    public static String[] geoQuestions = {"geoq1", "geoq2"};
    public static String[] geoChoices = {"geoc1xxxc2xxxc3xxxx4", "geoc5xxxc6xxxc7xxxc8"};
    public static String[] geoAns = {"geoa1", "geoa2"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_math_q);

        btn_c1 = findViewById(R.id.btn_c1);
        btn_c2 = findViewById(R.id.btn_c2);
        btn_c3 = findViewById(R.id.btn_c3);
        btn_c4 = findViewById(R.id.btn_c4);

        tv_question = findViewById(R.id.tv_question);
        String topic = getIntent().getStringExtra("TOPIC");

        if (topic.contentEquals("nt")) {
            /*questions = getResources().getStringArray(R.array.ntQuestions);
            choices = getResources().getStringArray(R.array.ntChoices);
            ans = getResources().getStringArray(R.array.ntAns);*/
        } else if (topic.contentEquals("agb")) {
            questions = getResources().getStringArray(R.array.agbQuestions);
            choices = getResources().getStringArray(R.array.agbChoices);
            ans = getResources().getStringArray(R.array.agbAns);
        } else if (topic.contentEquals("combi")) {
            questions = getResources().getStringArray(R.array.cbQuestions);
            choices = getResources().getStringArray(R.array.cbChoices);
            ans = getResources().getStringArray(R.array.cbAns);
        } else if (topic.contentEquals("geo")) {
            questions = getResources().getStringArray(R.array.geoQuestions);
            choices = getResources().getStringArray(R.array.geoChoices);
            ans = getResources().getStringArray(R.array.geoAns);
        }
        points = 0;
        currentQ = 0;
        loadQ(0);
    }

    public void loadQ(int i) {
        tv_question.setText(questions[i]);
        String[] currentChoice = choices[i].split("xxx");
        btn_c1.setText(currentChoice[0]);
        btn_c2.setText(currentChoice[1]);
        btn_c3.setText(currentChoice[2]);
        btn_c4.setText(currentChoice[3]);
        if (ans[i].contains("A)")) {
            currentAns = 0;
        } else if (ans[i].contains("B)")) {
            currentAns = 1;
        } else if (ans[i].contains("C)")) {
            currentAns = 2;
        } else if (ans[i].contains("D)")) {
            currentAns = 3;
        }
    }

    public void check() {
        if (inputAns == currentAns) {
            points += 100;
            Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Wrong", Toast.LENGTH_SHORT).show();
        }
        if (currentQ + 1 < questions.length) {
            currentQ++;
        } else {

        }
        loadQ(currentQ);
    }

    public void onClick(View v) {
        if (v.getId() == btn_c1.getId()) {
            inputAns = 0;
        } else if (v.getId() == btn_c2.getId()) {
            inputAns = 1;
        } else if (v.getId() == btn_c3.getId()) {
            inputAns = 2;
        } else if (v.getId() == btn_c4.getId()) {
            inputAns = 3;
        }
        check();
    }
}
