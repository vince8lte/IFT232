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
import classes.Player;

public class ChessGame {
    
    private JFrame window;
    private Board board;
    
    public ChessGame(Dimension windowSize)
    {
    	this.board = new Board();
    	
        window = new JFrame();
        window.setSize(windowSize);
        window.setTitle("Chess Game");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLocationRelativeTo(null);
        window.setResizable(true);
        window.getContentPane().add(board, BorderLayout.CENTER);
        window.setVisible(true);
        window.addComponentListener(board);
    }
    
    //faire un constructeur qui recoit un nom de fichier
    //correspondant a une sauvegarde de la matrice des pieces
}
