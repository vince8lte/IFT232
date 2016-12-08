package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;

import Piece.Piece;
import classes.Board;
import classes.Player;

public class ChessGame {
    
    private JFrame window;
    private Board board;
    JMenuBar menuBar;
    JMenu menu;
    JMenuItem menuItem;
    
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
        window.getContentPane().add(board, BorderLayout.CENTER);
        window.setVisible(true);
        window.addComponentListener(board);
    }
}