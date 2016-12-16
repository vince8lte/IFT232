package Piece;

import classes.Player.Color;

public class Fou extends Piece {
    
    private final String IMG_URL = "ressources/pictures/fou";

	public Fou(Color color) {
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
		this.patterns= new PiecePattern[4];
		patterns[0] = new PiecePattern(1,1,8,true,true);
		patterns[1] = new PiecePattern(1,-1,8,true,true);
		patterns[2] = new PiecePattern(-1,1,8,true,true);
		patterns[3] = new PiecePattern(-1,-1,8,true,true);
	}
}
