package org.example.tictactoe25.models;

import java.util.List;
import java.util.Scanner;

public class Player {
    private String name;
    private Symbol symbol;
    private PlayerType playerType;
    private Scanner scanner = new Scanner(System.in);

    public Player() {
    }

    public Player(String name, Symbol symbol) {
        this.name = name;
        this.symbol = symbol;
        this.playerType = PlayerType.HUMAN;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Symbol getSymbol() {
        return symbol;
    }

    public void setSymbol(Symbol symbol) {
        this.symbol = symbol;
    }

    public PlayerType getPlayerType() {
        return playerType;
    }

    public void setPlayerType(PlayerType playerType) {
        this.playerType = playerType;
    }

    public Move makeMove(Board board) {
        //No need to create scanner
        System.out.println("PLease enter row number :");
        int row = scanner.nextInt();

        System.out.println("PLease enter column number :");
        int col = scanner.nextInt();

        Cell cell = board.getBoard().get(row).get(col);

        return new Move(cell,this);
    }
}
