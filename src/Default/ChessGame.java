package Default;

import java.awt.Frame;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ChessGame extends Frame {
    
    private JPanel background;
    
    public ChessGame()
    {
        background = new JPanel();
        this.add(new JLabel(new ImageIcon("src/pictures/board.png")));
    }
    
	public static void main(String[] args) {	    
	    ChessGame tata = new ChessGame();
	    tata.setVisible(true);
	}

}
