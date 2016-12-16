package classes;

import java.awt.Image;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.LinkedList;

import GraphicsInterface.IRenderable;
import Piece.*;

// Cette classe contient les informations spécifiques par rapport au board.
// Cette classe contient les règles du jeu d'échec.
public class Board {
    private final int BOARD_SIZE = 8;
    private final int NOT_SELECTED = -1;
    
    private Piece[][] board;		//Contient la position de toute les pieces de l'�chiquier
    private int selectedX;				//Indique la position de la piece selectionner en X
    private int selectedY;				//Indique la position de la piece selectionner en Y
    
    private int ghostPieceX;	//Indique la position de la piece fantome (pour la prise en passant)
    private int ghostPieceY;	//Indique la position de la piece fantome (pour la prise en passant)

	public Board(){        
        board = new Piece[BOARD_SIZE][BOARD_SIZE];
        selectedX = NOT_SELECTED;
        selectedY = NOT_SELECTED;
        ghostPieceX = NOT_SELECTED;
        ghostPieceY = NOT_SELECTED;
	}
	
	public void loadBoard(BufferedReader fileToRead)
	{
		// Effacer toutes les pieces du board pr�sent avant de continuer
		for (int x = 0; x < board.length; ++x)
			for (int y = 0; y < board[x].length; ++y)
				board[x][y] = null;
		
	    try
        {
	        String ligne;
	        
            ligne = fileToRead.readLine();
	    
            while (ligne != null)
            {
            	String[] infos = ligne.split(",");
            	int posX = Integer.parseInt(infos[0]);
            	int posY = Integer.parseInt(infos[1]);
            	
            	board[posX][posY] = PieceFactory.getInstance().givePiece(infos[2], Player.Color.valueOf(infos[3]));
                
                ligne = fileToRead.readLine();	                
            }
            
            fileToRead.close(); 
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }       
	}
	
	public void saveBoard(BufferedWriter fileToWrite)
	{
		try
		{
			for (int i = 0; i < (board.length * board.length); ++i){
				Piece currentPiece = getPiece(i%8, i/8);		
				
				if (currentPiece != null)
				{
					String currentPieceInfos = Integer.toString(i%8) + "," + Integer.toString(i/8) + "," + currentPiece.toString();
	
					fileToWrite.write(currentPieceInfos);
					fileToWrite.newLine();								
				}
			}
			fileToWrite.close();			
		}
		catch (Exception e)
		{}
	}
	
	public boolean selectPiece(int x, int y, Player.Color color)
	{
	    if (getPiece(x, y) != null && (getPiece(x,y).getColor() == color))
	    {
	        selectedX = x;
	        selectedY = y;	 
	        
	        return true;
	    }
	    return false;
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
       
       for (int i = 0; i < (board.length*board.length); ++i)
               pieces.add(getPiece(i%8, i/8));                   
                  
       return pieces;
   }
   
   // Cette procedure deplace la piece presentement selectionne a lendroit specifie recu en parametre.
	public boolean moveSelectedPieceTo(int x, int y, Player.Color color){	
        Piece movingPiece = getSelectedPiece();
        Piece catchedPiece = null;
        Piece ghostPiece = null;
        PiecePattern movementPatternFromMovingPiece = movingPiece.getPattern(x - selectedX, y - selectedY);
        if ((movementPatternFromMovingPiece != null) && canMovePiece(movementPatternFromMovingPiece, x, y, color))
        {        	        	
        	if(isTryingToCastling(getPiece(x,y))){
        		DoCastling(x, y);
        	} else{
        		catchedPiece = getPiece(x,y);
        		
        		if((catchedPiece instanceof Fantome) && (y==2)){
        			System.out.println("prise");
        			ghostPiece = catchedPiece;
        			catchedPiece = getPiece(x,y+1);
        			board[x][y+1] = null;
        		}
        		else if((catchedPiece instanceof Fantome) && (y==5)){
        			System.out.println("prise");
        			ghostPiece = catchedPiece;
        			catchedPiece = getPiece(x,y-1);
        			board[x][y-1] = null;
        		}
        		
	            board[x][y] = getSelectedPiece();
	            board[selectedX][selectedY] = null;
	            

	            //Gestion de la prise en passant
	            unselectGhostPiece();
                tryPutGhostPiece(x, y, selectedY);
        	}

            movingPiece.hasMoved();                 
            unselectPiece();          
            
            return true;        	
        } 
        else
            return false;
	}
	
	
	//Renvoie le highlight des mouvements possible
	public LinkedList<IRenderable> getHighlightedMoves()
	{
		LinkedList<IRenderable> highlight = new LinkedList<IRenderable>();
		
		PiecePattern[] patterns = this.getSelectedPiece().getPatterns();
		int x, y;		//Contient le positionnement de la v�rification
		Piece tempPiece;
		int nbrSaut;
		
		//boucle sur tout les parttern de la piece
		for ( int i =0 ; i < patterns.length ; i++)
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
				
				tempPiece = getPiece(x, y);
				if (tempPiece == null)
				{
					if(patterns[i].isMouvementPattern()){
						highlight.add(tempPiece);
					}
				}
				else
				{
					if((patterns[i].isAttackPattern()) && (tempPiece.getColor() != getSelectedPiece().getColor())){
						highlight.add(tempPiece);
					}
				}				
				
				nbrSaut++;
				
			}while((tempPiece == null) && (nbrSaut<patterns[i].getDistanceMax()));
			
			//Exeption pour le roque, compliquer mais le plus simple possible
			if(getSelectedPiece().canSpecialMove() && !(getSelectedPiece() instanceof Pion)){
				if((selectedX != 0) && (getPiece(4,selectedY) != null) && (getPiece(7,selectedY) != null)){
					if((board[5][selectedY] == null) && (board[6][selectedY] == null) && 
							getPiece(4,selectedY).canSpecialMove() && getPiece(7,selectedY).canSpecialMove()){
						if(selectedX == 4){
							highlight.add(getPiece(7,selectedY));
						}else{
							highlight.add(getPiece(4,selectedY));
						}
					}
				}
				else if((selectedX != 7) && (getPiece(0,selectedY) != null) && (getPiece(4,selectedY) != null)){
					if((board[1][selectedY] == null) && (board[2][selectedY] == null) && (board[3][selectedY] == null) && 
							getPiece(0,selectedY).canSpecialMove() && getPiece(4,selectedY).canSpecialMove()){
						if(selectedX == 4){
							highlight.add(getPiece(0,selectedY));
						}else{
							highlight.add(getPiece(4,selectedY));
						}
					}
				}
			}
		}
		
		return highlight;
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
	
	//Cette method s'assure que la piece peut se deplacer jusqu'a la possition souhaiter
	private boolean canMovePiece(PiecePattern pattern, int finalPosX, int finalPosY, Player.Color color){
		
		boolean result = false;
		int posX = selectedX;
		int posY = selectedY;
		int jumpCount = 0;			//Permet de ne jamais depace le nombre de saut d'un paterne
		Piece piecePosFinale = getPiece(finalPosX, finalPosY);		//contient la piece a la position final
		
		if(pattern != null){
			do{
				posX += pattern.getDirectionX();
				posY += pattern.getDirectionY();
				
				++jumpCount;		//Permet le d�pacement du nombre de bond pour faire la v�rification du roque
				
				if((posX == finalPosX) && (posY == finalPosY)){
					result = true;
				}
				
			}while(!result && (getPiece(posX, posY) == null));
			
			//Si l'usage ne tante pas de faire un roque, on s'assure que la piece n'est pas hors de port� et l'arriver n'est pas une
			//piece de meme couleur
			if(!isTryingToCastling(piecePosFinale)){
				if(result && (piecePosFinale != null)){
					result = (piecePosFinale.getColor() != color);
				}
				
				result = result && (jumpCount <= pattern.getDistanceMax());
			}
			
			//Si le pattern ne peut pas bouger et attaquer en meme temps (gere l'exeption du pion)
			if(!(pattern.isAttackPattern() && pattern.isMouvementPattern()) && result){
				
				//Si le pattern est exclusivement pour les attaque
				if(pattern.isAttackPattern()){
					if(piecePosFinale == null){
						result = false;
					}
				}
				//Si le pattern est exclusivement pour les d�placements
				if(pattern.isMouvementPattern()){
					if(piecePosFinale != null){
						result = false;
					}
				}
			}
		}
		
		return result;
	}
	
	//Verifie si l'usager tante d'effectuer le roque
	private boolean isTryingToCastling(Piece pieceFinalPos){
		boolean result = false;
		Piece SelectedPiece = getSelectedPiece();
		
		if((SelectedPiece != null) && (pieceFinalPos != null)){
			//Verifie que les deux pieces soit de la m�me couleur, sinon ca ne sert � rien
			if(SelectedPiece.getColor() == pieceFinalPos.getColor()){
			
				//Verifie que les piec soit des instances de roi et tour
				if(((SelectedPiece instanceof Roi) && (pieceFinalPos instanceof Tour)) 
						||((SelectedPiece instanceof Tour) && (pieceFinalPos instanceof Roi))){
					result = true;
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
		
		
		private void unselectGhostPiece(){
			System.out.println("supprime la piece fantome");
			
			if(getPiece(ghostPieceX, ghostPieceY) instanceof Fantome){
				board[ghostPieceX][ghostPieceY] = null;
			}
			
			ghostPieceX = NOT_SELECTED;
			ghostPieceY = NOT_SELECTED;
		}
		
		//Tante de placer une piece fantome si c'est nescessaire
		private void tryPutGhostPiece(int finalPosX, int finalPosY, int startPosY){
			Piece piece = getPiece(finalPosX, finalPosY);
			int ghostPosY;
			
			if(piece instanceof Pion){
				if(((finalPosY - startPosY)%2) == 0){
					ghostPosY = finalPosY - ((finalPosY - startPosY)/Math.abs(finalPosY - startPosY));
					board[finalPosX][ghostPosY] = PieceFactory.getInstance().giveGhostPiece(piece.getColor());
					System.out.println("ghost at" + finalPosX +","+ghostPosY);
					ghostPieceX = finalPosX;
					ghostPieceY = ghostPosY;
				}
			}
		}
}
