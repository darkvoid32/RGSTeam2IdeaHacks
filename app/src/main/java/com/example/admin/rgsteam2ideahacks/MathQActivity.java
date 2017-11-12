package com.example.admin.rgsteam2ideahacks;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import static java.lang.Thread.sleep;

public class MathQActivity extends AppCompatActivity {
    Player currentPlayer;
    Button btn_c1, btn_c2, btn_c3, btn_c4;
    String[] questions, choices, ans;
    TextView tv_question;
    int currentAns, inputAns, points, currentQ;
    public static String[] goodLuckMessage = {"Nice Try!", "You can do it!", "Keep going!"};

    private ImageView labTechIV;
    private boolean moveLabTech = false, gameRunning = true, goodLuckMsg = true, getNewMsg = true;
    private int labTechPosition = 0, labTechMovedAmt = 0, gLAmt = 0, i1 = 0;
    private TextView speechBubbleText;
    private ImageView textBubble;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); // Remove title
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN); // Full Screen
        setContentView(R.layout.activity_math_q);
        getSupportActionBar().hide();

        final Random r = new Random();

        btn_c1 = findViewById(R.id.btn_c1);
        btn_c2 = findViewById(R.id.btn_c2);
        btn_c3 = findViewById(R.id.btn_c3);
        btn_c4 = findViewById(R.id.btn_c4);

        labTechIV = findViewById(R.id.labTechIV);
        textBubble = findViewById(R.id.textBubble);
        speechBubbleText = findViewById(R.id.textBubbleSpeech);


        tv_question = findViewById(R.id.tv_question);
        String topic = getIntent().getStringExtra("TOPIC");

        try{
            FileInputStream inputStream = openFileInput("UserAccounts.txt");
            Scanner inputStreamScanner = new Scanner(inputStream);

            ArrayList<String> lines  = new ArrayList<String>();
            while(inputStreamScanner.hasNextLine()){
                lines.add(inputStreamScanner.nextLine());
            }

            currentPlayer = new Player(lines.get(0), Integer.parseInt(lines.get(1)), Integer.parseInt(lines.get(2)));

            // Parse physics progress into array
            Scanner physicsScanner = new Scanner(lines.get(3));
            physicsScanner.useDelimiter(",");
            ArrayList<Boolean> physicsProgress = new ArrayList<>();

            while(physicsScanner.hasNext()){
                physicsProgress.add(new Boolean(physicsScanner.next()));
            }

            currentPlayer.setPhysicsProgress(physicsProgress);
        }
        catch(IOException exception){
            Log.w("MainActivity", exception.getStackTrace().toString());
        }

        Toast.makeText(this, currentPlayer.toString(), Toast.LENGTH_LONG).show();

        Thread t = new Thread(new Runnable() {
            public void run() {
                try {
                    while (gameRunning) {
                        if (moveLabTech) {
                            if (labTechMovedAmt < 4) {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        if (getNewMsg) {
                                            i1 = r.nextInt((goodLuckMessage.length - 0) + 0);
                                            getNewMsg = false;
                                        }
                                        changeLabTechPosition();
                                        labTechMovedAmt++;
                                        textBubble.setVisibility(View.VISIBLE);
                                        speechBubbleText.setVisibility(View.VISIBLE);
                                        speechBubbleText.setText(goodLuckMessage[i1]);
                                    }
                                });
                            } else {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        textBubble.setVisibility(View.GONE);
                                        speechBubbleText.setVisibility(View.GONE);
                                        labTechMovedAmt = 0;
                                        getNewMsg = true;
                                        moveLabTech = false;
                                    }
                                });
                            }

                            if (goodLuckMsg) {
                                if (gLAmt < 5) {
                                    gLAmt++;
                                    Log.i("game running ", String.valueOf(gLAmt));
                                } else {
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            textBubble.setVisibility(View.GONE);
                                            speechBubbleText.setVisibility(View.GONE);
                                            goodLuckMsg = false;
                                        }
                                    });
                                }
                            }
                        }
                        sleep(500);
                    }
                } catch (Exception ex) {
                    Log.i("Game running", ex.toString());
                }
            }
        });

        t.start();

        if (topic.contentEquals("nt"))

        {
            questions = getResources().getStringArray(R.array.ntQuestions);
            choices = getResources().getStringArray(R.array.ntChoices);
            ans = getResources().getStringArray(R.array.ntAns);
            /*questions = ntQuestions;
            choices = ntChoices;
            ans = ntAns;*/
        } else if (topic.contentEquals("agb"))

        {
            questions = getResources().getStringArray(R.array.agbQuestions);
            choices = getResources().getStringArray(R.array.agbChoices);
            ans = getResources().getStringArray(R.array.agbAns);
            /*questions = agbQuestions;
            choices = agbChoices;
            ans = agbAns;*/
        } else if (topic.contentEquals("combi"))

        {
            questions = getResources().getStringArray(R.array.cbQuestions);
            choices = getResources().getStringArray(R.array.cbChoices);
            ans = getResources().getStringArray(R.array.cbAns);
            /*questions = cbQuestions;
            choices = cbChoices;
            ans = cbAns;*/
        } else if (topic.contentEquals("geo"))

        {
            questions = getResources().getStringArray(R.array.geoQuestions);
            choices = getResources().getStringArray(R.array.geoChoices);
            ans = getResources().getStringArray(R.array.geoAns);
            /*questions = geoQuestions;
            choices = geoChoices;
            ans = geoAns;*/
        }

        points = 0;
        currentQ = 0;

        loadQ(0);
    }

    private void changeLabTechPosition() {
        if (labTechPosition == 0) {
            labTechIV.setImageResource(R.drawable.labtech2);
            labTechPosition = 1;
        } else {
            labTechIV.setImageResource(R.drawable.labtechstationary2);
            labTechPosition = 0;
        }
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
            //Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show();

            if (currentQ + 1 < questions.length) {
                currentQ++;
            } else {
                gameRunning = false;
                Intent intent = new Intent(MathQActivity.this, MathIslandActivity.class);
                //TODO database shit
                startActivity(intent);
            }
            loadQ(currentQ);

        } else {
            //Toast.makeText(this, "Wrong", Toast.LENGTH_SHORT).show();
            moveLabTech = true;

        }
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
