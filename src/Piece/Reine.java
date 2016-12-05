package Piece;

import classes.Board;

public class Reine extends Piece {

	public Reine(boolean isWhite, Board b) {
		super(isWhite, b);
        this.imgURL = "ressources/pictures/reine";
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
		
		//Mouvement du fou ou de la tour
		if( (((x != 0) && ( y == 0)) || ((x == 0) && ( y != 0))) || (Math.abs(x) == Math.abs(y)) ){
			resultat = true;
		}
		
		return resultat;
	}
	public boolean canAttack(int x, int y){
		return canMove(x,y);
	}

	@Override
	public boolean freeWay(int x, int y, Board b) {
		// TODO Auto-generated method stub
		return false;
	}
}
