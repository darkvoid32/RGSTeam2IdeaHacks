package com.example.admin.rgsteam2ideahacks;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

public class PhysicsIslandActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); // Remove title
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN); // Full Screen
        setContentView(R.layout.activity_physics_island);

        getSupportActionBar().hide();

    }

    public void onDoomClick(View v){
        Toast.makeText(this, "Coming soon", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onBackPressed(){
        Intent intent = new Intent(PhysicsIslandActivity.this, MainActivity.class);
        startActivity(intent);
    }

}
