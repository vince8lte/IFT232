package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import classes.Board;
import classes.Player;

public class ChessGame {
    
    private JFrame window;
    private Board board;
    JMenuBar menuBar;
    JMenu menu;
    JMenuItem menuItem;
	private Player[] players;
	private Player activePlayer;
    
    public ChessGame(Dimension windowSize)
    {
    	this.board = new Board();
    	
    	//Create the menu bar.
    	menuBar = new JMenuBar();
    	menu = new JMenu("File");
    	menu.setMnemonic(KeyEvent.VK_F);
    	
    	menuItem = new JMenuItem("Restart game",KeyEvent.VK_R);
    	menuItem.addActionListener(new RestartAction(board));
    	menu.add(menuItem);
    	
    	menuItem = new JMenuItem("Load from file",KeyEvent.VK_L);
    	menuItem.addActionListener(new LoadAction(window, board));
        menu.add(menuItem);
        
        menuItem = new JMenuItem("Save game to file",KeyEvent.VK_S);
        menuItem.addActionListener(new SaveAction(window, board));
        menu.add(menuItem);
        
        menuBar.add(menu);
    	
        // Set Window
        window = new JFrame();
        window.setJMenuBar(menuBar);
        window.setSize(windowSize);
        window.setTitle("Chess Game");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLocationRelativeTo(null);
        window.setResizable(true);
        //window.getContentPane().add(board, BorderLayout.CENTER); <------- Je sais pas ca quoi faire (Patrick)
        window.setVisible(true);
    }
}