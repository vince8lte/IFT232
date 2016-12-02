package classes;

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
    	
    	initialization();
    }
    
    public Board(int boardSizeX, int boardSizeY){
    	this.boardSizeX = boardSizeX;
    	this.boardSizeY = boardSizeY;
    	
    	initialization();
    }
    
    private void initialization()
    {
    	posXSelect = NOT_SELECTED;
    	posYSelect = NOT_SELECTED;
    	
    	board = new Piece[boardSizeX][boardSizeY];
    	// A CHANGER EVENTUELLEMENT LORSQUON VA POUVOIR LOADER UN BOARD PERSONNALISÉ
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
    
    //Fonction qui gere le mouvement de piece
    public void move(int x, int y){
    	// si on a cliquer sur une piece
        if(haveSelectedPiece())
        {
        	// Si on reclique sur la piece selectionner, on veut déselectionner la piece presentement selectionne.
        	if(getPieceAt(x, y) == getSelectedPiece())
        		unselectPiece();
        	else
        		movePiece(x, y);                	                	
        }               
        else {
        	selectPiece(x, y);
        }
    }
    
    
    public int getBoardSizeX()
    {
    	return this.boardSizeX;
    }
    
    public int getBoardSizeY()
    {
    	return this.boardSizeY;
    }
    
    public String getImagePiece(int x, int y){
    	
    	String resultat = null;
    	
    	if(getPieceAt(x, y) != null){
    		resultat = getPieceAt(x, y).getImgUrl();
    	}
    	
    	return resultat;
    }
    
    //Retourne la piece à une position donner x et y
    private Piece getPieceAt(int posX, int posY)
	{
		if (posX >= 0 && posX < this.boardSizeX &&
			posY >= 0 && posY < this.boardSizeY)
			return board[posX][posY];
		else
			return null;
	}
	
    // Retourne si une pièce est présentement sélectionné ou pas
    private boolean haveSelectedPiece(){
    	return ((posXSelect != NOT_SELECTED) && (posYSelect != NOT_SELECTED));
    }
    
    private Piece getSelectedPiece() {
    	return getPieceAt(this.posXSelect, this.posYSelect);
    }
    
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
    
    private void unselectPiece()
    {
    	posXSelect = NOT_SELECTED;
    	posYSelect = NOT_SELECTED;
    }
    
    private void movePiece(int posX, int posY)
    {
    	Piece pieceToMove = getPieceAt(posXSelect, posYSelect);
    	
    	if (pieceToMove != null)
    	{
    		pieceToMove.canMove(posX - posXSelect, posY - posYSelect);
    		
    		
    		
    		this.board[posXSelect][posYSelect] = null;
    		this.board[posX][posY] = pieceToMove;
    		
    		unselectPiece();
    	}
    }
}
