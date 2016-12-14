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
		patterns[0] = new PiecePatterns(1,0,1,false);
		patterns[1] = new PiecePatterns(-1,0,1,false);
		patterns[2] = new PiecePatterns(0,1,1,false);
		patterns[3] = new PiecePatterns(0,-1,1,false);
		patterns[4] = new PiecePatterns(1,1,1,false);
		patterns[5] = new PiecePatterns(1,-1,1,false);
		patterns[6] = new PiecePatterns(-1,1,1,false);
		patterns[7] = new PiecePatterns(-1,-1,1,false);
	}
	
	@Override
	public boolean canMoveTo(Square square) {
	    return square.isHighlighted();
	}

	/*@Override
	public void highlightPossibleMove() {
	    Grid grid = board.getGrid();
        Point pos = this.square.getPos();
        
        for (int i = -1;i <= 1;i++){
            for (int j = -1; j <= 1;j++){
                Square square = grid.getSquare(pos.x+i, pos.y+j);
                this.tryHighlight(square);
            }
        }
	}*/
}
