package classes;

import java.util.ArrayList;
import java.util.List;

import Piece.*;

public class Board {

	//Constante
	final int DEFAULT_BOARD_SIZE = 8;
	final int NOT_SELECTED = -1;
	
	//conserve la position du premier clic
    private int posXSelect;
    private int posYSelect;
    
    //Contient la taille maximal du board
    private int boardSizeX;
    private int boardSizeY;
    
    //Contient le tableau de piece
    Piece[][] board;
    
    public Board(){
    	
    	this.boardSizeX = DEFAULT_BOARD_SIZE;
    	this.boardSizeY = DEFAULT_BOARD_SIZE;
    	
    	posXSelect = NOT_SELECTED;
    	posYSelect = NOT_SELECTED;
    	
    	board = new Piece[boardSizeX][boardSizeY];
    	
    	// A CHANGER EVENTUELLEMENT LORSQUON VA POUVOIR LOADER UN BOARD PERSONNALIS�
        for (int X = 0; X < board.length; ++X)
        {
            board[X][0] = new Pion(true);
            board[X][1] = new Pion(true);
            board[X][2] = null;
            board[X][3] = null;
            board[X][4] = null;
            board[X][5] = null;      
            board[X][6] = new Pion(false);
            board[X][7] = new Pion(false);
        }
    }
    
    
    //Methode qui renvoie les informations nescessaire a afficher le board
    public List<Object[]> getBoardImage(){
    	
    	List<Object[]> result = new ArrayList<Object[]>();
    	Object[] Information;
    	
    	//Parcour le board pour chercher les piece a afficher
    	for(int x = 0; x < DEFAULT_BOARD_SIZE; x++){
    		for(int y = 0; y < DEFAULT_BOARD_SIZE; y++){
        		if(getPieceAt(x, y) != null){
        			Information = new Object[3];
        			Information[0] = getPieceAt(x, y).getImgUrl();
        			Information[1] = x;
        			Information[2] = y;
        			result.add(Information);
        		}
        	}
    	}
 
    	return result;		
    }
    
    //Fonction qui gere le mouvement de piece
    public void move(int x, int y){
    	// si on a cliquer sur une piece
        if(haveSelectedPiece())
        {
        	// Si on reclique sur la piece selectionner, on veut d�selectionner la piece presentement selectionne.
        	if(getPieceAt(x, y) == getSelectedPiece())
        		unselectPiece();
        	else
        		movePiece(x, y);                	                	
        }               
        else {
        	selectPiece(x, y);
        }
    }
    
    //Retourne la piece � une position donner x et y
    private Piece getPieceAt(int posX, int posY)
	{
		if (posX >= 0 && posX < this.boardSizeX &&
			posY >= 0 && posY < this.boardSizeY)
			return board[posX][posY];
		else
			return null;
	}
	
    // Retourne si une pi�ce est pr�sentement s�lectionn� ou pas
    private boolean haveSelectedPiece(){
    	return ((posXSelect != NOT_SELECTED) && (posYSelect != NOT_SELECTED));
    }
    
    //Retourne la piece selectionner
    private Piece getSelectedPiece() {
    	return getPieceAt(this.posXSelect, this.posYSelect);
    }
    
    //Change la piece selectionner
    private boolean selectPiece(int posX, int posY)
    {
    	if (getPieceAt(posX, posY) != null)
    	{
    		this.posXSelect = posX;
    		this.posYSelect = posY;
    		return true;
    	}
    	else
    		return false;
    }
    
    //Deselectionne une piece
    private void unselectPiece()
    {
    	posXSelect = NOT_SELECTED;
    	posYSelect = NOT_SELECTED;
    }
    
    //Deplace une piece sur le board si on peux selon ce qui se trouve a la destination
    private void movePiece(int posX, int posY)
    {	
    	if(getPieceAt(posX, posY) !=null)
    	{
    		Piece targetPiece = getPieceAt(posX, posY);
    		if((getSelectedPiece().isWhite() && !targetPiece.isWhite()) || (!getSelectedPiece().isWhite() && targetPiece.isWhite()))
    		{
    			if(getSelectedPiece().canAttack(posXSelect - posX, posYSelect - posY))
    			{
    				this.board[posX][posY] = getSelectedPiece();
    				this.board[posXSelect][posYSelect] = null;
    				unselectPiece();
    				return;
    			}
    			else
    				System.out.println("i can't attack this piece its out of reach for my attack move!!");
    			
    		}else
    			System.out.println("i wont attack meh friends!");
    		
    	}
    	else
    	{
    		if(getSelectedPiece().canMove(posXSelect - posX, posYSelect - posY))
    		{
    			this.board[posX][posY] = getSelectedPiece();
    			this.board[posXSelect][posYSelect] = null;
    			unselectPiece();
    		}
    		else
    			System.out.println("im 2 lay-Z 2 move there, bro");
    	}
    }
}
