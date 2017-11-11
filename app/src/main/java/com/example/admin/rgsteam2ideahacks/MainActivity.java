package com.example.admin.rgsteam2ideahacks;

import android.app.ActionBar;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
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
    }

}
