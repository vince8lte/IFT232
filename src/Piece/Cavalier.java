package Piece;

import classes.Player.Color;

public class Cavalier extends Piece {
    
    private final String IMG_URL = "ressources/pictures/cavalier";

	public Cavalier(Color color) {
	    super(color);
        this.initPattern();
	}
	
   @Override
    protected String getImgURL()
    {
        return IMG_URL;
    }

   @Override
	protected void initPattern() {
		this.patterns= new PiecePattern[8];
		
		patterns[0] = new PiecePattern(1,2,1,true,true);
		patterns[1] = new PiecePattern(1,-2,1,true,true);
		patterns[2] = new PiecePattern(-1,2,1,true,true);
		patterns[3] = new PiecePattern(-1,-2,1,true,true);
		patterns[4] = new PiecePattern(2,1,1,true,true);
		patterns[5] = new PiecePattern(2,-1,1,true,true);
		patterns[6] = new PiecePattern(-2,1,1,true,true);
		patterns[7] = new PiecePattern(-2,-1,1,true,true);
	}
	
	// Retourne le pattern associé au mouvement de la pièce
    // Exemple: On reçoit srcX comme 3, src Y comme 5, recptX comme 4, recptY comme 5
    // On sait que la piece a avancé d'une case vers la droite. Est-ce qu'elle a le droit?
    // La fonction va verifier parmi tout ses "mouvements possibles" qu'elle a le droit
    // Si, parmi tous les mouvements possibles, s'il y en a un qui correspond à "un vers la droite",
    // alors il va retourner celui là. Sinon, il va retourner null ce qui veut dire que le mouvement
    // n'est pas autorisée.
	@Override
    public PiecePattern getPattern(int sourceX, int sourceY, int recipientX, int recipientY)
    {
        int patternIndex = 0;        
        int differenceX = (recipientX - sourceX);
        int differenceY = (recipientX - sourceY);
        
        boolean foundPattern = false;                       
        
        while (!foundPattern && patternIndex < this.patterns.length)
        {
            foundPattern = ((patterns[patternIndex].getDirectionX() == differenceX) && 
                            (patterns[patternIndex].getDirectionY() == differenceY)); 
            
            ++patternIndex;
        }
        
        if (foundPattern)
            return patterns[--patternIndex];
        else
            return null;
    }
}
