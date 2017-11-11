package com.example.admin.rgsteam2ideahacks;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by Gnoh Cheng Yi on 11/11/2017.
 */

public class MathIslandActivity extends AppCompatActivity{
    Button btn_algebra, btn_nt, btn_combi, btn_geo;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_math_island);
        btn_algebra = findViewById(R.id.btn_algebra);
        btn_combi = findViewById(R.id.btn_combi);
        btn_geo = findViewById(R.id.btn_geo);
        btn_nt = findViewById(R.id.btn_nt);
        intent = new Intent(getApplicationContext(), MathQActivity.class);
    }

    private void onAlgebraClick(View v){
        intent.putExtra("TOPIC", "agb");
        startActivity(intent);
    }

    private void onCombiClick(View v){
        intent.putExtra("TOPIC", "combi");
        startActivity(intent);
    }

    private void onGeoClick(View v){
        intent.putExtra("TOPIC", "geo");
        startActivity(intent);
    }

    private void onNTclick(View v){
        intent.putExtra("TOPIC", "nt");
        startActivity(intent);
    }

}
