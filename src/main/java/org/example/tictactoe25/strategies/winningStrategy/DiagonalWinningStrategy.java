package org.example.tictactoe25.strategies.winningStrategy;

import org.example.tictactoe25.models.Board;
import org.example.tictactoe25.models.Move;
import org.example.tictactoe25.models.Symbol;

import java.util.HashMap;
import java.util.Map;

public class DiagonalWinningStrategy implements WinningStrategy{

    private Map<Symbol,Integer> leftDiagonalMap = new HashMap<Symbol,Integer>();
    private Map<Symbol,Integer> rightDiagonalMap = new HashMap<Symbol,Integer>();


    @Override
    public boolean checkWinner(Move move, Board board) {
        int row = move.getCell().getRow();
        int col = move.getCell().getCol();

        Symbol symbol = move.getPlayer().getSymbol();
        //LeftDiagonal
        if(row == col){
            if(!leftDiagonalMap.containsKey(symbol)){
                leftDiagonalMap.put(symbol,0);
            }
            leftDiagonalMap.put(symbol,leftDiagonalMap.get(symbol)+1);
            if(leftDiagonalMap.get(symbol)== board.getDimension()){
                return true;
            }
        }

        //RightDiagonal
        if(row + col == board.getDimension() -1){
            if(!rightDiagonalMap.containsKey(symbol)){
                rightDiagonalMap.put(symbol,0);
            }
            rightDiagonalMap.put(symbol,rightDiagonalMap.get(symbol)+1);

        }

        return rightDiagonalMap.get(symbol)== board.getDimension();
    }
}