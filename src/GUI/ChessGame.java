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

    %3CmxGraphModel%3E%3Croot%3E%3CmxCell%20id%3D%220%22%2F%3E%3CmxCell%20id%3D%221%22%20parent%3D%220%22%2F%3E%3CmxCell%20id%3D%222%22%20value%3D%22Main%26lt%3Bdiv%26gt%3B%26lt%3Bbr%26gt%3B%26lt%3B%2Fdiv%26gt%3B%22%20style%3D%22swimlane%3Bhtml%3D1%3BfontStyle%3D1%3Balign%3Dcenter%3BverticalAlign%3Dtop%3BchildLayout%3DstackLayout%3Bhorizontal%3D1%3BstartSize%3D26%3BhorizontalStack%3D0%3BresizeParent%3D1%3BresizeLast%3D0%3Bcollapsible%3D1%3BmarginBottom%3D0%3BswimlaneFillColor%3D%23ffffff%3B%22%20vertex%3D%221%22%20parent%3D%221%22%3E%3CmxGeometry%20x%3D%22400%22%20y%3D%22240%22%20width%3D%22160%22%20height%3D%2290%22%20as%3D%22geometry%22%2F%3E%3C%2FmxCell%3E%3CmxCell%20id%3D%223%22%20value%3D%22%2B%20field%3A%20type%22%20style%3D%22text%3Bhtml%3D1%3BstrokeColor%3Dnone%3BfillColor%3Dnone%3Balign%3Dleft%3BverticalAlign%3Dtop%3BspacingLeft%3D4%3BspacingRight%3D4%3BwhiteSpace%3Dwrap%3Boverflow%3Dhidden%3Brotatable%3D0%3Bpoints%3D%5B%5B0%2C0.5%5D%2C%5B1%2C0.5%5D%5D%3BportConstraint%3Deastwest%3B%22%20vertex%3D%221%22%20parent%3D%222%22%3E%3CmxGeometry%20y%3D%2226%22%20width%3D%22160%22%20height%3D%2226%22%20as%3D%22geometry%22%2F%3E%3C%2FmxCell%3E%3CmxCell%20id%3D%224%22%20value%3D%22%22%20style%3D%22line%3Bhtml%3D1%3BstrokeWidth%3D1%3BfillColor%3Dnone%3Balign%3Dleft%3BverticalAlign%3Dmiddle%3BspacingTop%3D-1%3BspacingLeft%3D3%3BspacingRight%3D3%3Brotatable%3D0%3BlabelPosition%3Dright%3Bpoints%3D%5B%5D%3BportConstraint%3Deastwest%3B%22%20vertex%3D%221%22%20parent%3D%222%22%3E%3CmxGeometry%20y%3D%2252%22%20width%3D%22160%22%20height%3D%228%22%20as%3D%22geometry%22%2F%3E%3C%2FmxCell%3E%3CmxCell%20id%3D%225%22%20value%3D%22%2B%20method(type)%3A%20type%22%20style%3D%22text%3Bhtml%3D1%3BstrokeColor%3Dnone%3BfillColor%3Dnone%3Balign%3Dleft%3BverticalAlign%3Dtop%3BspacingLeft%3D4%3BspacingRight%3D4%3BwhiteSpace%3Dwrap%3Boverflow%3Dhidden%3Brotatable%3D0%3Bpoints%3D%5B%5B0%2C0.5%5D%2C%5B1%2C0.5%5D%5D%3BportConstraint%3Deastwest%3B%22%20vertex%3D%221%22%20parent%3D%222%22%3E%3CmxGeometry%20y%3D%2260%22%20width%3D%22160%22%20height%3D%2226%22%20as%3D%22geometry%22%2F%3E%3C%2FmxCell%3E%3C%2Froot%3E%3C%2FmxGraphModel%3E
}