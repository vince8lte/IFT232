package Piece;

import classes.Player.Color;

public abstract class PieceSpeciale extends Piece {
	boolean canSpecialMove;
	
	public PieceSpeciale(Color color, String imgPiece) {
	    super(color, imgPiece);
		canSpecialMove = true;
	}
	
	@Override
	public boolean canSpecialMove(){
    	return false;
    }
	
	@Override
	public void hasMoved()
	{
		canSpecialMove = false;
	}
}
