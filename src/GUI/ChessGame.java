package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import Piece.Piece;
import classes.Board;

public class ChessGame extends JPanel implements ComponentListener {
    
    private JFrame window;
    private Image background;
    private Image scaledBackground;
    private Image piece;
    private Image scaledPiece;
    private int x,y;
    
    private Board board;

    //déclaration de la bordure et de la taille d'un carré
    private double squareSizeX;
    private double squareSizeY;
    private double borderSizeX;
    private double borderSizeY;
    
    public ChessGame(int x, int y)
    {
    	//initialisation du board
    	this.board = new Board();
    	
        // Initialisation de la fenêtre
        window = new JFrame();
        window.setSize(x, y);
        window.setTitle("Jeu d'échec");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLocationRelativeTo(null);
        window.setResizable(true);
        window.getContentPane().add(this, BorderLayout.CENTER);
        window.setVisible(true);
        window.addComponentListener(this);
        
        this.borderSizeX = this.getWidth()*0.0625;
        this.borderSizeY = this.getHeight()*0.0625;
        this.squareSizeX = (this.getWidth()-borderSizeX*2.0)/8.0;
        this.squareSizeY = (this.getHeight()-borderSizeY*2.0)/8.0;
        
        addMouseListener(new MouseAdapter() {
            
            @Override
            public void mouseClicked(MouseEvent e) {
                //on recupère la position dans le board
                int clickPosX = (int)((e.getX()-borderSizeX)/squareSizeX);
                int clickPosY = (int)((e.getY()-borderSizeY)/squareSizeY);
               
                // si on a cliquer sur une piece
                if(board.haveSelectedPiece())
                {
                	// Si on reclique sur la piece selectionner, on veut d�selectionner la piece presentement selectionne.
                	if(board.getPieceAt(clickPosX, clickPosY) == board.getSelectedPiece())
                		board.unselectPiece();
                	else
                		board.movePiece(clickPosX, clickPosY);                	                	
                }               
                else {
                	board.selectPiece(clickPosX, clickPosY);
                }
                
                SwingUtilities.updateComponentTreeUI(window);
            }
        });
        
        // Initialisation du JPanel
        // this.setPreferredSize(new Dimension(x, y));
        
        // Initialisation des pièces
 
    }
    //faire un constructeur qui recoit un nom de fichier
    //correspondant a une sauvegarde de la matrice des pieces
    
    @Override
    protected void paintComponent(Graphics g) {        
        super.paintComponent(g);
        g.drawImage(scaledBackground, 0, 0, this);
        
        for (x = 0; x < board.getBoardSizeX(); ++x)
        {
            for (y = 0; y < board.getBoardSizeY(); ++y)
            {
            	Piece currentPiece = board.getPieceAt(x, y);
                if (currentPiece != null)
                {
                    piece = new ImageIcon(currentPiece.getImgUrl()).getImage(); // transform it 
                    scaledPiece = piece.getScaledInstance((int)(piece.getWidth(null)*((squareSizeX)/piece.getWidth(null))),
                            (int)(piece.getHeight(null)*((this.squareSizeY)/piece.getHeight(null))), Image.SCALE_FAST);  
                    ImageIcon newimg = new ImageIcon(scaledPiece);
                    g.drawImage(newimg.getImage(), (int)(x*this.squareSizeX+this.borderSizeX), (int)(y*this.squareSizeY+this.borderSizeY), this);
                }
            }
        }        
    }


    @Override
    public void componentResized(ComponentEvent e)
    {
        this.borderSizeX = this.getWidth()*0.0625;
        this.borderSizeY = this.getHeight()*0.0625;
        this.squareSizeX = (this.getWidth()-borderSizeX*2.0)/8.0;
        this.squareSizeY = (this.getHeight()-borderSizeY*2.0)/8.0;

        if (background == null) {
            background = new ImageIcon("ressources/pictures/chessboard.jpg").getImage();
        }
        scaledBackground = background.getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_DEFAULT);
       
        /*
            if (scaledPiece == null) {
                piece = new ImageIcon(pieces[x][y].getImgUrl()).getImage();
            }
            
            piece = new ImageIcon(pieces[x][y].getImgUrl()).getImage();
            scaledPiece = piece.getScaledInstance((int)(piece.getWidth(null)*((squareSizeX)/piece.getWidth(null))),
                    (int)(piece.getHeight(null)*((squareSizeY)/piece.getHeight(null))), Image.SCALE_FAST);
        */
    }

    @Override
    public void componentShown(ComponentEvent arg0){}
    @Override
    public void componentHidden(ComponentEvent e){}
    @Override
    public void componentMoved(ComponentEvent e){}
}
