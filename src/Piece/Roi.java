package Piece;

import classes.Player.Color;

public class Roi extends PieceSpeciale {

    private final String IMG_URL = "ressources/pictures/roi";
    
	public Roi(Color color) {
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
		this.patterns= new PiecePattern[8];
		
		patterns[0] = new PiecePattern(1,0,1,true,true);
		patterns[1] = new PiecePattern(-1,0,1,true,true);
		patterns[2] = new PiecePattern(0,1,1,true,true);
		patterns[3] = new PiecePattern(0,-1,1,true,true);
		patterns[4] = new PiecePattern(1,1,1,true,true);
		patterns[5] = new PiecePattern(1,-1,1,true,true);
		patterns[6] = new PiecePattern(-1,1,1,true,true);
		patterns[7] = new PiecePattern(-1,-1,1,true,true);
	}
}
