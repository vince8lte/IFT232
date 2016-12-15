package GUI;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import java.awt.geom.Point2D;
import java.util.LinkedList;
import GraphicsInterface.IRenderable;

public class ChessGraphic extends JFrame implements ComponentListener {
	
	private final float BORDER_MULTIPLICATOR = (float) 0.0625;
	private final float BOARD_SIZE = (float) 8.0; 
	private final String SELECTION_IMAGE = "ressources/pictures/selected.png";
	private final String BOARD_IMAGE = "ressources/pictures/chessboard.jpg";
	private static final long serialVersionUID = 1L;
	
	private JPanel panel;
    
    private Point2D.Double borderSize;
    private Point2D.Double squareSize;
    private Image background;
    private Image scaledBackground;    
    
    private LinkedList<IRenderable> board;
    
    public ChessGraphic(ChessGame game, LinkedList<IRenderable> board){
        this.board = board; 
        
        this.panel = new JPanel();
        this.panel.setPreferredSize(new Dimension(500, 500));
        this.getContentPane().add(this.panel);
        
        this.setSize(800, 600);
        this.setTitle("Chess Game");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(true);
        this.getContentPane().setLayout(new FlowLayout());
        this.setVisible(true);
        
        JMenu menu = new JMenu("File");
        menu.setMnemonic(KeyEvent.VK_F);
        
        this.setJMenuBar(new JMenuBar());
        this.getJMenuBar().add(menu);
        
        //Cr�e tout les boutons du menu
    //  this.createMenuTool(window.getJMenuBar().getMenu(0), "Restart game", new RestartAction(board),KeyEvent.VK_R);
        //this.createMenuTool(window.getJMenuBar().getMenu(0), "Load from file", new LoadAction(window, board),KeyEvent.VK_L);
    //  this.createMenuTool(window.getJMenuBar().getMenu(0), "Save game to file", new SaveAction(window, board),KeyEvent.VK_S);

        this.panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int clickPosX = (int)((e.getX()-borderSize.getX())/squareSize.getX());
                int clickPosY = (int)((e.getY()-borderSize.getY())/squareSize.getY());
                game.play(clickPosX, clickPosY);
            }
        });
    }
    
    public void setBoard(LinkedList<IRenderable> board)
    {
        this.board = board;
    }
    
    public void paintGUI()
    {
        Rectangle window = this.getBounds();
        Rectangle currentContainer = null;
        
        this.panel.getGraphics().drawImage(scaledBackground, 0, 0, this);
        
        for (int currentPieceIndex = 0; currentPieceIndex < this.board.size(); ++currentPieceIndex)
        {
            IRenderable currentPiece = this.board.get(currentPieceIndex);
            currentContainer = new Rectangle();
                        
            currentContainer.setRect(((currentPieceIndex % BOARD_SIZE) * squareSize.getX()) + borderSize.getX(),
                                     ((currentPieceIndex / BOARD_SIZE) * squareSize.getY()) + borderSize.getY(),
                                     squareSize.getX(), squareSize.getY());            
            currentPiece.render(currentContainer, window, this.panel.getGraphics());
        }
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
    
    @Override
    public void componentResized(ComponentEvent e)
    {
        this.borderSize = new Point2D.Double(this.getWidth() * BORDER_MULTIPLICATOR, this.getHeight() * BORDER_MULTIPLICATOR);
        this.squareSize = new Point2D.Double((this.getWidth()-borderSize.x*2.0)/8.0, (this.getHeight()-borderSize.y*2.0)/8.0);

        if (background == null) 
            background = new ImageIcon(BOARD_IMAGE).getImage();
        
        scaledBackground = background.getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_FAST);
        
        paintGUI();
    } 

    @Override
    public void componentHidden(ComponentEvent e)
    {
        this.borderSize = new Point2D.Double(this.getWidth() * BORDER_MULTIPLICATOR, this.getHeight() * BORDER_MULTIPLICATOR);
        this.squareSize = new Point2D.Double((this.getWidth()-borderSize.x*2.0)/8.0, (this.getHeight()-borderSize.y*2.0)/8.0);

        if (background == null) 
            background = new ImageIcon(BOARD_IMAGE).getImage();
        
        scaledBackground = background.getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_FAST);
        
        paintGUI();
    }

    @Override
    public void componentMoved(ComponentEvent e)
    {
        this.borderSize = new Point2D.Double(this.getWidth() * BORDER_MULTIPLICATOR, this.getHeight() * BORDER_MULTIPLICATOR);
        this.squareSize = new Point2D.Double((this.getWidth()-borderSize.x*2.0)/8.0, (this.getHeight()-borderSize.y*2.0)/8.0);

        if (background == null) 
            background = new ImageIcon(BOARD_IMAGE).getImage();
        
        scaledBackground = background.getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_FAST);
        
        paintGUI();
    }

    @Override
    public void componentShown(ComponentEvent e)
    {
        this.borderSize = new Point2D.Double(this.getWidth() * BORDER_MULTIPLICATOR, this.getHeight() * BORDER_MULTIPLICATOR);
        this.squareSize = new Point2D.Double((this.getWidth()-borderSize.x*2.0)/8.0, (this.getHeight()-borderSize.y*2.0)/8.0);

        if (background == null) 
            background = new ImageIcon(BOARD_IMAGE).getImage();
        
        scaledBackground = background.getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_FAST);
        
        paintGUI();
    }
}
