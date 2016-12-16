package GUI;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.util.LinkedList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import GraphicsInterface.IRenderable;

public class ChessGraphicPanel extends JPanel implements ComponentListener
{
    private final float BORDER_MULTIPLICATOR = (float) 0.0625;
    private final float BOARD_SIZE = (float) 8.0; 
    private final String SELECTION_IMAGE = "ressources/pictures/selected.png";
    private final String BOARD_IMAGE = "ressources/pictures/chessboard.jpg";
    
    private Point2D.Double borderSize;
    private Point2D.Double squareSize;
    private Image background;
    private Image scaledBackground;    
    
    private LinkedList<IRenderable> board;
    private IRenderable[][] highlightedSquares;
    
    public ChessGraphicPanel(ChessGraphic parent, LinkedList<IRenderable> board)
    {
        this.setSize(400, 400);
        this.board = board;
        this.highlightedSquares = new IRenderable[8][8];
        
        this.borderSize = new Point2D.Double(this.getWidth() * BORDER_MULTIPLICATOR, this.getHeight() * BORDER_MULTIPLICATOR);
        this.squareSize = new Point2D.Double((this.getWidth()-borderSize.x*2.0)/8.0, (this.getHeight()-borderSize.y*2.0)/8.0);

        background = new ImageIcon(BOARD_IMAGE).getImage();
        scaledBackground = background.getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_FAST);   
        
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int clickPosX = (int)((e.getX()-borderSize.getX())/squareSize.getX());
                int clickPosY = (int)((e.getY()-borderSize.getY())/squareSize.getY());
                parent.play(clickPosX, clickPosY);
            }
        });
        
    }
    
    public void setBoard(LinkedList<IRenderable> board, IRenderable[][] highlightedSquares)
    {
        this.board = board;
        this.highlightedSquares = highlightedSquares;
    }
    
    @Override
    public void paintComponent(Graphics g)
    {
       super.paintComponent(g);
                     
       Rectangle window = this.getBounds();
       Rectangle currentContainer = null;
       
       g.drawImage(scaledBackground, 0, 0, this);
       
       for (int y = 0; y < this.highlightedSquares.length; y++) {
           for (int x = 0; x < this.highlightedSquares[y].length; x++) {
               IRenderable currentSquare = this.highlightedSquares[y][x];
               
               if (currentSquare != null) {
                   currentContainer = new Rectangle();
                   
                   currentContainer.setRect(x * squareSize.getX() + borderSize.getX(),
                                            y * squareSize.getY() + borderSize.getY(),
                                            squareSize.getX(), squareSize.getY());   
                   
                   currentSquare.render(currentContainer, window, g);                
               }
           }
       }
       
       for (int currentPieceIndex = 0; currentPieceIndex < this.board.size(); ++currentPieceIndex)
       {
           IRenderable currentPiece = this.board.get(currentPieceIndex);
           
           if (currentPiece != null)
           {
               currentContainer = new Rectangle();
               
               currentContainer.setRect(((currentPieceIndex % BOARD_SIZE) * squareSize.getX()) + borderSize.getX(),
                                        ((int)(currentPieceIndex / BOARD_SIZE) * squareSize.getY()) + borderSize.getY(),
                                        squareSize.getX(), squareSize.getY());   
               
               currentPiece.render(currentContainer, window, g);                
           }
       }
    } 
    
    @Override
    public void componentResized(ComponentEvent arg0)
    {
        this.borderSize = new Point2D.Double(this.getWidth() * BORDER_MULTIPLICATOR, this.getHeight() * BORDER_MULTIPLICATOR);
        this.squareSize = new Point2D.Double((this.getWidth()-borderSize.x*2.0)/8.0, (this.getHeight()-borderSize.y*2.0)/8.0);

        if (background == null) 
            background = new ImageIcon(BOARD_IMAGE).getImage();
        
        scaledBackground = background.getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_FAST);                       
    }

    @Override
    public void componentHidden(ComponentEvent arg0)
    {
    }

    @Override
    public void componentMoved(ComponentEvent arg0)
    {
    }
    
    @Override
    public void componentShown(ComponentEvent arg0)
    {
    }
}
