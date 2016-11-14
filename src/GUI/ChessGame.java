package GUI;

import java.awt.Frame;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ChessGame extends JFrame {
    
    private JLabel background;
    
    private ChessGame()
    {
    }
    
    public ChessGame(int x, int y)
    {
        background = new JLabel(new ImageIcon(new ImageIcon("src/pictures/chessboard.jpg").getImage().getScaledInstance(x, y, Image.SCALE_DEFAULT)));
        this.setSize(x, y);        
        this.add(background);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);        
    }
    
	public static void main(String[] args) {	    
	    ChessGame tata = new ChessGame(500, 500);
	    tata.setVisible(true);
	}

}
