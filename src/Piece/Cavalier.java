package Piece;

import classes.Board;
import classes.Player;
import classes.Square;

public class Cavalier extends Piece {

	public Cavalier(Player.Color color, Board b) {
		super(color, b);
        this.imgUrl = "ressources/pictures/cavalier";
        if(this.getColor() == Player.Color.WHITE){
        	this.imgUrl += "b.png";
        }else{
        	this.imgUrl += "n.png";
        }
	}

	@Override
	public boolean canMoveTo(Square square) {
		boolean resultat = false;
		/*if(((Math.abs(x) == 1) && (Math.abs(y) == 2)) || ((Math.abs(x) == 2) && (Math.abs(y) == 1))){
			resultat = true;
		}*/
		return resultat;
	}

	@Override
	protected boolean freeWay(int x, int y, Board b) {
		return false;
	}

	@Override
	public void highlightPossibleMove() {
		// TODO Auto-generated method stub
		
	}

}
