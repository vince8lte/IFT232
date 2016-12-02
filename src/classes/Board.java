package classes;

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
    	
        for (int X = 0; X < board.length; ++X)
        {
            board[X][0] = new Piece(true);
            board[X][1] = new Piece(true);
            board[X][2] = null;
            board[X][3] = null;
            board[X][4] = null;
            board[X][5] = null;      
            board[X][6] = new Piece(false);
            board[X][7] = new Piece(false);
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
    
    //Retourne la piece à une position donner x et y
	public Piece getPieceAt(int posX, int posY)
	{
		if (posX >= 0 && posX < this.boardSizeX &&
			posY >= 0 && posY < this.boardSizeY)
			return board[posX][posY];
		else
			return null;
	}
	
    // Retourne si une pièce est présentement sélectionné ou pas
    public boolean haveSelectedPiece(){
    	return ((posXSelect != NOT_SELECTED) && (posYSelect != NOT_SELECTED));
    }
    
    public Piece getSelectedPiece() {
    	return getPieceAt(this.posXSelect, this.posYSelect);
    }
    
    public boolean selectPiece(int posX, int posY)
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
    
    public void unselectPiece()
    {
    	posXSelect = NOT_SELECTED;
    	posYSelect = NOT_SELECTED;
    }
    
    public void movePiece(int posX, int posY)
    {
    	Piece pieceToMove = getPieceAt(posXSelect, posYSelect);
    	
    	if (pieceToMove != null)
    	{
    		this.board[posXSelect][posYSelect] = null;
    		this.board[posX][posY] = pieceToMove;
    		
    		unselectPiece();
    	}
    		
    }
}
