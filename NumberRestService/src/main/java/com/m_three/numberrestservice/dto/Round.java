/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.m_three.numberrestservice.dto;

import java.time.LocalDateTime;

/**
 *
 * @author Brian
 */
public class Round {

    private int roundID;
    private String roundDateTime;
    private int gameID;
    private String guess;

    public int getRoundID() {
        return roundID;
    }

    public void setRoundID(int roundID) {
        this.roundID = roundID;
    }

    public String getRoundDateTime() {
        return roundDateTime;
    }

    public void setRoundDateTime(String roundDateTime) {
        this.roundDateTime = roundDateTime;
    }

    public int getGameID() {
        return gameID;
    }

    public void setGameID(int gameID) {
        this.gameID = gameID;
    }

    public String getGuess() {
        return guess;
    }

    public void setGuess(String guess) {
        this.guess = guess;
    }

}
