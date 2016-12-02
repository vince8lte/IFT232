package Piece;

public class Tour extends PieceSpeciale {

	public Tour(boolean isWhite) {
		super(isWhite);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean canMove(int x, int y) {
		boolean resultat = false;
		
		if(((x != 0) && ( y == 0)) || ((x == 0) && ( y != 0))){
			resultat = true;
		}
		
		return resultat;
	}

}
