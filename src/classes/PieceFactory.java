package classes;

import Piece.*;

public class PieceFactory
{    
    /** Holder */ /* NOTE DE JONATHAN: LAISSEZ-ÇA COMME ÇA. J'AI PARLÉ AVEC MIKAEL EN CLASSE. */
    private static class PieceFactoryHolder
    { 
        /** Instance unique non préinitialisée */
        private final static PieceFactory instance = new PieceFactory();
    }
    
    public static PieceFactory getInstance()
    {
        return PieceFactoryHolder.instance;
    }
    
    public Piece givePiece(String strPiece){
    	  	
    	//Inspecte le string pour d�terminer la couleur de la nouvelle piece
    	Player.Color color = Player.Color.BLACK;
    	if(strPiece.split(",")[1] == "WHITE"){
    		color = Player.Color.WHITE;
    	}
    	
    	switch(strPiece.split(",")[0])
    	{
	    	case "Pion":{
	    		return new Pion(color);
	    	}
	    	case "Tour":{
	    		return new Tour(color);
	    	}
	    	case "Fou":
            {
                return new Fou(color);
            }
	    	case "Cavalier":
            {
                return new Cavalier(color);
            }
	    	case "Reine":
            {
                return new Reine(color);
            }
	    	case "Roi":
            {
                return new Roi(color);
            }
	    	case "PieceFantome":
	    	{
	    		return new PieceFantome(color);
	    	}
	    	default:
                return null;
    	}
    }
    
    public Piece giveGhostPiece(Player.Color color){
    	return new PieceFantome(color);
    }
    
    public Piece givePromotatePiece(String strPiece){
    	Piece promotedPiece = givePiece(strPiece);
    	promotedPiece.hasMoved();
    	return promotedPiece;
    }
}
