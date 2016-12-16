package GUI;

import java.io.BufferedReader;

import classes.Board;
import java.io.*;
import classes.Player;
import classes.Player.Color;

public class ChessGame {
    
    private Board board;
    private ChessGraphic graphic;

    private Player[] players; // Joueurs du jeu
    private int currentPlayerIndex; // Index du joueur qui joue actuellement
    
    public ChessGame(int playersCount) 
    {
        this.board = new Board();               
        this.players = new Player[playersCount]; 
        
        for (int playerIndex = 0; playerIndex < this.players.length; ++playerIndex)
            this.players[playerIndex] = Player.createPlayer(Color.values()[playerIndex]);

        this.graphic = new ChessGraphic(this, board.getBoard());
        loadDefaultBoard();
    }
           
    // Change de tour du joueur est met à jour les informations à afficher sur le titre de la fenetre
    private void changeActivePlayer(){
        currentPlayerIndex = ((currentPlayerIndex + 1) % players.length);
    }
    
    private void showGameStatus(){
		String ColorTurn = players[currentPlayerIndex].getColor().toString();
        
        graphic.showGameStatus("turn of " + ColorTurn + " player");
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
            graphic.setBoard(board.getBoard());
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
        if (board.pieceIsSelected())
        {      
            // Si la case selectionne est la piece presentement selectionne
        	if(!board.equalsPieceSelected(x, y)){
		        // Si le dÃ©placement de la piÃ¨ce a fonctionnÃ©
		        if (board.moveSelectedPieceTo(x, y, players[currentPlayerIndex].getColor()))
		        {
		            changeActivePlayer();
		            showGameStatus();
		            graphic.setBoard(board.getBoard());
		            graphic.paintGUI();
		        }
        	}
        	else
        	{
                board.unselectPiece();
            }
        }
        else
        {
        	if(board.selectPiece(x, y, players[currentPlayerIndex].getColor())){
        		graphic.paintGUI();
        	}          
        }
    }
}