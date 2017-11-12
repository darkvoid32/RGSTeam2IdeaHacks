package com.example.admin.rgsteam2ideahacks;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by Zhuo Quan on 12/11/2017.
 */

public class DescriptionActivity extends AppCompatActivity {

    RelativeLayout descriptionLayout;
    TextView descriptionTextView;
    Drawable background;
    String topicString, firstSentence, thirdSentence, topic;
    String fourthSentence = " Click ok to start practicing.";
    Intent intent;
    Button btn_ok;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); // Remove title
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN); // Full Screen
        setContentView(R.layout.activity_description);

        getSupportActionBar().hide();

        descriptionTextView = (TextView) findViewById(R.id.descriptionText);
        descriptionLayout = (RelativeLayout) findViewById(R.id.descriptionBackground);
        btn_ok = findViewById(R.id.okButton);
        Resources res = getResources();

        topic = getIntent().getStringExtra("TOPIC");
        if (topic.contentEquals("agb")) {
            background = res.getDrawable(R.drawable.tower);
            topicString = "Algebra";
            firstSentence = "Here is the Algebra Tower. It is the tallest building ever built!";
        }

        else if (topic.contentEquals("nt")) {
            background = res.getDrawable(R.drawable.park);
            topicString = "Number Theory";
            firstSentence = "Here is the Number Theory Park. It is one of the most popular parks around!";
        }


        else if (topic.contentEquals("combi")) {

        }

        else if (topic.contentEquals("geo")) {

        }

        thirdSentence = " You can practice your " + topicString + " skills here with some simple " + topicString + " questions.";
        descriptionTextView.setText(firstSentence + thirdSentence + fourthSentence);
        descriptionLayout.setBackground(background);
        intent = new Intent(getApplicationContext(), MathQActivity.class);
    }

    public void onOkClick(View view){
        intent.putExtra("TOPIC", topic);
        startActivity(intent);
    }
}
