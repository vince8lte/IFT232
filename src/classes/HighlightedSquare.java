package classes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import GraphicsInterface.IRenderable;
import Utils.ImgUtils;

public class HighlightedSquare implements IRenderable {
    private final String IMG_URL_BLUE = "ressources/pictures/blueBackground.png";
    private final String IMG_URL_RED = "ressources/pictures/redBackground.png";
    
    private String currentImgUrl = null;
    
    public HighlightedSquare(Color color) {
        if (color == Color.BLUE) {
            currentImgUrl = IMG_URL_BLUE;
        }
        else if (color == Color.RED) {
            currentImgUrl = IMG_URL_RED;
        }
    }

    @Override
    public void render(Rectangle container, Rectangle parentContainer, Graphics g)
    {
        Image scaledImage = ImgUtils.getScaledImage(this.currentImgUrl, container, parentContainer);
        g.drawImage(scaledImage, (int)container.getX(), (int)container.getY(), null);
    }
}
