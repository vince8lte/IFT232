package Piece;

import GUI.PatternFactory;
import classes.Player.Color;

public class Reine extends Piece {

    private final String QUEEN_IMG = "ressources/pictures/reine";
    
	public Reine(Color color) {
	    super(color);
	    this.IMG_URL = QUEEN_IMG;
	    this.patterns = PatternFactory.getInstance().getQueenPattern();
	}
}
