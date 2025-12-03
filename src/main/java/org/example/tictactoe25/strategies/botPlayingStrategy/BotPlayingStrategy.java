package org.example.tictactoe25.strategies.botPlayingStrategy;

import org.example.tictactoe25.models.Board;
import org.example.tictactoe25.models.Move;

public interface BotPlayingStrategy {
    Move makeMove(Board board);
}
