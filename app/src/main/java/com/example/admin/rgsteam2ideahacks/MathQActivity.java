package com.example.admin.rgsteam2ideahacks;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MathQActivity extends AppCompatActivity {
    public static String[] questions = {"Which of the following is not a prime number?", "Which of the following is a prime number?"
            , "What is the largest prime factor of 12345?", "Which of the following is not the factor of 142857?"};
    public static String[] choices = {"A) 71xxxB) 73xxxC) 77xxxD) 79", "A) 91xxxB) 93xxxC) 97xxxD) 99", "A) 3xxxB) 5xxxC) 823xxxD) 12345"
            , "A) 3xxxB) 11xxxC) 13xxxD) 47"};
    public static String[] ans = {"C) 77", "C) 97", "C) 823", "D) 47"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_math_q);
    }
}
