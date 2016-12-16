package Piece;

import classes.Player;

public class PieceFantome extends Piece {
    
	private final String GHOST_IMG = "";
	
    public boolean canSpecialMove(){
		return false;
	}
    
    public PieceFantome(Player.Color color) {
        super(color);
        this.IMG_URL = GHOST_IMG;
    }
    
    public PiecePattern getPattern(int sourceX, int sourceY, int recipientX, int recipientY){return null;}
    
    public PiecePattern[] getPatterns(){return null;}
}
