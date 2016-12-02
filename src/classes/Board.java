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
    	
    	Initialization();
    }
    
    public Board(int boardSizeX, int boardSizeY){
    	this.boardSizeX = boardSizeX;
    	this.boardSizeY = boardSizeY;
    	
    	Initialization();
    }
    
    private void Initialization()
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
    
    
    public int BoardSizeX()
    {
    	return this.boardSizeX;
    }
    
    public int BoardSizeY()
    {
    	return this.boardSizeY;
    }
    
    //Retourne la piece à une position donner x et y
	public Piece GetPieceAt(int posX, int posY)
	{
		if (posX >= 0 && posX < this.boardSizeX &&
			posY >= 0 && posY < this.boardSizeY)
			return board[posX][posY];
		else
			return null;
	}
	
    // Retourne si une pièce est présentement sélectionné ou pas
    public boolean HaveSelectedPiece(){
    	return ((posXSelect != NOT_SELECTED) && (posYSelect != NOT_SELECTED));
    }
    
    public Piece getSelectedPiece() {
    	return GetPieceAt(this.posXSelect, this.posYSelect);
    }
    
    public boolean SelectPiece(int posX, int posY)
    {
    	if (GetPieceAt(posX, posY) != null)
    	{
    		this.posXSelect = posX;
    		this.posYSelect = posY;
    		return true;
    	}
    	else
    		return false;
    }
    
    public void UnselectPiece()
    {
    	posXSelect = NOT_SELECTED;
    	posYSelect = NOT_SELECTED;
    }
    
    public void MovePiece()
    {
    	
    }
}
