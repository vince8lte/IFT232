package GUI;

import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import classes.Piece;

public class ChessGame extends JFrame {
    
    private static String backgroundURL = "ressources/pictures/chessboard.jpg";
    
    private Piece[][] pieces;
    private ImageIcon oldBackground;
    private JLabel background;
    private ImageIcon background2;
    
    private ChessGame()
    {
    }
    
    public ChessGame(int x, int y)
    {
        // Initialisation de l'interface
        background = new JLabel(new ImageIcon(new ImageIcon(backgroundURL).getImage().getScaledInstance(x, y, Image.SCALE_DEFAULT)));
        background2 = new ImageIcon(new ImageIcon(backgroundURL).getImage().getScaledInstance(x, y, Image.SCALE_DEFAULT));
        oldBackground = new ImageIcon(backgroundURL);
        this.setSize(x, y);        
        this.add(background);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);      
        
        // Initialisation des pièces
        pieces = new Piece[8][8];
        
        for (int X = 0; X < pieces.length; ++X)
        {
            for (int Y = 0; Y < pieces[X].length; ++Y)
            {
                pieces[X][Y] = new Piece("");
            }            
        }
        
        afficher();
    }
    
    public void afficher()
    {
      /*
        for (int X = 0; X < pieces.length; ++X)
        {
            for (int Y = 0; Y < pieces[X].length; ++Y)
            {
                ImageIcon imgPiece = new ImageIcon(pieces[X][Y].toString());
                int f = imgPiece.getIconHeight();
                int t = imgPiece.getIconWidth();
                int b = oldBackground.getIconHeight();
                
                this.add(new JLabel(new ImageIcon(imgPiece.getImage().getScaledInstance((imgPiece.getIconWidth() * background2.getIconWidth()) / oldBackground.getIconWidth(), 
                                           (imgPiece.getIconHeight() * background2.getIconHeight()) / oldBackground.getIconHeight(),
                                           Image.SCALE_DEFAULT))));                       
            }            
        }
        */
        
    }
    
	public static void main(String[] args) {	    
	    ChessGame tata = new ChessGame(500, 500);
	    tata.setVisible(true);
	}

}
