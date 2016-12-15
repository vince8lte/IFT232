package Piece;

import java.awt.Point;

import classes.Player;

public class Pion extends PieceSpeciale {

	public Pion(Player.Color color) {
		super(color);
		this.imgUrl = "ressources/pictures/pion";
		if(this.getColor() == Player.Color.WHITE){
			this.imgUrl += "b.png";
		}else{
			this.imgUrl += "n.png";
		}
	}
	protected void initPattern() {
		this.patterns= new PiecePattern[3];
		
		if(this.getColor() ==Player.Color.BLACK)
		{
			patterns[0] = new PiecePattern(1,-1,1,true,false);
			patterns[1] = new PiecePattern(-1,-1,1,true,false);
			patterns[2] = new PiecePattern(0,-1,2,false,true);
		}
		else
		{
			patterns[0] = new PiecePattern(1,1,1,true,false);
			patterns[1] = new PiecePattern(-1,1,1,true,false);
			patterns[2] = new PiecePattern(0,1,2,false,true);
		}

	}

	@Override
	public void hasMoved()
	{
		if(this.canSpecialMove)
		{
			this.canSpecialMove = false;
			//Modifie le pattern de déplacement
			this.patterns[2] = new PiecePattern(0,this.patterns[2].getDirectionX(),1,false,true);
		}
		
	}
}
