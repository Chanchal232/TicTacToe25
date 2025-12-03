package org.example.tictactoe25.factories;

import org.example.tictactoe25.exceptions.InvalidBotDifficultyException;
import org.example.tictactoe25.models.Board;
import org.example.tictactoe25.models.BotDifficultyLevel;
import org.example.tictactoe25.strategies.botPlayingStrategy.BotPlayingStrategy;
import org.example.tictactoe25.strategies.botPlayingStrategy.EasyBotPlayingStrategy;
import org.example.tictactoe25.strategies.botPlayingStrategy.HardBotPlayingStrategy;
import org.example.tictactoe25.strategies.botPlayingStrategy.MediumBotPlayingStrategy;
import org.springframework.beans.factory.BeanNotOfRequiredTypeException;

public class BotPlayingStrategyFactory {

    public static BotPlayingStrategy getBotPlayingStrategy(BotDifficultyLevel level) throws InvalidBotDifficultyException {
        switch (level) {
            case EASY:
                return new EasyBotPlayingStrategy();
            case HARD:
                return new HardBotPlayingStrategy();
            case MEDIUM:
                return new MediumBotPlayingStrategy();
            default:{
                throw new InvalidBotDifficultyException("Invalid Bot level "+level);
            }
        }

    }
}
