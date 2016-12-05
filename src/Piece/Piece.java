package Piece;

import classes.Board;

public abstract class Piece
{
	private Board b;
    protected String imgURL;		//Indique l'image de la piece
    protected boolean isWhite;		//Indique la couleur de la piece au joueur
    
    public Piece(boolean isWhite, Board b)
    {      
        this.isWhite = isWhite;
        this.b=b;
    }
    
    public String getImgUrl()
    {
        return imgURL;
    }
    
    public boolean isWhite(){
    	return this.isWhite;
    }
    
    public abstract boolean canMove(int x, int y);
    
    public abstract boolean canAttack(int x, int y);
    
    /*Determine si le "chemin" est libre (pour un move)
     * 
     */
    public abstract boolean freeWay(int x, int y, Board b);
    
    protected Board getBoard()
    {
    	return this.b;
    }
}
