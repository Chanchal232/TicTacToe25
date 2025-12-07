package org.example.tictactoe25.strategies.botPlayingStrategy;

import org.example.tictactoe25.models.*;

public class EasyBotPlayingStrategy implements BotPlayingStrategy
{
    @Override
    public Move makeMove(Board board, Player player) {
        //Bot is going to iterate over the board
        // And pick the first empty cell & make move here

        for(int i=0;i<board.getDimension();i++){
            for(int j=0;j<board.getDimension();j++){
                Cell cell = board.getBoard().get(i).get(j);
                if(cell.getCellState() == CellState.EMPTY){
                    return new Move(cell,player);
                }
            }
        }
        return null;
    }
}
