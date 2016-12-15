package Piece;

import java.awt.Point;

import classes.Player;

public class Fou extends Piece {

	public Fou(Player.Color color) {
		super(color);
		this.initPattern();
		this.imgUrl = "ressources/pictures/fou";
		if(this.getColor() == Player.Color.WHITE){
			this.imgUrl += "b.png";
		}else{
			this.imgUrl += "n.png";
		}
	}

	@Override
	protected void initPattern() {
		// TODO Auto-generated method stub
		this.patterns= new PiecePattern[4];
		patterns[0] = new PiecePattern(1,1,8,true,true);
		patterns[1] = new PiecePattern(1,-1,8,true,true);
		patterns[2] = new PiecePattern(-1,1,8,true,true);
		patterns[3] = new PiecePattern(-1,-1,8,true,true);
	}
}
