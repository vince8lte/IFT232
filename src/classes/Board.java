package classes;

import java.awt.Image;
import java.awt.geom.Point2D;

import Piece.*;

public class Board {
   // private static final long serialVersionUID = 1L;

    private final int BOARD_SIZE = 8;
    private final int NOT_SELECTED = -1;

	
    private Image background;
    private Image scaledBackground;
    private Point2D.Double borderSize;
    private Point2D.Double squareSize;
    
    private Piece[][] echequier;		//Contient la position de toute les pieces de l'échiquier
    private int selectedX;				//Indique la position de la piece selectionner en X
    private int selectedY;				//Indique la position de la piece selectionner en Y
    
    private int phase;					//Indique la phase du board en cour
	
	public Board(){
	    //FlowLayout layout = (FlowLayout)this.getLayout();
        //layout.setVgap(0);
        
        echequier = new Piece[BOARD_SIZE][BOARD_SIZE];
        selectedX = NOT_SELECTED;
        selectedY = NOT_SELECTED;
        
        phase = 0;
        
        //this.borderSize = new Point2D.Double(this.getWidth()*0.0625, this.getHeight()*0.0625);
        //this.squareSize = new Point2D.Double((this.getWidth()-borderSize.x*2.0)/8.0, this.getHeight()-borderSize.y*2.0/8.0);
	}

	public int Action(int clickedX, int clickedY){
		return phase;
	}
	
	public void highlightPossibleMoves()
	{
		PiecePatterns[] patterns = this.getSelectedPiece().getPattern();
		int x, y;		//Contient le positionnement de la vérification
		Piece tempPiece;
		int nbrSaut;
		
		//boucle sur tout les parttern de la piece
		for ( int i =0 ; i<patterns.length ; i++)
		{
			x = selectedX;
			y = selectedY;
			nbrSaut = 0; 	//Compteur de saut effectuer	
			
			//Vérifie toutes les possibilitées de mouvement selon un patter
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
				sauvegarde += "[" + Integer.toString(i%8) + "," + Integer.toString(i%8) + "] ";
				sauvegarde += pieceTempo.toString() + "/n";
			}
		}
		
		return sauvegarde;
	}
	
	//Lit un fichier pour l'appliquer au board
	public void LoadSaveFile(String loadFile){
		
		deleteBoard();
	
		//Hash le loadFile pour faire des appels à la factory
		
	}
	
	//Vide l'echequier
	public void deleteBoard(){
		for(int i = 0; i < (BOARD_SIZE*BOARD_SIZE); i++){
			echequier[i%8][i/8] = null;
		}
	}
	
	
	//****** Partie privée du code ******************//
	
	private Piece getPiece(int x, int y){
		if(isInChess(x, y)){
			return echequier[x][y];
		}else{
			return null;
		}
	}
	
	private Piece getSelectedPiece(){
		if(pieceIsSelected()){
			return echequier[selectedX][selectedY];
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
		return (echequier[x][y] == null);
	}
	
	private boolean pieceIsSelected(){
		return ((selectedX != NOT_SELECTED) && (selectedY != NOT_SELECTED));
	}
	
	private void movePiece(int x, int y){
		echequier[x][y] = echequier[selectedX][selectedY];
		echequier[selectedX][selectedY] = null;
	}
	
	
	/*
	private void movePiece(Square square)
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
