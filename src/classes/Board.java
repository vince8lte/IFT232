package classes;

import java.awt.Image;

import Piece.*;

// Cette classe contient les informations spÃ©cifiques par rapport au board.
// Cette classe contient les rÃ¨gles du jeu d'Ã©chec.
public class Board {
   // private static final long serialVersionUID = 1L;

    private final int BOARD_SIZE = 8;
    private final int NOT_SELECTED = -1;
    
    private Piece[][] board;		//Contient la position de toute les pieces de l'ï¿½chiquier
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
   
   // Cette procedure deplace la piece presentement selectionne a lendroit specifie recu en parametre.
	public void moveSelectedPieceTo(int x, int y){	
	    if (pieceIsSelected())
	    {
	        Piece movingPiece = getSelectedPiece();
	        PiecePattern movementPatternFromMovingPiece = movingPiece.getPattern(selectedX, selectedY, x, y);
	        
	        if (movementPatternFromMovingPiece != null)
	        {
	        	if(canMovePiece(movementPatternFromMovingPiece, x, y)){
	        		
	        		//déplacement special pour le roque
	        		if(isTryingToCastling(getPiece(x,y))){
	        			
	        			this.DoCastling(x,y);
	        			
	        		}else{
		        		
		                board[x][y] = board[selectedX][selectedY];
		                board[selectedX][selectedY] = null;
	        		}
	        		
	        		movingPiece.hasMoved();
	                
	                unselectPiece(); 	       
	        	}
	        }
       
	    }

	}
	
	
	public void highlightPossibleMoves()
	{
		PiecePattern[] patterns = this.getSelectedPiece().getPatterns();
		int x, y;		//Contient le positionnement de la vï¿½rification
		Piece tempPiece;
		int nbrSaut;
		
		//boucle sur tout les parttern de la piece
		for ( int i =0 ; i<patterns.length ; i++)
		{
			x = selectedX;
			y = selectedY;
			nbrSaut = 0; 	//Compteur de saut effectuer	
			
			//Vï¿½rifie toutes les possibilitï¿½es de mouvement selon un patter
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
		
		//Parcour toute les lignes ï¿½ charger
		for(int i=0; i < Ligne.length; i++){
			posSection = Ligne[1].split(",")[0];
			posX = Integer.parseInt(posSection.split(",")[0]);
			posY = Integer.parseInt(posSection.split(",")[1]);
			board[posX][posY] = PieceFactory.getInstance().givePiece(Ligne[1].split(",")[1]);
		}		
	}
	
	
	//****** Partie privï¿½e du code ******************//
	
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
	
	//Cette method s'assure que la piece peut se déplacer jusqu'à la possition souhaiter
	private boolean canMovePiece(PiecePattern pattern, int finalPosX, int finalPosY){
		
		boolean result = false;
		int posX = selectedX;
		int posY = selectedY;
		int jumpCount = 0;			//Permet de ne jamais dépassé le nombre de saut d'un paterne
		Piece finalPosPiece = getPiece(posX, posY);		//contient la piece à la position final
		
		do{
			posX += pattern.getDirectionX();
			posY += pattern.getDirectionY();
			
			++jumpCount;		//Permet le dépacement du nombre de bond pour faire la vérification du roque
			
			if((posX == finalPosX) && (posY == finalPosY)){
				result = true;
			}
			
		}while(!result && (getPiece(posX, posY) != null));
		
		//Si l'usage ne tante pas de faire un roque, on s'assure que la piece ne
		if(!isTryingToCastling(finalPosPiece)){
			result = result && (jumpCount < pattern.getDistanceMax());
		}
		
		//Si le pattern ne peut pas bouger et attaquer en même temps (gère l'exeption du pion)
		if(!(pattern.isAttackPattern() && pattern.isMouvementPattern()) && result){
			
			//Si le pattern est exclusivement pour les attaque
			if(pattern.isAttackPattern()){
				if(finalPosPiece == null){
					result = false;
				}
			}
			//Si le pattern est exclusivement pour les déplacements
			if(pattern.isMouvementPattern()){
				if(finalPosPiece != null){
					result = false;
				}
			}
		}
		
		return result;
	}
	
	//Verifie si l'usager tante d'effectuer le roque
	private boolean isTryingToCastling(Piece finalPosPiece){
		boolean result = false;
		Piece SelectedPiece = getSelectedPiece();
		
		if((SelectedPiece != null) && (finalPosPiece != null)){
			//Verifie que les deux pieces soit de la même couleur, sinon ca ne sert à rien
			if(SelectedPiece.getColor() == finalPosPiece.getColor()){
			
				//Verifie que les piec soit des instances de roi et tour
				if(((SelectedPiece instanceof Roi) && (finalPosPiece instanceof Tour)) 
						||((SelectedPiece instanceof Tour) && (finalPosPiece instanceof Roi))){
					
					if(SelectedPiece.canSpecialMove() && finalPosPiece.canSpecialMove()){
						result = true;
					}
				}
			}
		}
		
		return result;
	}
	
	
	//Effectue le roque
	private void DoCastling(int x, int y){
		int kingX = selectedX;
		int castleX = x;
		Piece tempoPiece;
		
		if(getSelectedPiece() instanceof Tour){			
			kingX = x;
			castleX = selectedX;
		}
		
		//Retire le mouvement spécial de la tour non utilisé
		if(castleX == 0){
			tempoPiece = getPiece(7,y);
		}else{
			tempoPiece = getPiece(0,y);
		}
		
		//Retire le mouvement special de la tour non utilisé et de la piece d'arriver : la piece selectionner se fait au retour
		if(tempoPiece != null){ tempoPiece.hasMoved(); }
		getPiece(x,y).hasMoved();
		
		//Verifie si le grand roque doit être fait
		if(castleX < kingX){
			board[2][y] = board[kingX][y];	//Deplace le roi
			board[3][y] = board[castleX][y];	//Deplace la tour
		}else{
			board[6][y] = board[kingX][y];	//Deplace le roi
			board[5][y] = board[castleX][y];	//Deplace la tour
		}

		board[kingX][y] = null;
		board[castleX][y] = null;
	}
}
