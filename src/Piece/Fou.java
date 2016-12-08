package Piece;

import classes.Board;
import classes.Player;
import classes.Square;

public class Fou extends Piece {

	public Fou(Player.Color color, Board b) {
		super(color, b);
		this.imgUrl = "ressources/pictures/fou";
		if(this.getColor() == Player.Color.WHITE){
			this.imgUrl += "b.png";
		}else{
			this.imgUrl += "n.png";
		}
	}

	@Override
	public boolean canMoveTo(Square square) {
		boolean resultat = false;
		/*if( Math.abs(x) == Math.abs(y) && freeWay(x,y,board))
		{
			resultat = true;
		}*/
		return resultat;
	}

	@Override
	protected boolean freeWay(int xDist, int yDist, Board b)
	{
		/*int[]currentPos = b.getSelectedPosition();
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
			if (b.getPieceAt(currentX, currentY) != null )
			{
				if(b.getPieceAt(currentX, currentY).isWhite() != this.isWhite() && i == Math.abs(xDist) -1)
				{
					return true;
				}
				return false;
			}
				
		}*/

		return true;
	}

	@Override
	public void highlightPossibleMove() {
		// TODO Auto-generated method stub
		
	}
}
