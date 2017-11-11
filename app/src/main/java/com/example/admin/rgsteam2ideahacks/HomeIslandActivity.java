package com.example.admin.rgsteam2ideahacks;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;


/**
 * Created by Zhuo Quan on 11/11/2017.
 */

public class HomeIslandActivity extends AppCompatActivity {

    TextView expMax;
    TextView expCurrent;
    TextView currentLevel;
    int expCurrentValue, level, count = 1, expMaxValue = 0, expLength;
    Player p1 = GlobalPlayer.p1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_island);
        expCurrent = (TextView) findViewById(R.id.expCurrent);
        expMax = (TextView) findViewById(R.id.expMax);
        currentLevel = (TextView) findViewById(R.id.currentLevel);

        //Get the level of the player

        level = p1.getLevel();
        currentLevel.setText("level");

        //Get the exp of the player
        expCurrentValue = p1.getExp();

        //Get max exp for level
        if (level < 10){
            int i = level + 1;
            while (i != 0){
                expMaxValue = expMaxValue + count * 100;
                i--;
            }
            expLength = ((expMaxValue - expCurrentValue) / (count * 100)) * 150;
        }

        else{
            expMaxValue = (level - 9) * 1000 + 4500;
            expLength = ((expMaxValue - expCurrentValue) / 1000) * 150;
        }


        expMax.setText(expMaxValue - expCurrentValue);

        final float scale = getResources().getDisplayMetrics().density;
        expCurrent.setWidth((int) (expLength * scale));
    }


}
