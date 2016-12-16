package classes;

import java.awt.Image;
import java.util.ArrayList;
import java.util.LinkedList;

import GUI.PatternFactory;
import GraphicsInterface.IRenderable;
import Piece.*;

// Cette classe contient les informations spécifiques par rapport au board.
// Cette classe contient les règles du jeu d'échec.
public class Board {
    private final int BOARD_SIZE = 8;
    private final int NOT_SELECTED = -1;
    
    private Piece[][] board;		//Contient la position de toute les pieces de l'�chiquier
    private ArrayList<Piece> deadPiece; //Contient les pi�ces captur�es
    private int selectedX;				//Indique la position de la piece selectionner en X
    private int selectedY;				//Indique la position de la piece selectionner en Y
    
    private int ghostPieceX;	//Indique la position de la piece fantome (pour la prise en passant)
    private int ghostPieceY;	//Indique la position de la piece fantome (pour la prise en passant)
    
    //Concerve la position des roi (pour la verification d'echec)
    private int whiteKingX;
    private int whiteKingY;
    private int blackKingX;
    private int blackKingY;
    

	public Board(){        
        board = new Piece[BOARD_SIZE][BOARD_SIZE];
        deadPiece = new ArrayList<Piece>();
        selectedX = NOT_SELECTED;
        selectedY = NOT_SELECTED;

        ghostPieceX = NOT_SELECTED;
        ghostPieceY = NOT_SELECTED;
        
        whiteKingX = 4;
        whiteKingY = 0;
        blackKingX = 4;
        blackKingY = 7;
        
        board[0][0] = new Pion(Player.Color.WHITE);
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
   
   // Retourne si la pièce de la position en x et en y est la même pièce que la pièce présentemment sélectionné
   public boolean equalsPieceSelected(int x, int y)
   {
       return (getPiece(x, y) == getSelectedPiece());
   }
   
   // Retourne le board sous la forme d'énuméré (on ne retourne pas les pointeurs de Pieces, ce n'est pas nécessaire)
   public LinkedList<IRenderable> getBoard()
   {
       LinkedList<IRenderable> pieces = new LinkedList<IRenderable>();
       
       for (int x = 0; x < board.length; ++x)
           for (int y = 0; y < board[x].length; ++y)
               pieces.add((IRenderable) getPiece(x, y));                   
                  
       return pieces;
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
	public boolean moveSelectedPieceTo(int x, int y, Player.Color color){			
	    if (pieceIsSelected())
	    {
	        Piece movingPiece = getSelectedPiece();
	        Piece catchedPiece;
	        PiecePattern movementPatternFromMovingPiece = movingPiece.getPattern(selectedX, selectedY, x, y);
	        
	        if (movementPatternFromMovingPiece != null && canMovePiece(movementPatternFromMovingPiece, x, y))
	        {	        		
        		//d�placement special pour le roque
        		if(isTryingToCastling(getPiece(x,y))){
        			
        			DoCastling(x,y);
        			
        		}else{
        			board[finalPosX][ghostPosY] = PieceFactory.getInstance().giveGhostPiece(piece.getColor());
        			
	        		catchedPiece = board[x][y];
        			board[x][y] = movingPiece;
	                board[selectedX][selectedY] = null;
	                
	              	//Verification de la prise en passant
	                doEnPassant(x, y);
	                tryPutGhostPiece(x, y, selectedY);
	                
	                //Si l'action met le roi en echec, undo
        			if(accecibleByEnnemy(0,0,color)){ //<-----------------------------------------place la position du roi
        				//Il faut undo le d�placement
        			}else{ 
	        			if(catchedPiece != null) {
	        				deadPiece.add(catchedPiece);
		        		}
		        		
		        		//Verification de la promotion
		                if(canDoPromotion(x,y)){
		                	getPromotablePiece(board[x][y].getColor());
		                }
        			}
    			}
    		}
    		
    		movingPiece.hasMoved();
            
            unselectPiece(); 
            unselectGhostPiece();
    	}
    }
	
	public void promote(int x, int y, String pieceName){
		board[x][y] = PieceFactory.getInstance().givePromotatePiece(pieceName);
	}
	
	public void highlightPossibleMoves()
	{
		PiecePattern[] patterns = this.getSelectedPiece().getPatterns();
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
		return getPiece(selectedX,selectedY);
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
	
	private boolean canDoPromotion(int x, int y) {
		Piece piece = getPiece(x, y);
		if (piece instanceof Pion) {
			if (piece.getColor() == Player.Color.WHITE && y == 7)
				return true;
			if (piece.getColor() == Player.Color.BLACK && y == 0)
				return true;
		}
		return false;
	}
	
	private ArrayList<Piece> getPromotablePiece(Player.Color color) {
		
		ArrayList<Piece> promotablePiece = new ArrayList<Piece>();
		
		for(Piece piece: deadPiece){
			if(!(piece instanceof Pion) && (piece.getColor() == color)){
				promotablePiece.add(piece);
			}
		}
		
		return promotablePiece;	
	}
	
	private void unselectGhostPiece(){
		ghostPieceX = NOT_SELECTED;
		ghostPieceY = NOT_SELECTED;
	}
	
	//Tante de placer une piece fantome si c'est nescessaire
	private void tryPutGhostPiece(int finalPosX, int finalPosY, int startPosY){
		Piece piece = getPiece(finalPosX, finalPosY);
		int ghostPosY;
		
		if(piece instanceof Pion){
			if(((finalPosY - startPosY)%2) == 0){
				ghostPosY = finalPosY - ((startPosY - finalPosY)/Math.abs(startPosY - finalPosY));
				board[finalPosX][ghostPosY] = PieceFactory.getInstance().giveGhostPiece(piece.getColor());
				ghostPieceX = finalPosX;
				ghostPieceY = ghostPosY;
			}
		}
	}
	
	//Tante de faire une prise en passant
	private void doEnPassant(int posX, int posY){
		//Si une prise en passant est fait
		if((posX == ghostPieceX) && (posY == ghostPieceY)){
			if(posY == 1){
				deadPiece.add(getPiece(posX,posY+1));
				board[posX][posY+1] = null;
			}else{
				deadPiece.add(getPiece(posX,posY-1));
				board[posX][posY-1] = null;
			}
		}
	}
	
	//Cette method s'assure que la piece peut se d�placer jusqu'� la possition souhaiter
	private boolean canMovePiece(PiecePattern pattern, int finalPosX, int finalPosY){
		
		boolean result = false;
		int posX = selectedX;
		int posY = selectedY;
		int jumpCount = 0;			//Permet de ne jamais d�pass� le nombre de saut d'un paterne
		Piece finalPosPiece = getPiece(posX, posY);		//contient la piece � la position final

		do{
			posX += pattern.getDirectionX();
			posY += pattern.getDirectionY();
			
			++jumpCount;		//Permet le d�pacement du nombre de bond pour faire la v�rification du roque
			
			if((posX == finalPosX) && (posY == finalPosY)){
				result = true;
			}
			
		}while(!result && (getPiece(posX, posY) != null));
		
		//Si l'usage ne tante pas de faire un roque, on s'assure que la piece ne
		if(!isTryingToCastling(finalPosPiece)){
			result = result && (jumpCount < pattern.getDistanceMax());
		}
		
		//Si le pattern ne peut pas bouger et attaquer en m�me temps (g�re l'exeption du pion)
		if(!(pattern.isAttackPattern() && pattern.isMouvementPattern()) && result){
			
			//Si le pattern est exclusivement pour les attaque
			if(pattern.isAttackPattern()){
				if(finalPosPiece == null){
					result = false;
				}
			}
			//Si le pattern est exclusivement pour les d�placements
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
			//Verifie que les deux pieces soit de la m�me couleur, sinon ca ne sert � rien
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
		
		//Retire le mouvement sp�cial de la tour non utilis�
		if(castleX == 0){
			tempoPiece = getPiece(7,y);
		}else{
			tempoPiece = getPiece(0,y);
		}
		
		//Retire le mouvement special de la tour non utilis� et de la piece d'arriver : la piece selectionner se fait au retour
		if(tempoPiece != null){ tempoPiece.hasMoved(); }
		getPiece(x,y).hasMoved();
		
		//Verifie si le grand roque doit �tre fait
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
	
	
	//Verifie si une position peut �tre attaquer par une piece
	private boolean accecibleByEnnemy(int x, int y, Player.Color color){
		
		boolean isAccecible = false;
		boolean	FindPiece;
		int PosX, PosY, jumpCount; 
		PiecePattern tempoPattern;
		
		for(PiecePattern pattern: PatternFactory.getInstance().getAllPattern()){
			PosX = x;
			PosY = y;
			jumpCount = 0;
			FindPiece = false;
			
			do
			{
				PosX += pattern.getDirectionX();
				PosY += pattern.getDirectionY();
				
				if(getPiece(PosX,PosY) != null){
					FindPiece = true;
					if(getPiece(PosX,PosY).getColor() == color){
						tempoPattern = getPiece(PosX,PosY).getPattern(PosX, PosY, x, y);
					
						if((tempoPattern!= null) && (jumpCount < tempoPattern.getDistanceMax())){
							isAccecible = true;
						}
					}
				}
				
				jumpCount++;
			}while(!isAccecible && (jumpCount < pattern.getDistanceMax()) && !FindPiece);
		}
		
		return isAccecible;
	}
	
	
	
	
	
	
}
