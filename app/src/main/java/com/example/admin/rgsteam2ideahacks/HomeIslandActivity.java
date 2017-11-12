package com.example.admin.rgsteam2ideahacks;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;



public class HomeIslandActivity extends AppCompatActivity implements View.OnTouchListener {

    private TextView expMax;
    private TextView expCurrent;
    private TextView currentLevel;
    private TextView expBar;
    private TextView coinTV;
    private ImageView coinIV;

    private int count = 0,level;
    private double expLength = 0,expCurrentValue,expMaxValue = 0;
    private Player p1 = GlobalPlayer.p1;


    private static final String TAG = "Touch";
    @SuppressWarnings("unused")
    private static final float MIN_ZOOM = 1f,MAX_ZOOM = 1f;

    // These matrices will be used to scale points of the image
    Matrix matrix = new Matrix();
    Matrix savedMatrix = new Matrix();

    // The 3 states (events) which the user is trying to perform
    static final int NONE = 0;
    static final int DRAG = 1;
    static final int ZOOM = 2;
    int mode = NONE;

    // these PointF objects are used to record the point(s) the user is touching
    PointF start = new PointF();
    PointF mid = new PointF();
    float oldDist = 1f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); // Remove title
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN); // Full Screen

        setContentView(R.layout.activity_home_island);

        getSupportActionBar().hide();

        setEXPBar();

        ImageView view = (ImageView) findViewById(R.id.mainIslandIV);
        view.setOnTouchListener(this); // Setting zoom function
        matrix.postScale((float)1.5, (float)1.5, 100, 200);
        view.setImageMatrix(matrix);

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


    private void setEXPBar() {

        expCurrent = (TextView) findViewById(R.id.expCurrent);
        expMax = (TextView) findViewById(R.id.expMax);
        currentLevel = (TextView) findViewById(R.id.currentLevel);
        expBar = (TextView) findViewById(R.id.expBar);
        coinIV = (ImageView) findViewById(R.id.goldCoinIV);
        coinTV = (TextView) findViewById(R.id.coinTV);
        ImageView settingsButton = (ImageView) findViewById(R.id.settings);

        RelativeLayout rL = (RelativeLayout) findViewById(R.id.rootRL);
        rL.bringChildToFront((LinearLayout) findViewById(R.id.rootLL));
        rL.bringChildToFront(settingsButton);

        expCurrent.invalidate();
        ((View)expCurrent.getParent()).requestLayout();



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


    @Override
    public boolean onTouch(View v, MotionEvent event)
    {
        ImageView view = (ImageView) v;
        view.setScaleType(ImageView.ScaleType.MATRIX);
        float scale;

        dumpEvent(event);
        // Handle touch events here...

        switch (event.getAction() & MotionEvent.ACTION_MASK)
        {
            case MotionEvent.ACTION_DOWN:   // first finger down only
                savedMatrix.set(matrix);
                start.set(event.getX(), event.getY());
                Log.d(TAG, "mode=DRAG"); // write to LogCat
                mode = DRAG;
                break;

            case MotionEvent.ACTION_UP: // first finger lifted

            case MotionEvent.ACTION_POINTER_UP: // second finger lifted

                mode = NONE;
                Log.d(TAG, "mode=NONE");
                break;

            case MotionEvent.ACTION_POINTER_DOWN: // first and second finger down

                oldDist = spacing(event);
                Log.d(TAG, "oldDist=" + oldDist);
                if (oldDist > 5f) {
                    savedMatrix.set(matrix);
                    midPoint(mid, event);
                    mode = ZOOM;
                    Log.d(TAG, "mode=ZOOM");
                }
                break;

            case MotionEvent.ACTION_MOVE:

                if (mode == DRAG)
                {
                    matrix.set(savedMatrix);
                    matrix.postTranslate(event.getX() - start.x, event.getY() - start.y); // create the transformation in the matrix  of points
                }
                else if (mode == ZOOM)
                {
                   /* // pinch zooming
                    float newDist = spacing(event);
                    Log.d(TAG, "newDist=" + newDist);
                    if (newDist > 5f)
                    {
                        matrix.set(savedMatrix);
                        scale = newDist / oldDist; // setting the scaling of the
                        // matrix...if scale > 1 means
                        // zoom in...if scale < 1 means
                        // zoom out
                        matrix.postScale(scale, scale, mid.x, mid.y);
                    }*/

                    float[] f = new float[9];

                    float newDist = spacing(event);
                    if (newDist > 10f) {
                        matrix.set(savedMatrix);
                        float tScale = newDist / oldDist;
                        matrix.postScale(tScale, tScale, mid.x, mid.y);
                    }

                    matrix.getValues(f);
                    float scaleX = f[Matrix.MSCALE_X];
                    float scaleY = f[Matrix.MSCALE_Y];

                    if(scaleX <= 0.7f) {
                        matrix.postScale((0.7f)/scaleX, (0.7f)/scaleY, mid.x, mid.y);
                    } else if(scaleX >= 2.5f) {
                        matrix.postScale((3.0f)/scaleX, (3.0f)/scaleY, mid.x, mid.y);
                    }
                }
                break;
        }

        view.setImageMatrix(matrix); // display the transformation on screen

        return true; // indicate event was handled
    }

    private float spacing(MotionEvent event)
    {
        float x = event.getX(0) - event.getX(1);
        float y = event.getY(0) - event.getY(1);
        return (float) Math.sqrt(x * x + y * y);
    }

    private void midPoint(PointF point, MotionEvent event)
    {
        float x = event.getX(0) + event.getX(1);
        float y = event.getY(0) + event.getY(1);
        point.set(x / 2, y / 2);
    }

    private void dumpEvent(MotionEvent event)
    {
        String names[] = { "DOWN", "UP", "MOVE", "CANCEL", "OUTSIDE","POINTER_DOWN", "POINTER_UP", "7?", "8?", "9?" };
        StringBuilder sb = new StringBuilder();
        int action = event.getAction();
        int actionCode = action & MotionEvent.ACTION_MASK;
        sb.append("event ACTION_").append(names[actionCode]);

        if (actionCode == MotionEvent.ACTION_POINTER_DOWN || actionCode == MotionEvent.ACTION_POINTER_UP)
        {
            sb.append("(pid ").append(action >> MotionEvent.ACTION_POINTER_ID_SHIFT);
            sb.append(")");
        }

        sb.append("[");
        for (int i = 0; i < event.getPointerCount(); i++)
        {
            sb.append("#").append(i);
            sb.append("(pid ").append(event.getPointerId(i));
            sb.append(")=").append((int) event.getX(i));
            sb.append(",").append((int) event.getY(i));
            if (i + 1 < event.getPointerCount())
                sb.append(";");
        }

        sb.append("]");
        Log.d("Touch Events ---------", sb.toString());
    }

    @Override
    public void onBackPressed(){
        Intent intent = new Intent(HomeIslandActivity.this, MainActivity.class);
        startActivity(intent);
    }
}
