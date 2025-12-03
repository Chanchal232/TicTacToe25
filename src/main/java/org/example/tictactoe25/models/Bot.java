package org.example.tictactoe25.models;

public class Bot extends  Player{

    private BotDifficultyLevel difficultyLevel;

    public Bot(String name,Symbol symbol, BotDifficultyLevel difficultyLevel) {
        super(name,symbol);
        this.setPlayerType(PlayerType.BOT);
        this.difficultyLevel = difficultyLevel;
    }

    public BotDifficultyLevel getDifficultyLevel() {
        return difficultyLevel;
    }

    public void setDifficultyLevel(BotDifficultyLevel difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }



}
