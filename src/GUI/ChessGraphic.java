package GUI;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;

import Action.LoadAction;
import Action.RestartAction;
import Action.SaveAction;
import classes.Board;

public class ChessGraphic extends JPanel {
	
	private final float BORDER_MULTIPLICATOR = (float) 0.0625;
	private final float BOARD_SIZE = (float) 8.0; 
	private final String SELECTION_IMAGE = "ressources/pictures/selected.png";
	private final String BOARD_IMAGE = "ressources/pictures/chessboard.jpg";
	private static final long serialVersionUID = 1L;
	
	private JFrame window;
    
    private Point2D.Double borderSize;
    private Point2D.Double squareSize;
    
    public ChessGraphic(Board board){
    	
    	//Instancie la taille du border
    	this.borderSize = new Point2D.Double((this.getWidth()*BORDER_MULTIPLICATOR), (this.getHeight()*BORDER_MULTIPLICATOR));
    	
    	//Instancie la taille d'une case
        this.squareSize = new Point2D.Double(((this.getWidth()-borderSize.x*2.0)/BOARD_SIZE), ((this.getHeight()-borderSize.y*2.0)/BOARD_SIZE));
        
    	this.initWindow(new Dimension(500, 500), board);
    	
    	//Crée tout les boutons du menu
    	this.createMenuTool(window.getJMenuBar().getMenu(0), "Restart game", new RestartAction(board),KeyEvent.VK_R);
    	this.createMenuTool(window.getJMenuBar().getMenu(0), "Load from file", new LoadAction(window, board),KeyEvent.VK_L);
    	this.createMenuTool(window.getJMenuBar().getMenu(0), "Save game to file", new SaveAction(window, board),KeyEvent.VK_S);
    }
    
    public void createMenuTool(JMenu menu, String itemName, ActionListener action, int keyCode){

        JMenuItem menuItem;
        
        menuItem = new JMenuItem(itemName,keyCode);
    	menuItem.addActionListener(action);
    	menu.add(menuItem);
    }
    
    public void createMenuTool(JMenu menu, String itemName, ActionListener action){

        JMenuItem menuItem;
        
        menuItem = new JMenuItem(itemName);
    	menuItem.addActionListener(action);
    	menu.add(menuItem);
    }
    
    
    public void paintComponent(Point2D.Double borderSize, Point2D.Double squareSize,Graphics g)
    {
        for (int i = 0; i < (int)BOARD_SIZE; ++i) {
            for ( int j = 0; j < (int)BOARD_SIZE; ++j) {
                Rectangle rec = new Rectangle();
                rec.setRect(borderSize.x + squareSize.x*i, borderSize.y + squareSize.y*j, squareSize.x, squareSize.y);
                //paintComponent(rec, g);
            }
        }
    }

    public void paintComponent(String imgUrl, Rectangle rec, Graphics g) {
    	Image scaledImage = scaledImage(imgUrl);
        g.drawImage(scaledImage, rec.x, rec.y, null);
    }
    
    /*
    @Override
    protected void paintComponent(Graphics g) {        
        super.paintComponent(g);
        //g.drawImage(scaledImage(), 0, 0, this);
    }*/
    
    @Override
    protected void paintComponent(Graphics g) {
    	super.paintComponent(g);
        g.drawImage(getScaledBackground(), 0, 0, this);
    }

/*
    @Override
    public void componentResized(ComponentEvent e)
    {
    	this.borderSize = new Point2D.Double(this.getWidth()*0.0625, this.getHeight()*0.0625);
        this.squareSize = new Point2D.Double((this.getWidth()-borderSize.x*2.0)/8.0, (this.getHeight()-borderSize.y*2.0)/8.0);
    }*/
    
    private void initWindow(Dimension windowSize, Board board){
 	
        JMenu menu;
        
        window = new JFrame();
        
        //Create the menu bar.
        window.setJMenuBar(new JMenuBar());
    	menu = new JMenu("File");
    	menu.setMnemonic(KeyEvent.VK_F);
        window.getJMenuBar().add(menu);
    	
        // Set Window
        window.setSize(windowSize);
        window.setTitle("Chess Game");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLocationRelativeTo(null);
        window.setResizable(true);
        //window.getContentPane().add(board, BorderLayout.CENTER);
        window.getContentPane().setLayout(new FlowLayout());
        window.setVisible(true);
    }
    
    private Image scaledImage(String imgUrl) {
    	Image image = new ImageIcon(imgUrl).getImage();
    	Image scaledImage = image.getScaledInstance((int)(this.getWidth()*((squareSize.x)/this.getWidth())),
                (int)(this.getHeight()*((squareSize.y)/this.getHeight())), Image.SCALE_FAST);
    	Image newimg = new ImageIcon(scaledImage).getImage();
    	return newimg;
    }
    
    private Image getScaledBackground(){
    	Image Background = new ImageIcon(BOARD_IMAGE).getImage();
    	return Background.getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_FAST);
    }
    

    
    /*
    public void highlightCase(Point[] posCase){
    	
    }
    
    public void PaintMouvement(Point[] posDepart, Point[] posArriver, String )
    
    public void paintComponent(Rectangle rec, Graphics g) {
		if (isSelected || isHighlighted) {
			Image scaledPiece = board.getScaledImage(SELECTION_IMAGE);
			g.drawImage(scaledPiece, rec.x, rec.y, null);
		}
		if (piece != null)
			piece.paintComponent(rec, g);
	}

	public void isHighlighted(boolean isHighlighted) {
		this.isHighlighted = isHighlighted;
	}
	
	public boolean isHighlighted() {
		return this.isHighlighted;
	}

	
    
    public boolean tryHighlight(Square square)
    {
        if (square != null)
        {
            if(square.isEmpty()) square.isHighlighted(true);
            else if (this.getColor() != square.getPiece().getColor()) square.isHighlighted(true);
            if (square.isHighlighted()) return true;
        }
        return false;
    }*/
}
