package Piece;

public class Pion extends PieceSpeciale {

			
	public Pion(boolean isWhite) {
		super(isWhite);
        this.imgURL = "ressources/pictures/pion";
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
		
		if((x == 0) && ((isWhite && (y == -1)) || (!isWhite && (y == 1)))){
			resultat = true;
		}
		
		return resultat;
	}

}
