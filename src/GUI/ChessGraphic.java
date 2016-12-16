package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import java.awt.geom.Point2D;
import java.io.File;
import java.util.LinkedList;
import GraphicsInterface.IRenderable;

public class ChessGraphic extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	private ChessGame game;
	private ChessGraphicPanel panel;

    public ChessGraphic(ChessGame game, LinkedList<IRenderable> board){
    	JMenu menu = null;
    	JMenuItem restartGameItem = null;
    	JMenuItem loadGameItem = null;
    	JMenuItem saveGameItem = null;
    	
        this.game = game;       
        this.panel = new ChessGraphicPanel(this, board);
                       
        this.setSize(800, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(true);
        this.getContentPane().add(this.panel, BorderLayout.CENTER);
        this.setVisible(true);
        this.addComponentListener(this.panel);
        this.showGameStatus("New game");
        
        menu = new JMenu("File");
        menu.setMnemonic(KeyEvent.VK_F);
        
        this.setJMenuBar(new JMenuBar());
        this.getJMenuBar().add(menu);
        
        //Cr�e tout les boutons du menu
        restartGameItem = new JMenuItem("Recommencer la partie", KeyEvent.VK_R);
        loadGameItem = new JMenuItem("Charger une partie", KeyEvent.VK_L);
        saveGameItem = new JMenuItem("Sauvegarder la partie", KeyEvent.VK_S);
        
        menu.add(restartGameItem);
        menu.add(loadGameItem);
        menu.add(saveGameItem);
        
        restartGameItem.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) { 
        	    game.loadDefaultBoard();
        	}
        });
        
        loadGameItem.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) { 
    		  JFileChooser fileChooser = new JFileChooser();
    	      
    		  fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
    	      
    		  int result = fileChooser.showOpenDialog(panel.getParent());
		       
    		  if (result == JFileChooser.APPROVE_OPTION) {
		            File selectedFile = fileChooser.getSelectedFile();
		            game.loadBoard(selectedFile.getPath());    	            
		      } 
        	}
        });
        
        saveGameItem.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) { 
      		  JFileChooser fileChooser = new JFileChooser();
    	      
      		  fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
      	      
      		  int result = fileChooser.showSaveDialog(panel.getParent());
  		       
      		  if (result == JFileChooser.APPROVE_OPTION) {
  		            File selectedFile = fileChooser.getSelectedFile();
  		            game.saveBoard(selectedFile.getPath());    	            
  		      } 
        	}
        });
    }
    
    public void showGameStatus(String stausName){
    	setTitle("Chess Game " + stausName);
    }
    
    public void play(int clickPosX, int clickPosY)
    {
        game.play(clickPosX, clickPosY);
    }
    
    public void setBoard(LinkedList<IRenderable> board, IRenderable[][] highlightedSquares)
    {
        panel.setBoard(board, highlightedSquares);
        panel.repaint();
    }
    
    public void paintGUI()
    {
        panel.repaint();
    }
}
