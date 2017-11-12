package com.example.admin.rgsteam2ideahacks;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

public class Player {

    private int psiDollars;
    private String username;
    private int exp;

    // Progress

    // For Physics:
    // Index 0: Visited the Professor
    private ArrayList<Boolean> physicsProgress = new ArrayList<>();


    public Player(String username, int psiDollars, int exp){
        this.username = username;
        this.psiDollars = psiDollars;
        this.exp = exp;
    }

    public Player(String username, int psiDollars, int exp, ArrayList<Boolean> physicsProgress){
        this.username = username;
        this.psiDollars = psiDollars;
        this.exp = exp;
        this.physicsProgress = physicsProgress;
    }

    /***************************************
    * Manipulate psiDollars [START]
    ****************************************/

    public int getPsiDollars() {
        return psiDollars;
    }

    public void setPsiDollars(int psiDollars) {
        this.psiDollars = psiDollars;
    }

    public void addPsiDollars(int amount){
        this.psiDollars += amount;
    }

    public void deductPsiDollars(int amount){
        this.psiDollars -= amount;
    }

    /***************************************
     * Manipulate username [START]
     ****************************************/

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    // Generate username for user that does not wish to create username
    public String generateUsername(String[][] names){
        String[] firstNames = names[0];
        String[] lastNames = names[1];
        Random theRand = new Random();
        String firstName = names[0][theRand.nextInt(firstNames.length)];
        String lastName = names[0][theRand.nextInt(lastNames.length)];
        return firstName + " " + lastName;
    }

    /***************************************
     * Manipulate exp [START]
     ****************************************/

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    // Return level of Player with exp
    public int getLevel(){

        if(exp >= 4500){
            return (exp - 4500)/1000 + 10;
        }

        else{
            int i = 1;
            for(int expCopy = exp; expCopy >= 0; expCopy-=100*i++);
            return i-1;
        }
    }

    /***************************************
     * Manipulate physics progress [START]
     ****************************************/

    public boolean getPhysicsProgress(int index){
        return physicsProgress.get(index);
    }

    public void setPhysicsProgress(ArrayList<Boolean> physicsProgress) {
        this.physicsProgress = physicsProgress;
    }

    /***************************************
     * Player String functions [START]
     ****************************************/
    public String stringToWrite(){

        // Convert physics progress to string to write
        String physicsProgressString = "";
        for(int i = 0; i < physicsProgress.size(); i++){
            if(physicsProgress.get(i)){
                    physicsProgressString += "true,";
            }
            else{
                physicsProgressString += "false,";
            }
        }
        physicsProgressString = physicsProgressString.substring(0, physicsProgressString.length() - 1);

        return username + "\n" + psiDollars + "\n" + exp + "\n" + physicsProgressString;

    }

    public String toString(){
        return "Name: " + username +
               "Psi Dollars: " + psiDollars +
               "EXP: " + exp;
    }
}
