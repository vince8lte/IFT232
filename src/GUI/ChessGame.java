package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import com.sun.glass.events.MouseEvent;

import classes.Board;
import classes.Player;

public class ChessGame implements ComponentListener {
    
    private JFrame window;
    private Board board;
    JMenuBar menuBar;
    JMenu menu;
    JMenuItem menuItem;
    
    Player[] joueur;
    int joueurActuel;		//Indique le joueur à qui est le tour
    
    public ChessGame(Dimension windowSize) 
    {
    	this.board = new Board();
    	joueur = new Player[2];
    	joueur[0] = new Player(Player.Color.WHITE);
    	joueur[1] = new Player(Player.Color.BLACK);
    	joueurActuel = 0;
    	

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
        //window.getContentPane().add(board, BorderLayout.CENTER);
        window.setVisible(true);
/*
        window.addMouseListener(new MouseAdapter() { 
        	@Override
        	public void mouseClicked(MouseEvent e) { 
                //on recupère la position dans le board
                int clickPosX = (int)((e.getX()-borderSizeX)/squareSizeX);
                int clickPosY = (int)((e.getY()-borderSizeY)/squareSizeY);
        		JoueUnCoup(e);
        	}
        });*/
    }
    
    //Passe au tour suivant
    private void TourSuivant(){
    	joueurActuel = (joueurActuel + 1) % 2;
    }
    
    private void JoueUnCoup(int clickedX, int clickedY){
    	
    	switch(board.Action(clickedX, clickedY)){
           
           //action effectuer lors du début du tour
    		case 0:
        	   
        	break;
        
        	//Action effectuer lors de la selection d'une piece
    		case 1:
    			board.highlightPossibleMoves();
        	break;
        	   
        	//Action effetuer lors de la fin du tour
    		case 2:
    			TourSuivant();
        	break;
           
       }
            
            //SwingUtilities.updateComponentTreeUI(window);
            //repaint();
    }

	@Override
	public void componentResized(ComponentEvent e) {}

	@Override
	public void componentMoved(ComponentEvent e) {}

	@Override
	public void componentShown(ComponentEvent e) {}

	@Override
	public void componentHidden(ComponentEvent e) {}
}