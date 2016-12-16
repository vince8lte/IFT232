package Piece;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import GraphicsInterface.IRenderable;
import Utils.ImgUtils;
import classes.Player.Color;

public abstract class Piece implements IRenderable
{
    protected PiecePattern[] patterns;
    protected Color color;
    protected String imgPiece;   
    
    protected Piece(Color color, String imgPiece)
    {
        this.color = color;
        this.imgPiece = imgPiece;
    }
    
    public boolean canSpecialMove(){
    	return false;
    }
    
    public Color getColor()
    {
        return this.color;
    }
    
    public void hasMoved(){}
    
    // Retourne le pattern associé au mouvement de la pièce
    // Exemple: On reçoit srcX comme 3, src Y comme 5, recptX comme 4, recptY comme 5
    // On sait que la piece a avancé d'une case vers la droite. Est-ce qu'elle a le droit?
    // La fonction va verifier parmi tout ses "mouvements possibles" qu'elle a le droit
    // Si, parmi tous les mouvements possibles, s'il y en a un qui correspond à "un vers la droite",
    // alors il va retourner celui là. Sinon, il va retourner null ce qui veut dire que le mouvement
    // n'est pas autorisée.
    public PiecePattern getPattern(int deltaX, int deltaY)
    {
        int patternIndex = 0;
        
        boolean foundPattern = false;
        
        if (deltaX != 0)
            deltaX = (deltaX / Math.abs(deltaX)); // S'assure que differenceX va soit être 1, -1 ou 0
        
        if (deltaY != 0)
            deltaY = (deltaY / Math.abs(deltaY)); // S'assure que differenceY va soi être 1, -1 ou 0. On divise par -1 a la fin, car lorsque le y est a 0, il est completement en haut...                        
        
        while (!foundPattern && patternIndex < this.patterns.length)
        {
            foundPattern = ((patterns[patternIndex].getDirectionX() == deltaX) && 
                            (patterns[patternIndex].getDirectionY() == deltaY)); 
            
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
    
    public String getImgURL(){
	    if (color == Color.BLACK)
	        return this.imgPiece + "n.png";
	    else if (color == Color.WHITE)
	        return this.imgPiece + "b.png";
	    else
	        return "";
    }
    
    @Override
    public String toString(){
		return this.getClass().getSimpleName() + "," + this.color.toString();
    }
    
    @Override
    public void render(Rectangle container, Rectangle parentContainer, Graphics g)
    {
        Image scaledImage = ImgUtils.getScaledImage(this.getImgURL(), container, parentContainer);
        g.drawImage(scaledImage, (int)container.getX(), (int)container.getY(), null);
    }   
}
