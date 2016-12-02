package Piece;

public class Pion extends PieceSpeciale {

	public Pion(boolean isWhite) {
		super(isWhite);
	}

	@Override
	public boolean canMove(int x, int y) {
		
		boolean resultat = false;
		
		if((x == 0) && ((isWhite && (y == -1)) || (!isWhite && (y == 1)))){
			resultat = true;
		}
		
		return resultat;
	}

}
