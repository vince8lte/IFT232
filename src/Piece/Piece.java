package Piece;

import classes.Board;
import classes.Player;

public abstract class Piece
{
	protected Board b;
    protected String imgURL;		//Indique l'image de la piece
    protected Player player;
    
    public Piece(Player player, Board b)
    {
        this.player = player;
        this.b=b;
    }
    
    public String getImgUrl()
    {
        return imgURL;
    }
    
    public boolean isWhite(){
    	return this.player.isWhite();
    }
    
    public abstract boolean canMove(int x, int y);
    
    public abstract boolean canAttack(int x, int y);
    
    /*Determine si le "chemin" est libre (pour un move)
     * avant le deplacement
     * 
     */
    protected abstract boolean freeWay(int x, int y, Board b);
    
    protected Board getBoard()
    {
    	return this.b;
    }
}
