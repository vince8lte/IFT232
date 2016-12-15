package Piece;

import java.awt.Point;

import classes.Player;

public class Reine extends Piece {

	public Reine(Player.Color color) {
		super(color);
        this.imgUrl = "ressources/pictures/reine";
        this.initPattern();
        if(this.getColor() == Player.Color.WHITE){
        	this.imgUrl += "b.png";
        }else{
        	this.imgUrl += "n.png";
        }
	}

	/*
	@Override
	public boolean canMoveTo(Square square) {
	    return square.isHighlighted();
	}*/
	
	@Override
	protected void initPattern() {
		this.patterns= new PiecePattern[8];
		
		patterns[0] = new PiecePattern(1,0,8,false);
		patterns[1] = new PiecePattern(-1,0,8,false);
		patterns[2] = new PiecePattern(0,1,8,false);
		patterns[3] = new PiecePattern(0,-1,8,false);
		patterns[4] = new PiecePattern(1,1,8,false);
		patterns[5] = new PiecePattern(1,-1,8,false);
		patterns[6] = new PiecePattern(-1,1,8,false);
		patterns[7] = new PiecePattern(-1,-1,8,false);
	}
}
