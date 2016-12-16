package Piece;

import classes.Player.Color;

public class Pion extends PieceSpeciale {

    private final static String IMG_URL = "ressources/pictures/pion";
    
	public Pion(Color color) 
	{
	    super(color, IMG_URL);
	    if(color == Color.BLACK){
	    	this.patterns = PatternFactory.getInstance().getBlackPawnPattern();
	    }else{
	    	this.patterns = PatternFactory.getInstance().getWhitePawnPattern();
	    }
	}
	
	@Override
	public void hasMoved()
	{
		if(this.canSpecialMove)
		{
			this.canSpecialMove = false;
			//Modifie le pattern de d�placement
			this.patterns[2] = new PiecePattern(0,this.patterns[2].getDirectionX(),1,false,true);
		}
		
	}
}
