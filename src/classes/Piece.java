package classes;

public class Piece
{
    private String imgURL;
    
    public Piece(String imgURL)
    {      
        this.imgURL = imgURL;
    }
    public void setImgUrl(String url)
    {
        imgURL=url;
    }
    
    public String getImgUrl()
    {
        return imgURL;
    }
}
