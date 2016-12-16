package Piece;

import classes.Player;

public class PieceFantome extends Piece {
    
    public boolean canSpecialMove(){
		return false;
	}
    
    public PieceFantome(Player.Color color) {
        super(color);
        imgUrl = "";
    }
    
    public Player.Color getColor() {
    	return this.color;
    }
    
    public PiecePattern getPattern(int sourceX, int sourceY, int recipientX, int recipientY){return null;}
    
    public PiecePattern[] getPatterns(){return null;}
    
    protected void initPattern(){}
    
    public void hasMoved(){}

}
