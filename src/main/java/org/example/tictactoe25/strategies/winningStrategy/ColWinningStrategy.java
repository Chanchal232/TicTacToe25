package org.example.tictactoe25.strategies.winningStrategy;

import org.example.tictactoe25.models.Board;
import org.example.tictactoe25.models.Move;
import org.example.tictactoe25.models.Symbol;

import java.util.HashMap;
import java.util.Map;

public class ColWinningStrategy implements WinningStrategy{

    /**This is a map which stores the symbol and count mapping of symbols for all columns
     * col0 => x 1
     * col0 => 0 2
     * col1 => x 3
     * col1 => 0 1
     * col2 => x 2
     * col3 => 0 1
     */
    private Map<Integer, Map<Symbol,Integer>> colMap = new HashMap<Integer,Map<Symbol,Integer>>();
    @Override
    public boolean checkWinner(Move move, Board board) {
        //Last move of player
        int col = move.getCell().getCol();
        Symbol playerSymbol = move.getPlayer().getSymbol();

        if(!colMap.containsKey(col)){
            //This is the first move in this column
            colMap.put(col,new HashMap<>());
        }
        Map<Symbol, Integer> curColCountMap = colMap.get(col);
        if(!curColCountMap.containsKey(playerSymbol)){
            curColCountMap.put(playerSymbol,0);
        }
        curColCountMap.put(playerSymbol,curColCountMap.get(playerSymbol)+1);

        return curColCountMap.get(playerSymbol)==board.getDimension();
    }
}
