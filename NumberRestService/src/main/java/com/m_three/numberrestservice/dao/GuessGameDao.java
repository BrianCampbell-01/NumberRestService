/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.m_three.numberrestservice.dao;

import com.m_three.numberrestservice.dto.Game;
import com.m_three.numberrestservice.dto.Round;
import java.util.List;

/**
 *
 * @author Brian
 */
public interface GuessGameDao {
    
    public Game addGame(Game game);
    public Game getGameByID(int id);
    public List<Game> getAllGames();
    public void gameRounds();
    public Round playGame(int id);
    public Round createRound(Round round);
    public boolean updateGame(Game game);
    public List<Round> getRoundByGameID(int id);
    
}
