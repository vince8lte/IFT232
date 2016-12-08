package Piece;

import java.awt.Point;

import classes.Board;
import classes.Grid;
import classes.Player;
import classes.Square;

public class Roi extends PieceSpeciale{

	public Roi(Player.Color color, Board board) {
		super(color, board);
        this.imgUrl = "ressources/pictures/roi";
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
        
        for (int i = -1;i <= 1;i++){
            for (int j = -1; j <= 1;j++){
                Square square = grid.getSquare(pos.x+i, pos.y+j);
                this.tryHighlight(square);
            }
        }
	}
}
