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
import java.util.Scanner;

public class LauncherActivity extends AppCompatActivity {

    private EditText usernameET;
    private final static String TAG = "LauncherActivity";
    private final String EXTRA_MESSAGE = "com.example.admin.rgsteam2ideahacks.username";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int READ_BLOCK_SIZE = 5;

        try{
            FileInputStream inputStream = openFileInput("PlayerData.txt");
            Scanner inputStreamScanner = new Scanner(inputStream);
            String lineNow = "";

            while(inputStreamScanner.hasNextLine()){
                lineNow = inputStreamScanner.nextLine();
            }

            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra(EXTRA_MESSAGE, lineNow);
            startActivity(intent);
        }

        catch(IOException exception){
            usernameET = (EditText) findViewById(R.id.username_edit_text);
            setContentView(R.layout.activity_launcher);
        }

    }

    public void createAccount(View view){
        String username = usernameET.getText().toString();

        // Checks if EditText is empty
        if (username.equals("")){
            Toast warningToast = Toast.makeText(this, "Username field is empty", Toast.LENGTH_LONG);
            warningToast.show();
        }

        // If not empty, write account to file
        else{
            Player newPlayer = new Player(username, 0, 0);
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
