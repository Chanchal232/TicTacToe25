package org.example.tictactoe25.models;

import org.example.tictactoe25.factories.BotPlayingStrategyFactory;
import org.example.tictactoe25.strategies.botPlayingStrategy.BotPlayingStrategy;

public class Bot extends  Player{

    private BotDifficultyLevel difficultyLevel;
    private BotPlayingStrategy botPlayingStrategy;

    public Bot(String name,Symbol symbol, BotDifficultyLevel difficultyLevel) {
        super(name,symbol);
        this.setPlayerType(PlayerType.BOT);
        this.difficultyLevel = difficultyLevel;
        this.botPlayingStrategy = BotPlayingStrategyFactory.getBotPlayingStrategy(difficultyLevel);
    }

    public BotDifficultyLevel getDifficultyLevel() {
        return difficultyLevel;
    }

    public void setDifficultyLevel(BotDifficultyLevel difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }

    public Move makeMove(Board board){
        //This is going to make move accordin to Bot playing strategy
        return botPlayingStrategy.makeMove(board, this);
    }

}
