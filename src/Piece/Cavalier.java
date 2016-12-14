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
        this.initPattern();
        if(this.getColor() == Player.Color.WHITE){
        	this.imgUrl += "b.png";
        }else{
        	this.imgUrl += "n.png";
        }
	}

	protected void initPattern() {
		// TODO Auto-generated method stub
		this.patterns= new PiecePatterns[8];
		patterns[0] = new PiecePatterns(1,2,1,false);
		patterns[1] = new PiecePatterns(1,-2,1,false);
		patterns[2] = new PiecePatterns(-1,2,1,false);
		patterns[3] = new PiecePatterns(-1,-2,1,false);
		patterns[4] = new PiecePatterns(2,1,1,false);
		patterns[5] = new PiecePatterns(2,-1,1,false);
		patterns[6] = new PiecePatterns(-2,1,1,false);
		patterns[7] = new PiecePatterns(-2,-1,1,false);
	}
	@Override
	public boolean canMoveTo(Square square) {
	    return square.isHighlighted();
	}

	/*@Override
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
		
	}*/
}
