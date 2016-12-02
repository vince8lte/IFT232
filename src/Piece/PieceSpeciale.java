package Piece;

public abstract class PieceSpeciale extends Piece {

	boolean doneSpecialMove;
	
	public PieceSpeciale(boolean isWhite) {
		super(isWhite);
		doneSpecialMove = false;
	}

	
}
