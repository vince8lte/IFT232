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
		// TODO Auto-generated method stub
		this.patterns= new PiecePatterns[4];
		if(this.getColor() ==Player.Color.BLACK)
		{
			patterns[0] = new PiecePatterns(1,-1,1,true);
			patterns[1] = new PiecePatterns(-1,-1,1,true);
			patterns[2] = new PiecePatterns(0,-2,1,false);
			patterns[3] = new PiecePatterns(0,-1,1,false);
		}
		else
		{
			patterns[0] = new PiecePatterns(1,1,1,true);
			patterns[1] = new PiecePatterns(-1,1,1,true);
			patterns[2] = new PiecePatterns(0,2,1,false);
			patterns[3] = new PiecePatterns(0,1,1,false);
		}

	}

	/*
	@Override
	public boolean canMoveTo(Square square) {
		return square.isHighlighted();
	}*/
	
	/*
	public boolean canAttack(int x, int y){
		boolean resultat = false;

		/*if( Math.abs(x) == Math.abs(y) && ((this.getColor() == Player.Color.WHITE && (y == -1)) || (this.getColor() != Player.Color.WHITE && (y == 1)))){
			resultat = true;
		}
		return resultat;
	}*/

	@Override
	public void hasMoved()
	{
		if(this.canSpecialMove)
		{
			this.canSpecialMove = false;
			PiecePatterns [] patternTemp= new PiecePatterns[3];
			patternTemp[0] = patterns[0];
			patternTemp[1] = patterns[1];
			patternTemp[2] =patterns[3];
			this.patterns = patternTemp;
		}
		
	}
}
