package Piece;

import classes.Player.Color;

public class Tour extends PieceSpeciale {

    private final static String IMG_URL = "ressources/pictures/tour";
    
	public Tour(Color color) {
	    super(color, IMG_URL);
        this.patterns = PatternFactory.getInstance().getTowerPattern();
	}
}
