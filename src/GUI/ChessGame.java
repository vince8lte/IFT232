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
import Piece.Piece;
import classes.Board;
import classes.Player;
import classes.Player.Color;

public class ChessGame implements ComponentListener {
    
    private Board board;
    private ChessGraphic graphic;
    
    Player[] joueur;
    int joueurActuel;		//Indique le joueur � qui est le tour
    
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
                //on recup�re la position dans le board
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
    
    private void play(int x, int y){
        if (board.pieceIsSelected())
        {            
            Color teamColorPieceRecipient = board.getTeamColorFromPiece(x, y);
            
            // S'il n'existe pas une pièce destinataire OU 
            // Si la pièce présentement sélectionné et la pièce destinataire ne sont pas de la même équipe
            if ((teamColorPieceRecipient == Color.NONE) || 
                (teamColorPieceRecipient != board.getTeamColorFromSelectedPiece()))
            {
                // Si le déplacement de la pièce a fonctionné
                //if (board.moveSelectedPieceTo(x, y))
                  //  graphic.refresh
                
            }
        }
        else
        {
            
        }
        if (board.getSelectedPiece())
    	if (board.pieceExists(X, Y))
    	{
    	    board.
    	}
    	switch(board.Action(clickedX, clickedY)){
           
           //action effectuer lors du d�but du tour
    		case 0:
        	   //
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