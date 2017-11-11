package com.example.admin.rgsteam2ideahacks;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MathQActivity extends AppCompatActivity {
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
    public static String[] agbQuestions = {};
    public static String[] agbChoices = {};
    public static String[] agbAns = {};
    public static String[] cbQuestions = {};
    public static String[] cbChoices = {};
    public static String[] cbAns = {};
    public static String[] geoQuestions = {};
    public static String[] geoChoices = {};
    public static String[] geoAns = {};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_math_q);
        String topic = getIntent().getStringExtra("TOPIC");
        String[] questions, choices, ans;
        if (topic.contentEquals("nt")){
            questions = ntQuestions;
            choices = ntChoices;
            ans = ntAns;
        }else if(topic.contentEquals("agb")){
            questions = agbQuestions;
            choices = agbChoices;
            ans = agbAns;
        }else if(topic.contentEquals("combi")){
            questions = cbQuestions;
            choices = cbChoices;
            ans = cbAns;
        }else if(topic.contentEquals("geo")){
            questions = geoQuestions;
            choices = geoChoices;
            ans = geoAns;
        }
    }
}
