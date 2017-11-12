package com.example.admin.rgsteam2ideahacks;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

public class PhysicsIslandActivity extends AppCompatActivity {
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); // Remove title
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN); // Full Screen
        setContentView(R.layout.activity_physics_island);

        getSupportActionBar().hide();
        intent = new Intent(getApplicationContext(), DescriptionActivity.class);
    }

    public void onHorseshoeClick(View v){
        //fluid
        intent.putExtra("TOPIC", "f");
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
    public void onBackPressed() {
        Intent intent = new Intent(PhysicsIslandActivity.this, MainActivity.class);
        startActivity(intent);
    }

}
