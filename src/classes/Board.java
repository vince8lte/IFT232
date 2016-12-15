package classes;

import java.awt.Image;

import Piece.*;

// Cette classe contient les informations spécifiques par rapport au board.
// Cette classe contient les règles du jeu d'échec.
public class Board {
   // private static final long serialVersionUID = 1L;

    private final int BOARD_SIZE = 8;
    private final int NOT_SELECTED = -1;
    
    private Piece[][] board;		//Contient la position de toute les pieces de l'�chiquier
    private int selectedX;				//Indique la position de la piece selectionner en X
    private int selectedY;				//Indique la position de la piece selectionner en Y

	public Board(){
	    //FlowLayout layout = (FlowLayout)this.getLayout();
        //layout.setVgap(0);
        
        board = new Piece[BOARD_SIZE][BOARD_SIZE];
        selectedX = NOT_SELECTED;
        selectedY = NOT_SELECTED;
	}
	
	public void selectPiece(int x, int y)
	{
	    if (getPiece(x, y) != null)
	    {
	        selectedX = x;
	        selectedY = y;	      
	    }
	}
	
	public void unselectPiece()
	{
	    selectedX = NOT_SELECTED;
	    selectedY = NOT_SELECTED;
	}
	
	   public boolean pieceIsSelected(){
	        return ((selectedX != NOT_SELECTED) && (selectedY != NOT_SELECTED));
	    }
	
	// Retourne la couleur de "l'equipe" de la piece (none, si une piece existe pas)
	public Player.Color getTeamColorFromPiece(int x, int y)
	{
	    Piece piece = getPiece(x, y);
	    
	    if (piece != null)	        
	        return (piece.getColor());
	    else
	        return Player.Color.NONE;
	}
	
   public Player.Color getTeamColorFromSelectedPiece()
    {
       return getTeamColorFromPiece(selectedX, selectedY);
    }
	

	
	public void highlightPossibleMoves()
	{
		PiecePatterns[] patterns = this.getSelectedPiece().getPattern();
		int x, y;		//Contient le positionnement de la v�rification
		Piece tempPiece;
		int nbrSaut;
		
		//boucle sur tout les parttern de la piece
		for ( int i =0 ; i<patterns.length ; i++)
		{
			x = selectedX;
			y = selectedY;
			nbrSaut = 0; 	//Compteur de saut effectuer	
			
			//V�rifie toutes les possibilit�es de mouvement selon un patter
			do
			{
				x +=patterns[i].getDirectionX();
				y += patterns[i].getDirectionY();
				tempPiece = null;
				
				if(isInChess(x, y)){
					tempPiece = getPiece(x, y);
					if (tempPiece == null)
					{
						//highlighter la piece
					}
				}
				
				nbrSaut++;
				
			}while((tempPiece == null) && (nbrSaut<patterns[i].getDistanceMax()));
		}
	}
	
	//Enregistre dans un string la partie courante
	public String createSaveFile(){
		String sauvegarde = "";
		Piece pieceTempo = null;
		
		for(int i = 0; i < (BOARD_SIZE*BOARD_SIZE); i++){
			pieceTempo = getPiece(i%8, i/8);
			
			if(pieceTempo != null){
				sauvegarde +=Integer.toString(i%8) + "," + Integer.toString(i%8) + "|";
				sauvegarde += pieceTempo.toString() + "\n";
			}
		}
		
		return sauvegarde;
	}
	
	//Lit un fichier pour l'appliquer au board
	public void LoadSaveFile(String loadFile){
		
		//Distingue toute les lignes
		String[] Ligne = loadFile.split("\n");
		String posSection;
		int posX;
		int posY;		

		//Vide l'echiquiers
		for(int i = 0; i < (BOARD_SIZE*BOARD_SIZE); i++){
			board[i%8][i/8] = null;
		}
		
		//Parcour toute les lignes � charger
		for(int i=0; i < Ligne.length; i++){
			posSection = Ligne[1].split(",")[0];
			posX = Integer.parseInt(posSection.split(",")[0]);
			posY = Integer.parseInt(posSection.split(",")[1]);
			board[posX][posY] = PieceFactory.getInstance().givePiece(Ligne[1].split(",")[1]);
		}		
	}
	
	
	//****** Partie priv�e du code ******************//
	
	private Piece getPiece(int x, int y){
		if(isInChess(x, y)){
			return board[x][y];
		}else{
			return null;
		}
	}
	
	private Piece getSelectedPiece(){
		if(pieceIsSelected()){
			return board[selectedX][selectedY];
		}else{
			return null;
		}
	}
	
	//S'assure que la position x et y fournis soient en tout temps dans les limites du board
	private boolean isInChess(int x, int y){
		
		if((x >= 0) && (x < BOARD_SIZE) && (y >= 0) && (y < BOARD_SIZE)){
			return true;
		}
		else{
			return false;
		}
	}
	
	private boolean squareIsEmpty(int x, int y){
		return (board[x][y] == null);
	}
	
	// Cette procedure deplace la piece presentement selectionne a lendroit specifie recu en parametre.
	public void moveSelectedPieceTo(int x, int y){	
	    if (pieceIsSelected())
	    {
	        Piece movingPiece = getPiece(selectedX, selectedY);
	        PiecePattern movementPatternFromMovingPiece = movingPiece.getPattern(selectedX, selectedY, x, y);
	        
	        if (movementPatternFromMovingPiece != null)
	        {
                board[x][y] = board[selectedX][selectedY];
                board[selectedX][selectedY] = null;
                
                unselectPiece(); 	            
	        }
       
	    }

	}
	
	private 
	
	
	/*
	public void movePiece(Square square)
	{
		Piece movingPiece = selectedSquare.getPiece();
		if (movingPiece.canMoveTo(square)) {
			selectedSquare.movePieceTo(square);
			selectedSquare = null;
			grid.resetSelectedSquare();
			changeActivePlayer();
		}
		else if (square == selectedSquare) {
			selectedSquare = null;
			grid.resetSelectedSquare();
		}
	}*/
}
