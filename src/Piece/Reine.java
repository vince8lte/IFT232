package Piece;

import classes.Player.Color;

public class Reine extends Piece {

    private final static String IMG_URL = "ressources/pictures/reine";
    
	public Reine(Color color) {
	    super(color, IMG_URL);
        this.patterns = PatternFactory.getInstance().getQueenPattern();       
	}
}
