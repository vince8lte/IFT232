package Piece;

import GUI.PatternFactory;
import classes.Player.Color;

public class Fou extends Piece {
    
    private final String BISHOP_IMG = "ressources/pictures/fou";

	public Fou(Color color) {
	    super(color);
	    this.IMG_URL = BISHOP_IMG;
		this.patterns= PatternFactory.getInstance().getBishopPattern();
	}
}
