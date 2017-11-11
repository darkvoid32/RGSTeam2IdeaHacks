package com.example.admin.rgsteam2ideahacks;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;


/**
 * Created by Zhuo Quan on 11/11/2017.
 */

public class HomeIslandActivity extends AppCompatActivity {

    private TextView expMax;
    private TextView expCurrent;
    private TextView currentLevel;
    private int count = 0,level;
    private double expLength = 0,expCurrentValue,expMaxValue = 0;
    private Player p1 = GlobalPlayer.p1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); // Remove title
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN); // Full Screen

        setContentView(R.layout.activity_home_island);

        getSupportActionBar().hide();

        expCurrent = (TextView) findViewById(R.id.expCurrent);
        expMax = (TextView) findViewById(R.id.expMax);
        currentLevel = (TextView) findViewById(R.id.currentLevel);

        //Get the level of the player

        level = p1.getLevel();

        String levelString = "" + level;
        currentLevel.setText(levelString);

        //Get the exp of the player
        expCurrentValue = p1.getExp();
        Log.i("data", String.valueOf(expCurrentValue));

        //Get max exp for level
        if (level < 10){
            int i = level;
            while (i != 0){
                count++;
                expMaxValue += count * 100;
                i--;
            }
            double levelValue = count * 100;
            expLength = ((levelValue - (expMaxValue - expCurrentValue)) / levelValue * 150);
            expMax.setText(String.valueOf((int)(levelValue - (expMaxValue - expCurrentValue)) + "/" + (int)(levelValue)));
        }

        else{
            expMaxValue = (level - 9) * 1000 + 4500;
            expLength = ((1000 - (expMaxValue - expCurrentValue)) / 1000) * 150;
            expMax.setText(String.valueOf((int)(1000 - (expMaxValue - expCurrentValue)) + "/1000" ));
        }

        final float scale = getResources().getDisplayMetrics().density;
        expCurrent.setWidth((int)(expLength * scale));

    }


}
