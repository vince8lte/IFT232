package Piece;

public class Roi extends PieceSpeciale{

	public Roi(boolean isWhite) {
		super(isWhite);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean canMove(int x, int y) {
		
		boolean resultat = false;
		
		if((Math.abs(x) < 2) && (Math.abs(y) < 2)){
			resultat = true;
		}
		
		return resultat;
	}

}
