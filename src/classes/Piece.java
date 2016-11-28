package classes;

public class Piece
{
    private String imgURL;
    
    public Piece(String imgURL)
    {      
        this.imgURL = imgURL;
    }
    
    /*@Override
    public String toString()
    {
        return this.imgURL;
    }*/
    
    public String getImgUrl(){
    	return imgURL;
    }
}
