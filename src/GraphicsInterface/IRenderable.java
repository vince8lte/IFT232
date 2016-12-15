package GraphicsInterface;

import java.awt.Graphics;
import java.awt.Rectangle;

public interface IRenderable
{
    public void render(Rectangle container, Rectangle parentContainer, Graphics g);
}
