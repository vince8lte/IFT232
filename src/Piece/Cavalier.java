package Piece;

import java.awt.Point;

import classes.Board;
import classes.Grid;
import classes.Player;
import classes.Square;

public class Cavalier extends Piece {

	public Cavalier(Player.Color color, Board b) {
		super(color, b);
        this.imgUrl = "ressources/pictures/cavalier";
        if(this.getColor() == Player.Color.WHITE){
        	this.imgUrl += "b.png";
        }else{
        	this.imgUrl += "n.png";
        }
	}

	@Override
	public boolean canMoveTo(Square square) {
	    return square.isHighlighted();
	}

	@Override
	protected boolean freeWay(int x, int y, Board b) {
		return false;
	}

	@Override
	public void highlightPossibleMove() {
	    Grid grid = board.getGrid();
        Point pos = this.square.getPos();
        
        Square square = grid.getSquare(pos.x+1, pos.y+2);
        this.tryHighlight(square);
            
        square = grid.getSquare(pos.x-1, pos.y+2);
        this.tryHighlight(square);
        
        square = grid.getSquare(pos.x+1, pos.y-2);
        this.tryHighlight(square);
        
        square = grid.getSquare(pos.x-1, pos.y-2);
        this.tryHighlight(square);
        
        square = grid.getSquare(pos.x+2, pos.y+1);
        this.tryHighlight(square);
        
        square = grid.getSquare(pos.x+2, pos.y-1);
        this.tryHighlight(square);
        
        square = grid.getSquare(pos.x-2, pos.y+1);
        this.tryHighlight(square);
        
        square = grid.getSquare(pos.x-2, pos.y-1);
        this.tryHighlight(square);
		
	}

}
