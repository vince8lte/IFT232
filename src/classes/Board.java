package classes;

import java.util.ArrayList;
import java.util.List;

import Piece.*;

public class Board {

	//Constantes
	//Fusionner "OOB & !Selected?
	final int DEFAULT_BOARD_SIZE = 8;
	final int NOT_SELECTED = -1;
	final int OUT_OF_BOUND = -10;

	//conserve la position du premier clic
	private int posXSelect;
	private int posYSelect;

	//Contient la taille maximal du board
	private int boardSizeX;
	private int boardSizeY;

	//Contient le tableau de piece
	Piece[][] board;

	//a revoir (si on veut ca beau.....)
	public Board(){

		this.boardSizeX = DEFAULT_BOARD_SIZE;
		this.boardSizeY = DEFAULT_BOARD_SIZE;

		posXSelect = NOT_SELECTED;
		posYSelect = NOT_SELECTED;

		board = new Piece[boardSizeX][boardSizeY];

		//remplissage du board. 
		//		>^.^<	meow
		for (int y = 0; y < board.length; ++y)
		{
			if(y==1 || y==6){
				if(y%2==0){
					for(int x =0;x<board.length;++x){
						board[x][y]=new Pion(false, this);
					}
				}else{
					for(int x =0;x<board.length;++x){
						board[x][y]=new Pion(true, this);
					}
				}
			}
			else if(y==0 || y==7){
				if(y%2==0){
					board[0][y] = new Tour(true, this);
					board[1][y] = new Cavalier(true, this);
					board[2][y] = new Fou(true, this);
					board[3][y] = new Roi(true, this);
					board[4][y] = new Reine(true, this);
					board[5][y] = new Fou(true, this);
					board[6][y] = new Cavalier(true, this);
					board[7][y] = new Tour(true, this);
				}else{
					board[0][y] = new Tour(false, this);
					board[1][y] = new Cavalier(false, this);
					board[2][y] = new Fou(false, this);
					board[3][y] = new Reine(false, this);
					board[4][y] = new Roi(false, this);
					board[5][y] = new Fou(false, this);
					board[6][y] = new Cavalier(false, this);
					board[7][y] = new Tour(false, this);
				}
			}
		}
	}


		//Methode qui renvoie les informations nescessaire a afficher le board
		public List<Object[]> getBoardImage(){

			List<Object[]> result = new ArrayList<Object[]>();
			Object[] Information;

			//Parcour le board pour chercher les piece a afficher
			for(int x = 0; x < DEFAULT_BOARD_SIZE; x++){
				for(int y = 0; y < DEFAULT_BOARD_SIZE; y++){
					if(getPieceAt(x, y) != null){
						Information = new Object[3];
						Information[0] = getPieceAt(x, y).getImgUrl();
						Information[1] = x;
						Information[2] = y;
						result.add(Information);
					}
				}
			}

			return result;		
		}

		//Fonction qui gere le mouvement de piece
		public void move(int x, int y){
			// si on a cliquer sur une piece
			if(haveSelectedPiece())
			{
				// Si on reclique sur la piece selectionner, on veut d�selectionner la piece presentement selectionne.
				if(getPieceAt(x, y) == getSelectedPiece())
					unselectPiece();
				else
					movePiece(x, y);                	                	
			}               
			else {
				selectPiece(x, y);
			}
		}

		//Retourne la piece � une position donner x et y
		public Piece getPieceAt(int posX, int posY)
		{
			if (posX >= 0 && posX < this.boardSizeX &&
					posY >= 0 && posY < this.boardSizeY)
				return board[posX][posY];
			else
				return null;
		}

		// Retourne si une pi�ce est pr�sentement s�lectionn� ou pas
		private boolean haveSelectedPiece(){
			return ((posXSelect != NOT_SELECTED) && (posYSelect != NOT_SELECTED));
		}

		//Retourne la piece selectionner
		private Piece getSelectedPiece() {
			return getPieceAt(this.posXSelect, this.posYSelect);
		}

		//Change la piece selectionner
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

		//Deselectionne une piece
		private void unselectPiece()
		{
			posXSelect = NOT_SELECTED;
			posYSelect = NOT_SELECTED;
		}

		//Deplace une piece sur le board si on peux selon ce qui se trouve a la destination
		private void movePiece(int posX, int posY)
		{	
			if(getPieceAt(posX, posY) !=null)
			{
				Piece targetPiece = getPieceAt(posX, posY);
				if((getSelectedPiece().isWhite() && !targetPiece.isWhite()) || (!getSelectedPiece().isWhite() && targetPiece.isWhite()))
				{
					if(getSelectedPiece().canAttack(posXSelect - posX, posYSelect - posY))
					{
						this.board[posX][posY] = getSelectedPiece();
						this.board[posXSelect][posYSelect] = null;
						unselectPiece();
						return;
					}
					else
						System.out.println("i can't attack this piece its out of reach for my attack move!!");

				}else
					System.out.println("i wont attack meh friends!");

			}
			else
			{
				if(getSelectedPiece().canMove(posXSelect - posX, posYSelect - posY))
				{
					this.board[posX][posY] = getSelectedPiece();
					this.board[posXSelect][posYSelect] = null;
					unselectPiece();
				}
				else
					System.out.println("im 2 lay-Z 2 move there, bro");
			}
		}
		
		public int[] getSelectedPosition()
		{
			int[] result = new int[2];
			if(!this.haveSelectedPiece())
			{
				result[0] = OUT_OF_BOUND;
				result[1] = OUT_OF_BOUND;
			}
			else
			{
				
				result[0] = this.posXSelect;
				result[1] = this.posYSelect;
			}
			
			return result;
		}
		
		
	}
