package Piece;

import classes.Board;
import classes.Player;
import classes.Square;

public class Tour extends PieceSpeciale {

	public Tour(Player.Color color, Board board) {
		super(color, board);
        this.imgUrl = "ressources/pictures/tour";
        if(this.getColor() == Player.Color.WHITE){
        	this.imgUrl += "b.png";
        }else{
        	this.imgUrl += "n.png";
        }
	}

	@Override
	public boolean canMoveTo(Square x) {
		boolean resultat = false;
		
		/*if((((x != 0) && ( y == 0)) || ((x == 0) && ( y != 0))) && freeWay(x, y, board)){
			resultat = true;
		}*/
		
		return resultat;
	}

	@Override
	protected boolean freeWay(int x, int y, Board b) {
		boolean isFreeWay = true;
		/*int[] currentPos = b.getSelectedPosition();
		int signX = (x >= 0) ? -1 : 1;
		int signY = (y >= 0) ? -1 : 1;
		int distX = Math.abs(x);
		int distY = Math.abs(y);
		for (int i = (distX > 0) ? 1 : 0; i <= distX; ++i) {
			for (int j = (distY > 0) ? 1 : 0; j <= distY; ++j) {
				if (b.getPieceAt(currentPos[0] + i * signX, currentPos[1] + j * signY) != null) {
					isFreeWay = false;
					break;
				}
			}
		}*/
		return isFreeWay;
	}

	@Override
	public void highlightPossibleMove() {
		// TODO Auto-generated method stub
		
	}

}
