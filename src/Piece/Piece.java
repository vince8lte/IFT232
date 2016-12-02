package Piece;

public abstract class Piece
{
    protected String imgURL;		//Indique l'image de la piece
    protected boolean isWhite;		//Indique la couleur de la piece au joueur
    
    public Piece(boolean isWhite)
    {      
        this.isWhite = isWhite;
        
        //Permet la construction du lien vers la bonne image de la piece
        String urlImage = "ressources/pictures/pion";
        if(isWhite){
        	urlImage += "b.png";
        }else{
        	urlImage += "n.png";
        }
        this.imgURL = urlImage;
    }
    
    public String getImgUrl()
    {
        return imgURL;
    }
    
    public boolean isWhite(){
    	return this.isWhite;
    }
    
    public abstract boolean canMove(int x, int y);
}
