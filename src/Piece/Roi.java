package Piece;

import classes.Board;
import classes.Player;
import classes.Square;

public class Roi extends PieceSpeciale{

	public Roi(Player.Color color, Board board) {
		super(color, board);
        this.imgUrl = "ressources/pictures/roi";
        if(this.getColor() == Player.Color.WHITE){
        	this.imgUrl += "b.png";
        }else{
        	this.imgUrl += "n.png";
        }
	}

	@Override
	public boolean canMoveTo(Square x) {
		boolean resultat = false;
		/*if((Math.abs(x) < 2) && (Math.abs(y) < 2)){
			resultat = true;
		}*/
		return resultat;
	}
	public boolean canAttack(int x, int y){
		return false;
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
