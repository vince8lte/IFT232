package Piece;

public class Fou extends Piece {

	public Fou(boolean isWhite) {
		super(isWhite);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean canMove(int x, int y) {
		
		boolean resultat = false;
		
		if( Math.abs(x) == Math.abs(y) ){
			resultat = true;
		}
		
		return resultat;
	}

}
