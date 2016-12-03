package Piece;

public abstract class Piece
{
    protected String imgURL;		//Indique l'image de la piece
    protected boolean isWhite;		//Indique la couleur de la piece au joueur
    
    public Piece(boolean isWhite)
    {      
        this.isWhite = isWhite;
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
}
