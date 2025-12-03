package org.example.tictactoe25;

import org.example.tictactoe25.controllers.GameController;
import org.example.tictactoe25.models.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class TicTacToe25Application {

    public static void main(String[] args) {
        SpringApplication.run(TicTacToe25Application.class, args);

        System.out.println("Welcome to TicTacToe - 2025");
        //Client needs to know the internal of system

        int dimension = 3;
        List<Player> playerList = new ArrayList<>();
        playerList.add(new Player("Chanchal",new Symbol('x')));
        playerList.add(new Bot("GPT Player",new Symbol('0'), BotDifficultyLevel.MEDIUM));


        /**
         * Whenever you play TicTacToe, we just say Start Game
         * Game should understand that it needs to build a board
         *
         * Then player will make move and see it on Board
         * displayWinner
         * checkGameStatus
         * undoYourLastMove
         * checkWinner
         */
        GameController gameController = new GameController();
        Game game = gameController.startGame(playerList, dimension);

       //Make a move if game is in progress state
        while(gameController.getGameState(game) == GameState.IN_PROGESS){

            //Display the board
            gameController.displayBoard(game);

            //makeMove
            gameController.makeMove(game);
        }

        //Check if win or draw
        //if win -> display winner or
        if(gameController.getGameState(game) == GameState.ENDED){
            gameController.displayBoard(game);
            System.out.println("Game has ended");
            Player winner = game.getWinner();
            System.out.println(winner.getName());
        }else{
            System.out.println("It's draw babe. No on one");
        }

    }

}
//MVC Patterm
// Controller - Service _ Database
// In Tic Tac Toe , we don't have Database => so, we don't need to work on repo part
// but still we can have
// GameController - to c