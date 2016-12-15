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
    protected PiecePattern[] patterns;
    
    protected abstract void initPattern();
    
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
    
    // Retourne le pattern associé au mouvement de la pièce
    // Exemple: On reçoit srcX comme 3, src Y comme 5, recptX comme 4, recptY comme 5
    // On sait que la piece a avancé d'une case vers la droite. Est-ce qu'elle a le droit?
    // La fonction va verifier parmi tout ses "mouvements possibles" qu'elle a le droit
    // Si, parmi tous les mouvements possibles, s'il y en a un qui correspond à "un vers la droite",
    // alors il va retourner celui là. Sinon, il va retourner null ce qui veut dire que le mouvement
    // n'est pas autorisée.
    public PiecePattern getPattern(int sourceX, int sourceY, int recipientX, int recipientY)
    {
        int patternIndex = 0;        
        int differenceX = (recipientX - sourceX);
        int differenceY = (recipientX - sourceY);
        
        boolean foundPattern = false;
        
        if (differenceX != 0)
            differenceX = (Math.abs(differenceX) / differenceX); // S'assure que differenceX va soit être 1, -1 ou 0
        
        if (differenceY != 0)
            differenceY = (Math.abs(differenceY) / differenceY); // S'assure que differenceY va soi être 1, -1 ou 0                        
        
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
    
    // Retourne tous les mouvements possibles d'une pièce.
    public PiecePattern[] getPatterns()
    {
        return this.patterns;
    }
    
    
    
    
}
