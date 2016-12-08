package Piece;

import classes.Board;
import classes.Player;

public class Tour extends PieceSpeciale {

	public Tour(Player player, Board b) {
		super(player, b);
        this.imgURL = "ressources/pictures/tour";
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
		
		if((((x != 0) && ( y == 0)) || ((x == 0) && ( y != 0))) && freeWay(x, y, b)){
			resultat = true;
		}
		
		return resultat;
	}
	public boolean canAttack(int x, int y){
		return canMove(x,y);
	}

	@Override
	protected boolean freeWay(int x, int y, Board b) {
		int[] currentPos = b.getSelectedPosition();
		int signX = (x >= 0) ? -1 : 1;
		int signY = (y >= 0) ? -1 : 1;
		int distX = Math.abs(x);
		int distY = Math.abs(y);
		boolean isFreeWay = true;
		for (int i = (distX > 0) ? 1 : 0; i <= distX; ++i) {
			for (int j = (distY > 0) ? 1 : 0; j <= distY; ++j) {
				if (b.getPieceAt(currentPos[0] + i * signX, currentPos[1] + j * signY) != null) {
					isFreeWay = false;
					break;
				}
			}
		}
		return isFreeWay;
	}

}
