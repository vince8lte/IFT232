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

	public Board(){        
        board = new Piece[BOARD_SIZE][BOARD_SIZE];
        selectedX = NOT_SELECTED;
        selectedY = NOT_SELECTED;
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
                final int posX = 0;
                final int posY = 1;
                final int pieceType = 2;
                final int pieceColor = 3;
                
                String[] infos = ligne.split(",");
                
                switch (infos[pieceType])
                {
                    case "Pion":
                        board[Integer.parseInt(infos[posX])][Integer.parseInt(infos[posY])] = new Pion(Player.Color.valueOf(infos[pieceColor]));
                        break;
                    case "Fou":
                        board[Integer.parseInt(infos[posX])][Integer.parseInt(infos[posY])] = new Fou(Player.Color.valueOf(infos[pieceColor]));
                        break;
                    case "Reine":
                        board[Integer.parseInt(infos[posX])][Integer.parseInt(infos[posY])] = new Reine(Player.Color.valueOf(infos[pieceColor]));
                        break;
                    case "Roi":
                        board[Integer.parseInt(infos[posX])][Integer.parseInt(infos[posY])] = new Roi(Player.Color.valueOf(infos[pieceColor]));
                        break;
                    case "Cavalier":
                        board[Integer.parseInt(infos[posX])][Integer.parseInt(infos[posY])] = new Cavalier(Player.Color.valueOf(infos[pieceColor]));
                        break;
                    case "Tour":
                        board[Integer.parseInt(infos[posX])][Integer.parseInt(infos[posY])] = new Tour(Player.Color.valueOf(infos[pieceColor]));
                        break;
                    default:
                        break;
                }
                
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
			for (int x = 0; x < board.length; ++x)
				for (int y = 0; y < board[x].length; ++y)
				{
					Piece currentPiece = getPiece(x, y);		
					
					if (currentPiece != null)
					{
						String currentPieceInfos = Integer.toString(x) + "," + 
								   Integer.toString(y) + "," + 
								   currentPiece.getClass().getSimpleName() + "," +
								   currentPiece.getColor().toString();
		
						fileToWrite.write(currentPieceInfos);
						fileToWrite.newLine();								
					}
				}
			
			fileToWrite.close();			
		}
		catch (Exception e)
		{}
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
               pieces.add(getPiece(y, x));                   
                  
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
	public boolean moveSelectedPieceTo(int x, int y){	
	    if (pieceIsSelected())
	    {
	        Piece movingPiece = getSelectedPiece();
	        PiecePattern movementPatternFromMovingPiece = movingPiece.getPattern(selectedX, selectedY, x, y);
	        
	        if (movementPatternFromMovingPiece != null && canMovePiece(movementPatternFromMovingPiece, x, y))
	        {
                board[x][y] = board[selectedX][selectedY];
                board[selectedX][selectedY] = null;	        		

                movingPiece.hasMoved();                 
                unselectPiece();          
                
                return true;
        		//if(isTryingToCastling(getPiece(x,y))){ APPREND A CODER SUR PAPIER PATRICK - JEAN
        		//}else{}		        	
	        } 
	        else
	            return false;
	    }
	    else
	        return false;
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
	
	//Cette method s'assure que la piece peut se d�placer jusqu'� la possition souhaiter
	private boolean canMovePiece(PiecePattern pattern, int finalPosX, int finalPosY){
		
		boolean result = false;
		int posX = selectedX;
		int posY = selectedY;
		int jumpCount = 0;			//Permet de ne jamais d�pass� le nombre de saut d'un paterne
		Piece piecePosFinale = getPiece(posX, posY);		//contient la piece � la position final
		
		do{
			posX += pattern.getDirectionX();
			posY += pattern.getDirectionY();
			
			++jumpCount;		//Permet le d�pacement du nombre de bond pour faire la v�rification du roque
			
			if((posX == finalPosX) && (posY == finalPosY)){
				result = true;
			}
			
		}while(!result && (getPiece(posX, posY) != null));
		
		//Si l'usage ne tante pas de faire un roque, on s'assure que la piece ne
		if(!isTryingToCastling(piecePosFinale)){
			result = result && (jumpCount <= pattern.getDistanceMax());
		}
		
		//Si le pattern ne peut pas bouger et attaquer en m�me temps (g�re l'exeption du pion)
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
