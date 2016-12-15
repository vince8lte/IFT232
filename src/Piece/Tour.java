package Piece;

import java.awt.Point;

import classes.Player;

public class Tour extends PieceSpeciale {

	public Tour(Player.Color color) {
		super(color);
        this.imgUrl = "ressources/pictures/tour";
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
		this.patterns= new PiecePattern[4];
		
		patterns[0] = new PiecePattern(1,0,8,false);
		patterns[1] = new PiecePattern(-1,0,8,false);
		patterns[2] = new PiecePattern(0,1,8,false);
		patterns[3] = new PiecePattern(0,-1,8,false);
	}
}
