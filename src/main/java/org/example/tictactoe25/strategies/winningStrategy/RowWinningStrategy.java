package org.example.tictactoe25.strategies.winningStrategy;

import org.example.tictactoe25.models.Board;
import org.example.tictactoe25.models.Move;
import org.example.tictactoe25.models.Symbol;

import java.util.HashMap;
import java.util.Map;

public class RowWinningStrategy implements WinningStrategy{
    /**This is a map which stores the symbol and count mapping of symbols for all Rowumns
     * Row0 => x 1
     * Row0 => 0 2
     * Row1 => x 3
     * Row1 => 0 1
     * Row2 => x 2
     * Row3 => 0 1
     */
    private Map<Integer, Map<Symbol,Integer>> rowMap = new HashMap<Integer,Map<Symbol,Integer>>();

    @Override
    public boolean checkWinner(Move move, Board board) {
        //Last move of player
        int row = move.getCell().getRow();
        Symbol playerSymbol = move.getPlayer().getSymbol();

        if(!rowMap.containsKey(row)){
            //This is the first move in this Rowumn
            rowMap.put(row,new HashMap<>());
        }
        Map<Symbol, Integer> curRowCountMap = rowMap.get(row);
        if(!curRowCountMap.containsKey(playerSymbol)){
            curRowCountMap.put(playerSymbol,0);
        }
        curRowCountMap.put(playerSymbol,curRowCountMap.get(playerSymbol)+1);

        return curRowCountMap.get(playerSymbol)==board.getDimension();
    }
}