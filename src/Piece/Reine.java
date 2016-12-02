package Piece;

public class Reine extends Piece {

	public Reine(boolean isWhite) {
		super(isWhite);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean canMove(int x, int y) {

		boolean resultat = false;
		
		//Mouvement du fou ou de la tour
		if( (((x != 0) && ( y == 0)) || ((x == 0) && ( y != 0))) || (Math.abs(x) == Math.abs(y)) ){
			resultat = true;
		}
		
		return resultat;
	}

}
