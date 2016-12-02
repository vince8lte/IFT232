package Piece;

public class Cavalier extends Piece {

	public Cavalier(boolean isWhite) {
		super(isWhite);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean canMove(int x, int y) {

		boolean resultat = false;
		
		if(((Math.abs(x) == 1) && (Math.abs(y) == 2)) || ((Math.abs(x) == 2) && (Math.abs(y) == 1))){
			resultat = true;
		}
		
		return resultat;
	}

}
