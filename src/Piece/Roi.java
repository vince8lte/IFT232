package Piece;

import GUI.PatternFactory;
import classes.Player.Color;

public class Roi extends PieceSpeciale {

    private final String KING_IMG = "ressources/pictures/roi";
    
	public Roi(Color color) {
	    super(color);
	    this.IMG_URL = KING_IMG;
	    this.patterns = PatternFactory.getInstance().getKingPattern();
	}
	
}
