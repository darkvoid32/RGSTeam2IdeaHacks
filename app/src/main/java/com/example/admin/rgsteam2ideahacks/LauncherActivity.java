package com.example.admin.rgsteam2ideahacks;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class LauncherActivity extends AppCompatActivity {

    private EditText usernameET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);

        int READ_BLOCK_SIZE = 5;
        String EXTRA_MESSAGE = "com.example.admin.rgsteam2ideahacks.username";

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

    public void CreateAccount(){
        //String username =
    }

}
