/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.m_three.numberrestservice.dao;

import com.m_three.numberrestservice.dto.Game;
import com.m_three.numberrestservice.dto.Round;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;

import org.springframework.stereotype.Repository;

/**
 *
 * @author Brian
 */
@Repository
public class GuessGameDaoDB implements GuessGameDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public GuessGameDaoDB(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Game addGame(Game game) {

        final String sql = "INSERT INTO GuessGame(number,gameStatus) VALUES(?,?);";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update((Connection conn) -> {

            PreparedStatement statement = conn.prepareStatement(
                    sql,
                    Statement.RETURN_GENERATED_KEYS);

            statement.setInt(1, game.getGameNumber());
            statement.setString(2, game.getGameStatus());
            return statement;

        }, keyHolder);

        game.setGameID(keyHolder.getKey().intValue());

        return game;
    }

    @Override
    public Game getGameByID(int id) {
        final String sql = "SELECT gameID , gameStatus, answer, number "
                + "FROM guessgame WHERE gameid = ?;";

        return jdbcTemplate.queryForObject(sql, new GameMapper(), id);
    }

    @Override
    public List<Game> getAllGames() {
        final String sql = "SELECT gameID , gameStatus, answer, number FROM GuessGame;";
        return jdbcTemplate.query(sql, new GameMapper());

    }

    @Override
    public void gameRounds() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Round playGame(int id) {
        // Game game = getGameByID(id);
        return new Round();
        //do calculations compare guess to answer
    }

    
    @Override
    public Round createRound(Round round) {
        final String sql = "INSERT INTO round(timePlayed,gameID, guessAnswer) VALUES(?,?,?);";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update((Connection conn) -> {

            PreparedStatement statement = conn.prepareStatement(
                    sql,
                    Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, round.getRoundDateTime());
            statement.setInt(2, round.getGameID());
            statement.setString(3, round.getGuess());
            return statement;

        }, keyHolder);

       round.setRoundID(keyHolder.getKey().intValue());

        return round;
    }

    @Override
    public boolean updateGame(Game game) {
      final String sql = "UPDATE GuessGame SET "
                + "gameStatus = ? "            
                + "WHERE Gameid = ?;";

        return jdbcTemplate.update(sql,
                game.getGameStatus(),
                game.getGameID())>0;
    }

  
    @Override
    public List<Round> getRoundByGameID(int id) {
          final String sql = "SELECT roundID , timePlayed FROM round";
                //+ " WHERE gameID = ?;";

        return jdbcTemplate.query(sql, new RoundMapper());

    }

    private static final class GameMapper implements RowMapper<Game> {

        @Override
        public Game mapRow(ResultSet rs, int index) throws SQLException {
            Game game = new Game();
            game.setGameID(rs.getInt("gameID"));
            game.setGameStatus(rs.getString("gameStatus"));
            game.setGameNumber(rs.getInt("number"));
            game.setGuessNumber(rs.getInt("answer"));

            return game;
        }
    }
     
    private static final class RoundMapper implements RowMapper<Round> {

        @Override
        public Round mapRow(ResultSet rs, int index) throws SQLException {
            Round round = new Round();
            round.setRoundID(rs.getInt("RoundId"));
            round.setRoundDateTime(rs.getString("timePlayed"));
            round.setGameID(rs.getInt("gameID"));
            round.setGuess(rs.getString("guessAnswer"));
    
            return round;
        }
    }

}
