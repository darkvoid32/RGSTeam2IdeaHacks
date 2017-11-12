package com.example.admin.rgsteam2ideahacks;

import android.app.ActionBar;
import android.app.Dialog;
import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {
    private ImageView mainIV, mathIV, physicsIV;
    private HorizontalScrollView scrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); // Remove title
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN); // Full Screen
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();

        //TODO Home page with bird eye view of different islands have clouds floating around
        //TODO Main islands (Graphics)
        //TODO Main islands (Animation n Personal Character)
        //TODO Personal island
        //TODO Settings shit
        //TODO Achievement shit (Later)
        //TODO Level shit (Later)
        //TODO Finish setting settings for other activities

        setUpIslandIV();
    }

    private void setUpIslandIV() {
        mainIV = (ImageView) findViewById(R.id.mainIsland);
        mathIV = (ImageView) findViewById(R.id.sideIslandMathIV);
        physicsIV = (ImageView) findViewById(R.id.sideIslandPhysicsIV);

        scrollView = (HorizontalScrollView) findViewById(R.id.rootScrollView);

        ViewTreeObserver vto = scrollView.getViewTreeObserver(); // Setting Horizontal scrollView to goto middle (Main island)

        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            public void onGlobalLayout() {
                scrollView.scrollTo(scrollView.getChildAt(0).getWidth()/3, 0);
            }
        });



        mainIV.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                final Intent intent = new Intent (MainActivity.this, HomeIslandActivity.class);
                startActivity(intent);
            }
        });

        mathIV.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                final Intent intent = new Intent (MainActivity.this, MathIslandActivity.class);
                startActivity(intent);
            }
        });

        physicsIV.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                final Intent intent = new Intent (MainActivity.this, PhysicsIslandActivity.class);
                startActivity(intent);
            }
        });

        ImageView SettingsWheel = (ImageView) findViewById(R.id.settings);

        SettingsWheel.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                setUpSettingsDialog();
            }
        });
    }

    private void setUpSettingsDialog() {
        // custom dialog
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.settings_dialog);

        // set the custom dialog components - text, image and button
        Button aboutButton = (Button) dialog.findViewById(R.id.AboutButton);

        // if button is clicked, close the custom dialog
        aboutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }

}
