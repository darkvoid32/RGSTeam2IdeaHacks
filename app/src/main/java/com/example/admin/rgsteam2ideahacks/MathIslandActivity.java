package com.example.admin.rgsteam2ideahacks;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Gnoh Cheng Yi on 11/11/2017.
 */

public class MathIslandActivity extends AppCompatActivity{
    Player player;
    Button btn_algebra, btn_nt, btn_combi, btn_geo;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); // Remove title
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN); // Full Screen
        setContentView(R.layout.activity_math_island);

        getSupportActionBar().hide();

        btn_algebra = findViewById(R.id.btn_algebra);
        btn_combi = findViewById(R.id.btn_combi);
        btn_geo = findViewById(R.id.btn_geo);
        btn_nt = findViewById(R.id.btn_nt);
        intent = new Intent(getApplicationContext(), DescriptionActivity.class);

        try{
            FileInputStream inputStream = openFileInput("UserAccounts.txt");
            Scanner inputStreamScanner = new Scanner(inputStream);

            ArrayList<String> lines  = new ArrayList<String>();
            while(inputStreamScanner.hasNextLine()){
                lines.add(inputStreamScanner.nextLine());
            }

            player = new Player(lines.get(0), Integer.parseInt(lines.get(1)), Integer.parseInt(lines.get(2)));

            // Parse physics progress into array
            Scanner physicsScanner = new Scanner(lines.get(3));
            physicsScanner.useDelimiter(",");
            ArrayList<Boolean> physicsProgress = new ArrayList<>();

            while(physicsScanner.hasNext()){
                physicsProgress.add(new Boolean(physicsScanner.next()));
            }

            player.setPhysicsProgress(physicsProgress);
        }
        catch(IOException exception){
            Log.w("MainActivity", exception.getStackTrace().toString());
        }

        Toast.makeText(this, player.toString(), Toast.LENGTH_LONG).show();

    }

    public void onAlgebraClick(View v){
        intent.putExtra("TOPIC", "agb");
        startActivity(intent);
    }

    public void onCombiClick(View v){
        intent.putExtra("TOPIC", "combi");
        startActivity(intent);
    }

    public void onGeoClick(View v){
        intent.putExtra("TOPIC", "geo");
        startActivity(intent);
    }

    public void onNTClick(View v){
        intent.putExtra("TOPIC", "nt");
        startActivity(intent);
    }

    @Override
    public void onBackPressed(){
        Intent intent = new Intent(MathIslandActivity.this, MainActivity.class);
        startActivity(intent);
    }

}
