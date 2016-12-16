package Piece;

import GUI.PatternFactory;
import classes.Player.Color;

public class Tour extends PieceSpeciale {

    private final String TOWER_IMG = "ressources/pictures/tour";
    
	public Tour(Color color) {
	    super(color);
	    this.IMG_URL = TOWER_IMG;
        this.patterns = PatternFactory.getInstance().getTowerPattern();
	}
}
