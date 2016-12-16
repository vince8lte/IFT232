package Piece;

import classes.Player.Color;

public class Pion extends PieceSpeciale {

    private final String IMG_URL = "ressources/pictures/pion";
    
	public Pion(Color color) 
	{
	    super(color);
	    this.initPattern();
	}
	
	@Override
	protected String getImgURL()
	{
	    if (color == Color.BLACK)
	        return IMG_URL + "n.png";
	    else if (color == Color.WHITE)
	        return IMG_URL + "b.png";
	    else
	        return "";
	}
	
	@Override
	protected void initPattern() {
		this.patterns = new PiecePattern[3];
		
		if (this.getColor() == Color.BLACK)
		{
			patterns[0] = new PiecePattern(0,-1,1,false,false);
			patterns[1] = new PiecePattern(-1,-1,1,true,false);
			patterns[2] = new PiecePattern(0,-1,2,false,true);
		}
		else
		{
			patterns[0] = new PiecePattern(0,1,1,false,false);
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
			//Modifie le pattern de d�placement
			this.patterns[2] = new PiecePattern(0,this.patterns[2].getDirectionX(),1,false,true);
		}
		
	}
}
