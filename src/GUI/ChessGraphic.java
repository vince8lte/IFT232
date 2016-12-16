package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
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

public class ChessGraphic extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	private ChessGame game;
	private ChessGraphicPanel panel;

    public ChessGraphic(ChessGame game, LinkedList<IRenderable> board){
        this.game = game;       
        this.panel = new ChessGraphicPanel(this, board);
                       
        this.setSize(800, 600);
        this.setTitle("Chess Game");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(true);
        this.getContentPane().add(this.panel, BorderLayout.CENTER);
        this.setVisible(true);
        this.addComponentListener(this.panel);
        
        JMenu menu = new JMenu("File");
        menu.setMnemonic(KeyEvent.VK_F);
        
        this.setJMenuBar(new JMenuBar());
        this.getJMenuBar().add(menu);
        
        //Cr�e tout les boutons du menu
    //  this.createMenuTool(window.getJMenuBar().getMenu(0), "Restart game", new RestartAction(board),KeyEvent.VK_R);
        //this.createMenuTool(window.getJMenuBar().getMenu(0), "Load from file", new LoadAction(window, board),KeyEvent.VK_L);
    //  this.createMenuTool(window.getJMenuBar().getMenu(0), "Save game to file", new SaveAction(window, board),KeyEvent.VK_S);

    }
    
    public void play(int clickPosX, int clickPosY)
    {
        game.play(clickPosX, clickPosY);
    }
    
    public void setBoard(LinkedList<IRenderable> board)
    {
        panel.setBoard(board);
    }
    
    public void paintGUI()
    {
        panel.repaint();
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
}
