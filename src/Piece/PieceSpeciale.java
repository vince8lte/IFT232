package Piece;

import classes.Player.Color;

public abstract class PieceSpeciale extends Piece {
	boolean canSpecialMove;
	
	public PieceSpeciale(Color color) {
	    super(color);
		canSpecialMove = true;
	}
	
	@Override
	public boolean canSpecialMove(){
    	return false;
    }
	
}
