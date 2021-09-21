/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.m_three.numberrestservice.dto;

import java.util.ArrayList;

/**
 *
 * @author Brian
 */
public class Game {
   private int gameID;
   private String gameStatus;
   private int guessNumber;
   private int gameNumber;
   private ArrayList<String> timePlayed;

    public int getGameNumber() {
        return gameNumber;
    }

    public void setGameNumber(int gameNumber) {
        this.gameNumber = gameNumber;
    }

    public int getGameID() {
        return gameID;
    }

    public void setGameID(int gameID) {
        this.gameID = gameID;
    }

    public String getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(String gameStatus) {
        this.gameStatus = gameStatus;
    }

    public int getGuessNumber() {
        return guessNumber;
    }

    public void setGuessNumber(int guessNumber) {
        this.guessNumber = guessNumber;
    }

    public ArrayList<String> getTimePlayed() {
        return timePlayed;
    }

    public void setTimePlayed(ArrayList<String> timePlayed) {
        this.timePlayed = timePlayed;
    }
   
}
