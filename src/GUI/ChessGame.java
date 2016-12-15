package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;

import com.sun.glass.events.MouseEvent;

import Action.*;
import classes.Board;
import classes.Player;

public class ChessGame implements ComponentListener {
    
    private Board board;
    private ChessGraphic graphic;
    
    Player[] joueur;
    int joueurActuel;		//Indique le joueur à qui est le tour
    
    public ChessGame() 
    {
    	this.board = new Board();
    	
    	this.graphic = new ChessGraphic(this.board);
    	
    	this.joueur = new Player[2];
    	this.joueur[0] = new Player(Player.Color.WHITE);
    	this.joueur[1] = new Player(Player.Color.BLACK);
    	this.joueurActuel = 0;
    	
 
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
	public void componentResized(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void componentMoved(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void componentShown(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void componentHidden(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}
}