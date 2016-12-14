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
		// TODO Auto-generated method stub
		this.patterns= new PiecePatterns[8];
		patterns[0] = new PiecePatterns(1,0,8,false);
		patterns[1] = new PiecePatterns(-1,0,8,false);
		patterns[2] = new PiecePatterns(0,1,8,false);
		patterns[3] = new PiecePatterns(0,-1,8,false);
		patterns[4] = new PiecePatterns(1,1,8,false);
		patterns[5] = new PiecePatterns(1,-1,8,false);
		patterns[6] = new PiecePatterns(-1,1,8,false);
		patterns[7] = new PiecePatterns(-1,-1,8,false);
	}
}
