package Piece;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;

import classes.Player;

public abstract class Piece
{
	protected Player.Color color;
    protected String imgUrl;
    protected PiecePatterns[] patterns;
    
    public void hasMoved(){}
    
    public Piece(Player.Color color) {
        this.color = color;
    }
    
    public String getImgUrl() {
        return imgUrl;
    }
    
    public Player.Color getColor() {
    	return this.color;
    }
    
    public String toString()
    {
        String text = "";
        text += getClass().getSimpleName()+" ";
        text += this.getColor().name();
        return text;
    }
    
    public PiecePatterns[] getPattern()
    {
    	return this.patterns;
    }
    
    //public abstract void highlightPossibleMove();
    
    //public abstract boolean canMoveTo(Square x);
    
    protected abstract void initPattern();
    
    
}
