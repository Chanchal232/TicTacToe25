package org.example.tictactoe25.controllers;

import org.example.tictactoe25.models.Game;
import org.example.tictactoe25.models.GameState;
import org.example.tictactoe25.models.Player;
import org.example.tictactoe25.models.PlayerType;

import java.util.List;


public class GameController {

    /**
     * Start the game
     * @return
     */
    public Game startGame(List<Player> players, int dimension){
        //Can use builder design pattern and use validations here
        //Call GameBuilder to start game
        return Game.getBuilder()
                .setPlayers(players)
                .setDimension(dimension).build();
    }

    public void makeMove(Game game){

        //It is responsiblity of player to make move
        try {
            game.makeMove();
        }catch (Exception e){

        }
    }

    public GameState getGameState(Game game){
        return game.getGameState();
    }

    public void displayBoard(Game game){
        game.displayBoard();
    }

    public Player getWinner(Game game){
        return game.getWinner();
    }

    public void undo(Game game) {
        //Check if we can undo
        game.undo();
    }
}
