package Piece;

import classes.Player.Color;

public class Fou extends Piece {
    
    private final static String IMG_URL = "ressources/pictures/fou";

	public Fou(Color color) {
	    super(color, IMG_URL);
		this.patterns = PatternFactory.getInstance().getBishopPattern();
		
	}
}
