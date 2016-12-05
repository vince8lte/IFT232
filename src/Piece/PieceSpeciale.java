package Piece;

import classes.Board;

public abstract class PieceSpeciale extends Piece {

	boolean doneSpecialMove;
	
	public PieceSpeciale(boolean isWhite, Board b) {
		super(isWhite, b);
		doneSpecialMove = false;
	}

	
}
