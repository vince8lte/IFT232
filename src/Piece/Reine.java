package Piece;

import classes.Board;
import classes.Player;
import classes.Square;

public class Reine extends Piece {

	public Reine(Player.Color color, Board board) {
		super(color, board);
        this.imgUrl = "ressources/pictures/reine";
        if(this.getColor() == Player.Color.WHITE){
        	this.imgUrl += "b.png";
        }else{
        	this.imgUrl += "n.png";
        }
	}

	@Override
	public boolean canMoveTo(Square square) {

		boolean resultat = false;
		
		/*if( (((x != 0) && ( y == 0)) || ((x == 0) && ( y != 0))) || (Math.abs(x) == Math.abs(y)) ){
			resultat = true;
		}*/
		
		return resultat;
	}

	@Override
	protected boolean freeWay(int x, int y, Board b) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void highlightPossibleMove() {
		// TODO Auto-generated method stub
		
	}
}
