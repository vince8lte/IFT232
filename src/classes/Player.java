package classes;

import Piece.Piece;

public class Player {
	private Color color;
	
	public enum Color {
		WHITE,
		BLACK,
		NONE
	}
	
	public Player(Color color) {
		this.color = color;
	}
	
	public Color getColor() {
		return color;
	}
	
	public boolean isPieceOwner(Piece piece) {
		return color == piece.getColor();
	}
}
