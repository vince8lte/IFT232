package GUI;

import java.io.BufferedReader;

import GraphicsInterface.IRenderable;
import classes.Board;
import java.io.*;
import classes.Player;
import classes.Player.Color;

public class ChessGame {
    
    private Board board;
    private ChessGraphic graphic;

    private Player[] players; // Joueurs du jeu
    private int currentPlayerIndex; // Index du joueur qui joue actuellement
    private boolean endGame;
    
    public ChessGame(int playersCount) 
    {
        this.board = new Board();               
        this.players = new Player[playersCount]; 
        this.endGame = false;
        
        for (int playerIndex = 0; playerIndex < this.players.length; ++playerIndex)
            this.players[playerIndex] = Player.createPlayer(Color.values()[playerIndex]);

        this.graphic = new ChessGraphic(this, board.getBoard());
        loadDefaultBoard();
    }
           
    // Change de tour du joueur est met � jour les informations � afficher sur le titre de la fenetre
    private void changeActivePlayer(){
        currentPlayerIndex = ((currentPlayerIndex + 1) % players.length);
    }
    
    private void showGameStatus(){
		String ColorTurn = players[currentPlayerIndex].getColor().toString();
        
        graphic.showGameStatus("turn of " + ColorTurn + " player");
    }
    
    private void showEndOfGame(){
    	graphic.showGameStatus("Checkmate " + players[currentPlayerIndex].getColor().toString() + " have lost.");
    }
    
    public void loadDefaultBoard()
    {
        loadBoard("ressources/grid.txt"); 
    }
    
    public void loadBoard(String filePath)
    {
        try
        {
            FileReader fileReader = new FileReader(filePath); 
            BufferedReader br = new BufferedReader(fileReader); 
            
            currentPlayerIndex = 0;
            showGameStatus();
            board.loadBoard(br);
            graphic.setBoard(board.getBoard(), board.getHighlightedSquares());
            graphic.paintGUI();
        }
        catch (Exception e){}
    }
    
    public void saveBoard(String filePath)
    {
        try
        {
            FileWriter fileWriter = new FileWriter(filePath); 
            BufferedWriter bw = new BufferedWriter(fileWriter); 
            
            board.saveBoard(bw);
        }
        catch (Exception e){}
    }
    
    public void play(int x, int y){
    	if(!endGame){
	        if (board.pieceIsSelected())
	        {      
	            // Si la case selectionne est la piece presentement selectionne
	        	if(!board.equalsPieceSelected(x, y)){
			        // Si le déplacement de la pièce a fonctionné
			        if (board.moveSelectedPieceTo(x, y, players[currentPlayerIndex].getColor()))
			        {
			            changeActivePlayer();
			            showGameStatus();
			            graphic.setBoard(board.getBoard(), board.getHighlightedSquares());
			            if(board.isCheckmate(players[currentPlayerIndex].getColor())){
			            	endGame = true;
			            	showEndOfGame();
			            }
			        }
	        	}
	        	else
	        	{
	                board.unselectPiece();
	                graphic.setBoard(board.getBoard(), new IRenderable[0][0]);
	            }
	        }
	        else
	        {
	        	if(board.selectPiece(x, y, players[currentPlayerIndex].getColor())){
	        	    graphic.setBoard(board.getBoard(), board.getHighlightedSquares());
	        	}          
	        }
	    }
    }
}