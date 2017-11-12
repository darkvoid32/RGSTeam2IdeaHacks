package com.example.admin.rgsteam2ideahacks;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class LauncherActivity extends AppCompatActivity {

    private EditText usernameET;
    private final static String TAG = "LauncherActivity";
    private final String EXTRA_MESSAGE = "com.example.admin.rgsteam2ideahacks.username";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try{
            FileInputStream inputStream = openFileInput("PlayerData.txt");
            Scanner inputStreamScanner = new Scanner(inputStream);
            String lineNow = inputStreamScanner.nextLine();

            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra(EXTRA_MESSAGE, lineNow);
            startActivity(intent);
        }

        catch(IOException exception){
            setContentView(R.layout.activity_launcher);
            usernameET = findViewById(R.id.username_edit_text);
        }

    }

    public void createAccount(View view){
        String username = "";
        if(usernameET!=null) {
            username = usernameET.getText().toString();
        }

        // Checks if EditText is empty
        if (username.equals("")){
            Toast warningToast = Toast.makeText(this, "Username field is empty", Toast.LENGTH_LONG);
            warningToast.show();
        }

        // If not empty, write account to file
        else{
            Player newPlayer = new Player(username, 0, 0);
            ArrayList<Boolean> physicsProgress = new ArrayList<>();
            physicsProgress.add(false);
            newPlayer.setPhysicsProgress(physicsProgress);
            writeAccount(newPlayer);
        }
    }

    public void writeAccount(Player player){
        try{
            FileOutputStream outputStream = openFileOutput("PlayerData.txt", MODE_PRIVATE);
            OutputStreamWriter outputWriter = new OutputStreamWriter(outputStream);
            outputWriter.write(player.stringToWrite());
            outputWriter.flush();
            outputWriter.close();
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra(EXTRA_MESSAGE, player.getUsername());
            startActivity(intent);
        }
        catch(IOException exception){
            Log.w(TAG, exception.getStackTrace().toString());
        }
    }

}
