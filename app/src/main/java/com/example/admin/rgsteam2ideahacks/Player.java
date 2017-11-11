package com.example.admin.rgsteam2ideahacks;

public class Player {
    private int psiDollars;
    private String username;
    private int level;

    public Player(String username, int psiDollars, int level){
        this.username = username;
        this.psiDollars = psiDollars;
        this.level = level;
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

    /*public String generateUsername(){

    }*/

    /***************************************
     * Manipulate level [START]
     ****************************************/

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
