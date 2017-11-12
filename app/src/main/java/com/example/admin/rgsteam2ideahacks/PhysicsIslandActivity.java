package com.example.admin.rgsteam2ideahacks;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
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

    public void onTennisClick(View v){
        intent.putExtra("TOPIC", "k");
        startActivity(intent);
    }

    public void onProfessorClick(View v){
        intent.putExtra("TOPIC", "m");
        startActivity(intent);
    }

    public void onDoomClick(View v){
        Toast.makeText(this, "Coming soon!", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(PhysicsIslandActivity.this, MainActivity.class);
        startActivity(intent);
    }

}
