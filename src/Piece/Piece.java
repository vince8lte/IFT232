package Piece;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;

import classes.Board;
import classes.Player;
import classes.Square;

public abstract class Piece
{
	//protected Board board;
	protected Player.Color color;
    protected String imgUrl;
    //protected Square square;
    protected PiecePatterns[] patterns;
    
    public void hasMoved(){}
    
    public Piece(Player.Color color, Board board) {
        //this.board = board;
        this.color = color;
    }
    
    public void setSquare(Square square) {
    	//this.square = square;
    }
    
    public String getImgUrl() {
        return imgUrl;
    }
    
    public Player.Color getColor() {
    	return this.color;
    }
    
    /*public void paintComponent(Rectangle rec, Graphics g) {
    	Image scaledPiece = board.getScaledImage(imgUrl);
        g.drawImage(scaledPiece, rec.x, rec.y, null);
    }*/
    
    /*public boolean tryHighlight(Square square)
    {
        if (square != null)
        {
            if(square.isEmpty()) square.isHighlighted(true);
            else if (this.getColor() != square.getPiece().getColor()) square.isHighlighted(true);
            if (square.isHighlighted()) return true;
        }
        return false;
    }*/
    
    public String exportPiece()
    {
        String text = "";
        text += getClass().getSimpleName()+",";
        text += this.getColor().name();
        return text;
    }
    
    //public abstract void highlightPossibleMove();
    
    public abstract boolean canMoveTo(Square x);
    
    protected abstract void initPattern();
    
    public PiecePatterns[] getPattern()
    {
    	return this.patterns;
    }
}
