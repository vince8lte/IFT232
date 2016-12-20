package Utils;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public final class ImgUtils
{
    public static Image getScaledImage(String imgUrl, Rectangle container, Rectangle biggerContainer) {
        Image image = new ImageIcon(imgUrl).getImage();
        
        Image scaledImage = image.getScaledInstance( (int)(biggerContainer.getWidth() * (container.getWidth() / biggerContainer.getWidth())),
                                                     (int)(biggerContainer.getHeight() * (container.getHeight() / biggerContainer.getHeight())), 
                                                     Image.SCALE_FAST);
        
        Image newimg = new ImageIcon(scaledImage).getImage();
        
        return newimg;
    }
}
