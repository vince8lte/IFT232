package Piece;

public class Fou extends Piece {

	public Fou(boolean isWhite) {
		super(isWhite);
        this.imgURL = "ressources/pictures/fou";
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
		
		if( Math.abs(x) == Math.abs(y) ){
			resultat = true;
		}
		
		return resultat;
	}
	public boolean canAttack(int x, int y){
		return false;
	}
}
