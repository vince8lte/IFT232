package Piece;

public class Tour extends PieceSpeciale {

	public Tour(boolean isWhite) {
		super(isWhite);
        this.imgURL = "ressources/pictures/tour";
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
		
		if(((x != 0) && ( y == 0)) || ((x == 0) && ( y != 0))){
			resultat = true;
		}
		
		return resultat;
	}
	public boolean canAttack(int x, int y){
		return false;
	}

}
