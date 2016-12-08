package Piece;

import classes.Board;
import classes.Player;

public class Pion extends PieceSpeciale {

	private boolean coupSpecial = true;
	
	public Pion(Player player, Board b) {
		super(player, b);
        this.imgURL = "ressources/pictures/pion";
        //Permet la construction du lien vers la bonne image de la piece
        if(this.isWhite()){
        	this.imgURL += "b.png";
        }else{
        	this.imgURL += "n.png";
        }
	}

	@Override
	public boolean canMove(int x, int y) {
		
		boolean resultat = false;
		
		if((x == 0) && ((isWhite() && (y == -1)) || (!isWhite() && (y == 1)))){
			coupSpecial = false;
			resultat = true;
		}
		else if((x == 0) && ((isWhite() && (y == -2)) || (!isWhite() && (y == 2))) && coupSpecial)
		{
			coupSpecial = false;
			resultat = true;
		}
		
		return resultat;
	}
	public boolean canAttack(int x, int y){
		boolean resultat = false;
		
		if( Math.abs(x) == Math.abs(y) && ((isWhite() && (y == -1)) || (!isWhite() && (y == 1)))){
			resultat = true;
		}
		return resultat;
	}

	@Override
	protected boolean freeWay(int x, int y, Board b) {
		// TODO Auto-generated method stub
		return false;
	}
	

}
