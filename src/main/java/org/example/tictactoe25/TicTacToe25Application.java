package org.example.tictactoe25;

import org.example.tictactoe25.controllers.GameController;
import org.example.tictactoe25.models.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class TicTacToe25Application {

    public static void main(String[] args) {
        SpringApplication.run(TicTacToe25Application.class, args);

        System.out.println("Welcome to TicTacToe - 2025");
        //Client needs to know the internal of system
        Scanner in = new Scanner(System.in);
        System.out.println("What should be the dimension of Game");
        int dimension = in.nextInt();

        System.out.println("Will there be any bot in the game? y/n");
        String isBot = in.next();

        //The number of player
        int toIterate = dimension - 1;

        if(isBot.equals("y")){
            toIterate = dimension - 2;
        }
        List<Player> playerList = new ArrayList<>();

        for(int i=0;i<toIterate;i++){
            System.out.println("What is the name of player : " + i + 1);
            String playerName = in.next();


            System.out.println("What is the name of symbol : "+i+1);
            String playerSymbol = in.next();

            playerList.add(new Player(playerName,new Symbol(playerSymbol.charAt(0))));

        }

        if(isBot.equals("y")){
            System.out.println("What is the name of BOT player : ");
            String playerName = in.next();

            System.out.println("What is the symbol of Bot ?");
            String playerSymbol = in.next();

            playerList.add(new Bot(playerName,new Symbol(playerSymbol.charAt(0)), BotDifficultyLevel.EASY));

        }


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

            System.out.println("Does anyone want to undo? y/n");
            String input = in.next();
            if(input.equals("y")){
                gameController.undo(game);
            }else{
                //makeMove
                gameController.makeMove(game);
            }
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