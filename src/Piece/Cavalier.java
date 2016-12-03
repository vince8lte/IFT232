package Piece;

public class Cavalier extends Piece {

	public Cavalier(boolean isWhite) {
		super(isWhite);
        this.imgURL = "ressources/pictures/cavalier";
        //Permet la construction du lien vers la bonne image de la piece
        if(this.isWhite){
        	this.imgURL += "b.png";
        }else{
        	this.imgURL += "n.png";
        }
	}

	@Override
	public boolean canMove(int x, int y) {

		boolean resultat = false;
		
		if(((Math.abs(x) == 1) && (Math.abs(y) == 2)) || ((Math.abs(x) == 2) && (Math.abs(y) == 1))){
			resultat = true;
		}
		
		return resultat;
	}
	
	public boolean canAttack(int x, int y){
		return false;
	}

}
