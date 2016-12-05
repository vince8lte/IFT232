package Piece;

import classes.Board;

public class Fou extends Piece {

	public Fou(boolean isWhite, Board b) {
		super(isWhite, b);
		this.imgURL = "ressources/pictures/fou";
		//Permet la construction du lien vers la bonne image de la piece
		if(this.isWhite){
			this.imgURL += "b.png";
		}else{
			this.imgURL += "n.png";
		}
	}

	@Override
	public boolean canMove(int x, int y) {

		boolean resultat = false;
		if( Math.abs(x) == Math.abs(y) && freeWay(x,y,super.getBoard()))
		{
			resultat = true;
		}

		return resultat;
	}
	public boolean canAttack(int x, int y){
		return canMove(x,y);
	}

	/*
	 * DOIT ETRE REVU (Si on veut ca beau ......)
	 * @see Piece.Piece#freeWay(int, int, classes.Board)
	 */
	@Override
	public boolean freeWay(int xDist, int yDist, Board b)
	{
		int[]currentPos = b.getSelectedPosition();
		int currentX = currentPos[0] ;
		int currentY = currentPos[1] ;
		int signDistanceX;
		int signDistanceY;

		signDistanceX = xDist / Math.abs(xDist);
		signDistanceY = yDist / Math.abs(yDist);
		
		for ( int i = 0 ; i < Math.abs(xDist);++i )
		{
			currentX -= signDistanceX;
			currentY -= signDistanceY;
			//s'il y a une piece sur la case
			if (b.getPieceAt(currentX, currentY) != null )
			{
				//si ennemi et si derniere case iteration
				if(b.getPieceAt(currentX, currentY).isWhite != this.isWhite && i == Math.abs(xDist) -1)
				{
					return true;
				}
				return false;
			}
				
		}

		return true;
	}
}
