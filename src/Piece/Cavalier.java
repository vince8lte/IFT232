package Piece;

import java.awt.Point;

import classes.Player;

public class Cavalier extends Piece {

	public Cavalier(Player.Color color) {
		super(color);
		
        this.imgUrl = "ressources/pictures/cavalier";
        this.initPattern();
        
        if(this.getColor() == Player.Color.WHITE){
        	this.imgUrl += "b.png";
        }else{
        	this.imgUrl += "n.png";
        }
	}

	protected void initPattern() {
		// TODO Auto-generated method stub
		this.patterns= new PiecePattern[8];
		patterns[0] = new PiecePattern(1,2,1,false);
		patterns[1] = new PiecePattern(1,-2,1,false);
		patterns[2] = new PiecePattern(-1,2,1,false);
		patterns[3] = new PiecePattern(-1,-2,1,false);
		patterns[4] = new PiecePattern(2,1,1,false);
		patterns[5] = new PiecePattern(2,-1,1,false);
		patterns[6] = new PiecePattern(-2,1,1,false);
		patterns[7] = new PiecePattern(-2,-1,1,false);
	}
	
	/*@Override
	public boolean canMoveTo() {
	    
	}*/
}
