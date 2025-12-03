package org.example.tictactoe25.strategies.winningStrategy;

import org.example.tictactoe25.models.Board;
import org.example.tictactoe25.models.Move;

public interface WinningStrategy {
    boolean checkWinner(Move move, Board board);
}
