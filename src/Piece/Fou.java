package Piece;

import java.awt.Point;

import classes.Board;
import classes.Grid;
import classes.Player;
import classes.Square;

public class Fou extends Piece {

	public Fou(Player.Color color, Board b) {
		super(color, b);
		this.initPattern();
		this.imgUrl = "ressources/pictures/fou";
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
	protected void initPattern() {
		// TODO Auto-generated method stub
		this.patterns= new PiecePatterns[4];
		patterns[0] = new PiecePatterns(1,1,8,false);
		patterns[1] = new PiecePatterns(1,-1,8,false);
		patterns[2] = new PiecePatterns(-1,1,8,false);
		patterns[3] = new PiecePatterns(-1,-1,8,false);
	}

	/*@Override
	public void highlightPossibleMove() {
	    //Grid grid = board.getGrid();
        //Point pos = this.square.getPos();
        
        // Mouvement en forme de X , on arrete si la case n'est pas possible
        // ou si la case est possible, mais qu'une piece est presente
        
	    for (int i = 1;;i++){
	        Square square = grid.getSquare(pos.x+i, pos.y+i);
	        if (!this.tryHighlight(square)) break;
	        else if (square != null && !square.isEmpty()) break;
        }
	    for (int i = 1;;i++){
            Square square = grid.getSquare(pos.x+i, pos.y-i);
            if (!this.tryHighlight(square)) break;
            else if (square != null && !square.isEmpty()) break;
        }
	    for (int i = 1;;i++){
            Square square = grid.getSquare(pos.x-i, pos.y+i);
            if (!this.tryHighlight(square)) break;
            else if (square != null && !square.isEmpty()) break;
        }
	    for (int i = 1;;i++){
            Square square = grid.getSquare(pos.x-i, pos.y-i);
            if (!this.tryHighlight(square)) break;
            else if (square != null && !square.isEmpty()) break;
        }
	}*/
}
