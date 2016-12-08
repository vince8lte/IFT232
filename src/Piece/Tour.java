package Piece;

import java.awt.Point;

import classes.Board;
import classes.Grid;
import classes.Player;
import classes.Square;

public class Tour extends PieceSpeciale {

	public Tour(Player.Color color, Board board) {
		super(color, board);
        this.imgUrl = "ressources/pictures/tour";
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
	protected boolean freeWay(Point Pos, Point nextPos) {
	    return false;
	}

	@Override
	public void highlightPossibleMove() {
	    Grid grid = board.getGrid();
        Point pos = this.square.getPos();
        
        // Mouvement en forme de croix , on arrete si la case n'est pas possible
        // ou si la case est possible, mais qu'une piece est presente
        
        for (int i = 1;;i++){
            Square square = grid.getSquare(pos.x+i, pos.y);
            if (!this.tryHighlight(square)) break;
            else if (square != null && !square.isEmpty()) break;
        }
        for (int i = 1;;i++){
            Square square = grid.getSquare(pos.x-i, pos.y);
            if (!this.tryHighlight(square)) break;
            else if (square != null && !square.isEmpty()) break;
        }
        for (int i = 1;;i++){
            Square square = grid.getSquare(pos.x, pos.y+i);
            if (!this.tryHighlight(square)) break;
            else if (square != null && !square.isEmpty()) break;
        }
        for (int i = 1;;i++){
            Square square = grid.getSquare(pos.x, pos.y-i);
            if (!this.tryHighlight(square)) break;
            else if (square != null && !square.isEmpty()) break;
        }
    }
}
