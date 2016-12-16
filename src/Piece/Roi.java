package Piece;

import classes.Player.Color;

public class Roi extends PieceSpeciale {

    private final static String IMG_URL = "ressources/pictures/roi";
    
	public Roi(Color color) {
	    super(color, IMG_URL);
        this.patterns = PatternFactory.getInstance().getKingPattern();
	}
}
