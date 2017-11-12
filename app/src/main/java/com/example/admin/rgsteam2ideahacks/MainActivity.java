package com.example.admin.rgsteam2ideahacks;

import android.app.Dialog;
import android.content.Intent;
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
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {
    private ImageView mainIV, mathIV, physicsIV;
    private HorizontalScrollView scrollView;
    private Player currentPlayer;

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

        try{
            FileInputStream inputStream = openFileInput("PlayerData.txt");
            Scanner inputStreamScanner = new Scanner(inputStream);

            ArrayList<String> lines  = new ArrayList<String>();
            while(inputStreamScanner.hasNextLine()){
                lines.add(inputStreamScanner.nextLine());
            }

            Log.w("MainActivity", lines.toString());

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
