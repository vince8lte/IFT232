package Piece;

import classes.Player.Color;

public class Tour extends PieceSpeciale {

    private final String IMG_URL = "ressources/pictures/tour";
    
	public Tour(Color color) {
	    super(color);
        this.initPattern();
	}
	
	@Override
	protected String getImgURL()
	{
	    return IMG_URL;
	}
	
	@Override
	protected void initPattern() {
		this.patterns= new PiecePattern[4];
		
		patterns[0] = new PiecePattern(1,0,8,true,true);
		patterns[1] = new PiecePattern(-1,0,8,true,true);
		patterns[2] = new PiecePattern(0,1,8,true,true);
		patterns[3] = new PiecePattern(0,-1,8,true,true);
	}
}
