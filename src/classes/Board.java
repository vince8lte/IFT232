package classes;

import java.awt.Color;
import java.awt.Image;
import java.awt.Point;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
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
    
    private int whiteKingX;
    private int whiteKingY;
    private int blackKingX;
    private int blackKingY;

	public Board(){        
		this.board = new Piece[BOARD_SIZE][BOARD_SIZE];
        this.selectedX = NOT_SELECTED;
        this.selectedY = NOT_SELECTED;
        this.ghostPieceX = NOT_SELECTED;
        this.ghostPieceY = NOT_SELECTED;
        
        this.whiteKingX = 4;
        this.whiteKingY = 0;
        this.blackKingX = 4;
        this.blackKingY = 7;
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
   
   public IRenderable[][] getHighlightedSquares() {
       IRenderable[][] highlightedSquares = new IRenderable[8][8];
       
       if (this.pieceIsSelected()) {
    	   int posX, posY, jumpCount;
           Piece piece = this.getSelectedPiece();
           PiecePattern[] patterns = piece.getPatterns();
           Point piecePos = new Point(selectedX, selectedY);
           highlightedSquares[piecePos.y][piecePos.x] = new HighlightedSquare(Color.BLUE);
           for (PiecePattern pattern : patterns) {
        	   posX = piecePos.x;
        	   posY = piecePos.y;
        	   jumpCount = 0;
               do {
                   posX += pattern.getDirectionX();
                   posY += pattern.getDirectionY();
                   if (isInChess(posX, posY)) {
                	   
                	   if (this.getPiece(posX, posY) == null){
                		  if(pattern.isMouvementPattern()) {
                			  highlightedSquares[posY][posX] = new HighlightedSquare(Color.BLUE);
                		  }
                       }
                       else {
                           if (!(this.getPiece(posX, posY).getColor() == piece.getColor()) && pattern.isAttackPattern()) {
                               highlightedSquares[posY][posX] = new HighlightedSquare(Color.RED);
                           }
                       }
                   }
                   ++jumpCount;
               }while((this.getPiece(posX, posY) == null) && (jumpCount < pattern.getDistanceMax()));
           }       
		   
			//Exeption pour le roque, compliquer mais le plus simple possible
			if(getSelectedPiece().canSpecialMove() && !(getSelectedPiece() instanceof Pion)){
				if((selectedX != 0) && (getPiece(4,selectedY) != null) && (getPiece(7,selectedY) != null)){
					//verifie le petit roque
					if((board[5][selectedY] == null) && (board[6][selectedY] == null) && 
							getPiece(4,selectedY).canSpecialMove() && getPiece(7,selectedY).canSpecialMove()){
						if(selectedX == 4){
							highlightedSquares[selectedY][7] = new HighlightedSquare(Color.BLUE);
						}else{
							highlightedSquares[selectedY][4] = new HighlightedSquare(Color.BLUE);
						}
					}
				}
				if((selectedX != 7) && (getPiece(0,selectedY) != null) && (getPiece(4,selectedY) != null)){
					//verifie le grand roque
					if((board[1][selectedY] == null) && (board[2][selectedY] == null) && (board[3][selectedY] == null) && 
							getPiece(4,selectedY).canSpecialMove() && getPiece(0,selectedY).canSpecialMove()){
						if(selectedX == 4){
							highlightedSquares[selectedY][0] = new HighlightedSquare(Color.BLUE);
						}else{
							highlightedSquares[selectedY][4] = new HighlightedSquare(Color.BLUE);
						}
					}
				}
			}
       }
       
       return highlightedSquares;
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
        		return DoCastling(x, y);
        	}
        	else{
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
        		
	            board[x][y] = movingPiece;
	            board[selectedX][selectedY] = null;
	            
	            if(movingPiece instanceof Roi){
	            	if(movingPiece.getColor() == Player.Color.BLACK){
	                    this.blackKingX = x;
	                    this.blackKingY = y;
	            	}else{
	            		this.whiteKingX = x;
	                    this.whiteKingY = y;
	            	}
	            }
	            
	            if(kingIsCheck(color)){
	            	board[selectedX][selectedY] = movingPiece;
	            	if((ghostPiece != null) && (y==2)){
	            		board[x][y] = ghostPiece;
	            		board[x][y+1] = catchedPiece;
	            	}
	            	else if((ghostPiece != null) && (y==5)){
	            		board[x][y] = ghostPiece;
	            		board[x][y-1] = catchedPiece;
	            	}
	            	else{
	            		board[x][y] = catchedPiece;
	            		if(movingPiece instanceof Roi){
	    	            	if(movingPiece.getColor() == Player.Color.BLACK){
	    	                    this.blackKingX = selectedX;
	    	                    this.blackKingY = selectedY;
	    	            	}else{
	    	            		this.whiteKingX = selectedX;
	    	                    this.whiteKingY = selectedY;
	    	            	}
	    	            }
	            	}
	            	return false;
	            }else{
		            //Gestion de la prise en passant
		            unselectGhostPiece();
	                tryPutGhostPiece(x, y, selectedY);
	            }
        	}

            movingPiece.hasMoved();                 
            unselectPiece();          
            
            return true;        	
        } 
        else
            return false;
	}
	
	public boolean isCheckmate(Player.Color color){
		
		boolean canEscape = true;
		int posX, posY, kingX, kingY, iPattern, dirX, dirY, jumpCount;
		PiecePattern ennemyPattern = null;
		PiecePattern[] patterns;
		Player.Color ennemyColor;
		
		if(kingIsCheck(color)){
			
			patterns = PatternFactory.getInstance().getAllPattern();
			iPattern =0;
			
			if(color == Player.Color.WHITE){
				kingX = whiteKingX;
				kingY = whiteKingY;
				ennemyColor = Player.Color.BLACK;
			}else{
				kingX = blackKingX;
				kingY = blackKingY;
				ennemyColor = Player.Color.WHITE;
			}
			
			//Rep�re la piece qui met le roi en echec
			do{
				jumpCount = 0;
				posX = kingX;
				posY = kingY;
				dirX = patterns[iPattern].getDirectionX();
				dirY = patterns[iPattern].getDirectionY();
				
				do{
					++jumpCount;
					posX += dirX;
					posY += dirY;
					if((getPiece(posX,posY) != null) && !(getPiece(posX,posY) instanceof Fantome)){
						if(!(getPiece(posX,posY).getColor() == color)){
							ennemyPattern = getPiece(posX,posY).getPattern(-(dirX), -(dirY));
						}
					}
				}while((getPiece(posX,posY) == null) && isInChess(posX,posY));
				++iPattern;
			}while(ennemyPattern == null);
			
			//Tente de defendre le roi, par bloquage
			while((jumpCount > 1) && (!accecibleByEnnemy(posX, posY, color))){
				--jumpCount;
				posX += ennemyPattern.getDirectionX();
				posY += ennemyPattern.getDirectionY();
			}
			
			canEscape = accecibleByEnnemy(posX, posY, color);
			
			
		
			//Si aucune pi�ce peut bloquer la pi�ce ennemie, on tente de bouger le roi
			if(!canEscape){
				for(int x = -1; x < 2; ++x){
					for(int y = -1; y < 2; ++y){
						if(!canEscape && isInChess(kingX + x, kingY + y) && (getPiece(kingX + x, kingY + y) == null)){
							if(!accecibleByEnnemy(kingX + x, kingY + y, ennemyColor)){
								canEscape = true;
							}
						}
					}
				}
			}
			
			
			//Verifie qu'un roque peut le sauv�
			if(!canEscape && getPiece(kingX,kingY).canSpecialMove()){
				if((getPiece(0,kingY).canSpecialMove()) && (board[1][kingY] == null) && (board[2][kingY] == null) && (board[3][kingY] == null)){
					canEscape = true;
				}
				if((getPiece(7,kingY).canSpecialMove()) && (board[6][kingY] == null) && (board[5][kingY] == null)){
					canEscape = true;
				}
			}
			
		}
		return !canEscape;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Board other = (Board) obj;
		if (BOARD_SIZE != other.BOARD_SIZE)
			return false;
		if (blackKingX != other.blackKingX)
			return false;
		if (blackKingY != other.blackKingY)
			return false;
		if (!Arrays.deepEquals(board, other.board))
			return false;
		if (selectedX != other.selectedX)
			return false;
		if (selectedY != other.selectedY)
			return false;
		if (whiteKingX != other.whiteKingX)
			return false;
		if (whiteKingY != other.whiteKingY)
			return false;
		return true;
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
	private boolean DoCastling(int x, int y){
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
		if(tempoPiece != null){ 

			if (getSelectedPiece().canSpecialMove() && tempoPiece.canSpecialMove()){
	
				tempoPiece.hasMoved();
			
				getPiece(x,y).hasMoved();
				
				//Verifie si le grand roque doit �tre fait
				if(castleX < kingX){
					board[2][y] = board[kingX][y];	//Deplace le roi
					board[kingX][y] = null;
					board[3][y] = board[castleX][y];	//Deplace la tour
					kingX = 2;
				}else{
					board[6][y] = board[kingX][y];	//Deplace le roi
					board[kingX][y] = null;
					board[5][y] = board[castleX][y];	//Deplace la tour
					kingX = 6;
				}
		
				board[castleX][y] = null;
				unselectPiece();
				
				//Met a jour la r�f�rence du roi
				if(getPiece(kingX,y).getColor() == Player.Color.WHITE){
					this.whiteKingX = kingX;
				}else{
					this.blackKingX = kingX;
				}
				
				return true;
			}
		}
		return false;
	}
	
	private void unselectGhostPiece(){		
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
	
	private boolean kingIsCheck(Player.Color color){
		if(color == Player.Color.WHITE){
			return accecibleByEnnemy(whiteKingX, whiteKingY, Player.Color.BLACK);
		}else{
			return accecibleByEnnemy(blackKingX, blackKingY, Player.Color.WHITE);
		}
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
				
				if((getPiece(PosX,PosY) != null) && !(getPiece(PosX,PosY) instanceof Fantome)){
					FindPiece = true;
					if(getPiece(PosX,PosY).getColor() == color){
						tempoPattern = getPiece(PosX,PosY).getPattern(-(PosX - x), -(PosY - y));
						if((tempoPattern!= null) && (jumpCount < tempoPattern.getDistanceMax())){
							if(!(getPiece(PosX,PosY) instanceof Roi) || ((getPiece(PosX,PosY) instanceof Roi) && (getPiece(x,y) != null))){
								if(tempoPattern.isAttackPattern() && (getPiece(x,y) != null)){
									isAccecible = true;
								}
								else if(tempoPattern.isMouvementPattern() && (getPiece(x,y) == null)){
									isAccecible = true;
								}
							}
						}
					}
				}
				
				jumpCount++;
			}while(!isAccecible && (jumpCount < pattern.getDistanceMax()) && !FindPiece);
		}
		
		return isAccecible;
	}

	
}
