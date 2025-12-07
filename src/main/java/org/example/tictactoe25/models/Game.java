package org.example.tictactoe25.models;

import org.example.tictactoe25.exceptions.InvaliMoveException;
import org.example.tictactoe25.exceptions.InvalidBotCountException;
import org.example.tictactoe25.exceptions.InvalidPlayerCountException;
import org.example.tictactoe25.strategies.winningStrategy.ColWinningStrategy;
import org.example.tictactoe25.strategies.winningStrategy.DiagonalWinningStrategy;
import org.example.tictactoe25.strategies.winningStrategy.RowWinningStrategy;
import org.example.tictactoe25.strategies.winningStrategy.WinningStrategy;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private Board board;
    private List<Player> players;
    private GameState gameState;
    private List<Move> moves;
    private Player winner;
    private int nextPlayerIndex;
    private int dimension;
    private List<WinningStrategy> winningStrategies;

    //Making constructor private so that anyone else can't create Game Object directly
    private Game(int dimension, List<Player> players,List<WinningStrategy> winningStrategies) {
        this.board = new Board(dimension);
        this.players = players;
        this.gameState = GameState.IN_PROGESS;
        this.moves = new ArrayList<>();
        this.dimension = dimension;

        this.nextPlayerIndex=0;
        this.winningStrategies = winningStrategies;

    }

    public static GameBuilder getBuilder(){
        return new GameBuilder();
    }

    public int getNextPlayerIndex() {
        return nextPlayerIndex;
    }

    public void setNextPlayerIndex(int nextPlayerIndex) {
        this.nextPlayerIndex = nextPlayerIndex;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public List<Move> getMoves() {
        return moves;
    }

    public void setMoves(List<Move> moves) {
        this.moves = moves;
    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public void displayBoard() {
        this.board.displayBoard(this.players);
    }

    public void makeMove() throws InvaliMoveException {
        //Find player who needs to make move
        Player currentPlayer = players.get(nextPlayerIndex);

        System.out.println("This is "+currentPlayer.getName()+"'s move");
        Move move = currentPlayer.makeMove(board);

        //Game will validate move
        if(!isMoveValid(move)){
            System.out.println("This is not valid cell "+currentPlayer.getName()+"'s move");
            //throw new InvaliMoveException("Invalid Move");
            return;
        }

        Cell cell = move.getCell();
        if(!cell.isEmpty()){
            System.out.println("This cell is not empty. Please try another one");
            return;
        }



        int row = move.getCell().getRow();
        int col = move.getCell().getCol();


        cell.setPlayer(currentPlayer);
        cell.setCellState(CellState.FILLED);
      //  move.setCell(cell);
        System.out.println(currentPlayer.getName() +" Thas been made move successfully");

        nextPlayerIndex = (nextPlayerIndex+1)%players.size();

        moves.add(move);

        //Check winner
        if(checkWinner(move)){
            this.winner = move.getPlayer();
            gameState = GameState.ENDED;
        }else if(moves.size() == board.getDimension()*board.getDimension()){
            //DRAW
            gameState = GameState.DRAW;

        }

    }

    private boolean checkWinner(Move move){
        for(WinningStrategy winningStrategy : winningStrategies){
            if(winningStrategy.checkWinner(move,this.board)){
                return true;
            }
        }
        return false;
    }

    private boolean isMoveValid(Move move) throws InvaliMoveException {
        int row = move.getCell().getRow();
        int col = move.getCell().getCol();

        if(row<0 ||
                row>=dimension ||
                col<0 ||
                col>=dimension){
            return false;
        }
        return true;
    }

    public void undo() {
        if(moves.isEmpty()){
            System.out.println("There is nothing to undo");
            return;
        }

        //Get the last move
        Move lastMove = moves.remove(moves.size()-1);

        //Reset the cell on board
        Cell cell = lastMove.getCell();
        cell.setCellState(CellState.EMPTY);
        cell.setPlayer(null);

        //Update next player index to prev player
        nextPlayerIndex = (nextPlayerIndex-1+players.size())%players.size();
        //0 1 2 3 4 5
        //If player 4 does and undo, then 3rd player's move gets undone
        System.out.println("Last move is undone. Now its "+ players.get(nextPlayerIndex)+" turn");
    }

    //Create Static Internal Class for Builder
    public static class GameBuilder{
        private List<Player> players = new ArrayList<>();
        private int dimension;

        public List<Player> getPlayers() {
            return players;
        }

        public GameBuilder setPlayers(List<Player> players) {
            this.players = players;
            return this;
        }

        public int getDimension() {
            return dimension;
        }

        public GameBuilder setDimension(int dimension) {
            this.dimension = dimension;
            return this;
        }

        private void validateBotCount() throws InvalidBotCountException{
            int botcount = 0;
            for(Player p:players){
                if(p.getPlayerType() == PlayerType.BOT){
                    botcount++;
                }
            }
            if(botcount>1){
                throw new InvalidBotCountException("There are more than 1 bot in game");
            }
        }

        private void validatePlayerCount()  throws InvalidPlayerCountException{
            int playerCount = 0;
            //playerCount = dimension-1
            if(players.size()!=dimension-1){
                throw new InvalidPlayerCountException("Players count is not valid");
            }
        }

        private void validateDimensions(){

        }

        //Here comes validations
        public Game build(){
            //validations
            validateGame();

            //Currently we are hardcoding
            List<WinningStrategy> winningStrategies = new ArrayList<>();
            winningStrategies.add(new RowWinningStrategy());
            winningStrategies.add(new ColWinningStrategy());
            winningStrategies.add(new DiagonalWinningStrategy());

            return new Game(dimension,players,winningStrategies);
        }

        private void validateGame() {

            try{
                validateBotCount();
            }catch (InvalidBotCountException e){
                System.out.println(e.getMessage());
            }

            try{
                validatePlayerCount();
            }catch (InvalidPlayerCountException e){
                System.out.println(e.getMessage());
            }
            validateDimensions();
        }


    }


    public int getDimension() {
        return dimension;
    }

    public void setDimension(int dimension) {
        this.dimension = dimension;
    }

    public List<WinningStrategy> getWinningStrategies() {
        return winningStrategies;
    }

    public void setWinningStrategies(List<WinningStrategy> winningStrategies) {
        this.winningStrategies = winningStrategies;
    }
}
