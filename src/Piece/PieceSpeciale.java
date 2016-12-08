package Piece;

import classes.Board;
import classes.Player;

public abstract class PieceSpeciale extends Piece {
	boolean doneSpecialMove;
	public PieceSpeciale(Player.Color color, Board board) {
		super(color, board);
		doneSpecialMove = false;
	}
}
