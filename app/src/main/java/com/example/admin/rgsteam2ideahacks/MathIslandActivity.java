package com.example.admin.rgsteam2ideahacks;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

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

        ImageView SettingsWheel = (ImageView) findViewById(R.id.settings);

        SettingsWheel.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                setUpSettingsDialog();
            }
        });
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
