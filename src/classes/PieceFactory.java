package classes;

import Piece.Cavalier;
import Piece.Fou;
import Piece.Piece;
import Piece.Pion;
import Piece.Reine;
import Piece.Roi;
import Piece.Tour;

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
    
    public Piece givePiece(String strPiece, Player.Color color){
    	
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
	    	default:
                return null;
    	}
    }
}
