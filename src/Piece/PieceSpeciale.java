package Piece;

import classes.Board;
import classes.Player;

public abstract class PieceSpeciale extends Piece {

	boolean doneSpecialMove;
	
	public PieceSpeciale(Player player, Board b) {
		super(player, b);
		doneSpecialMove = false;
	}

	
}
