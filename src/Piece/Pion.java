package Piece;

import classes.Board;

public class Pion extends PieceSpeciale {

	private boolean coupSpecial = true;
	
	public Pion(boolean isWhite, Board b) {
		super(isWhite, b);
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
			coupSpecial = false;
			resultat = true;
		}
		else if((x == 0) && ((isWhite && (y == -2)) || (!isWhite && (y == 2))) && coupSpecial)
		{
			coupSpecial = false;
			resultat = true;
		}
		
		return resultat;
	}
	public boolean canAttack(int x, int y){
		boolean resultat = false;
		
		if( Math.abs(x) == Math.abs(y) && y==-1){
			resultat = true;
		}
		return resultat;
	}

	@Override
	public boolean freeWay(int x, int y, Board b) {
		// TODO Auto-generated method stub
		return false;
	}
	

}
