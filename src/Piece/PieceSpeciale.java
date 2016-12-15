package Piece;

import classes.Player;

public abstract class PieceSpeciale extends Piece {
	boolean canSpecialMove;
	
	public PieceSpeciale(Player.Color color) {
		super(color);
		canSpecialMove = true;
	}
	
	@Override
	public boolean canSpecialMove(){
		return canSpecialMove;
	}
	
}
