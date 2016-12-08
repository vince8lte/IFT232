package Piece;

import java.awt.Point;

import classes.Board;
import classes.Player;
import classes.Square;

public class Pion extends PieceSpeciale {

	private boolean coupSpecial = true;
	
	public Pion(Player.Color color, Board board) {
		super(color, board);
        this.imgUrl = "ressources/pictures/pion";
        if(this.getColor() == Player.Color.WHITE){
        	this.imgUrl += "b.png";
        }else{
        	this.imgUrl += "n.png";
        }
	}

	@Override
	public void highlightPossibleMove() {
		Square[][] squares = board.getSquares();
		Point pos = this.square.getPos();
		int signY = (this.getColor() == Player.Color.WHITE) ? 1 : -1;
		// Un coup par en avant
		if (squares[pos.x][pos.y + signY] != null && squares[pos.x][pos.y + signY].isEmpty()) {
			squares[pos.x][pos.y + signY].isHighlighted(true);
			// deux coup par en avant
			if (squares[pos.x][pos.y + signY*2] != null && squares[pos.x][pos.y + signY*2].isEmpty() && coupSpecial == true) {
				squares[pos.x][pos.y + signY*2].isHighlighted(true);
			}
		}
		// Un coup en diagonale (capturer)
		if (squares[pos.x-1][pos.y + signY] != null && !squares[pos.x-1][pos.y + signY].isEmpty() &&
				this.getColor() != squares[pos.x-1][pos.y + signY].getPiece().getColor()) {
			squares[pos.x-1][pos.y + signY].isHighlighted(true);
		}
		// Un coup dans l'autre diagonale (capturer)
		if (squares[pos.x-1][pos.y + signY] != null && !squares[pos.x-1][pos.y + signY].isEmpty() &&
				this.getColor() != squares[pos.x-1][pos.y + signY].getPiece().getColor()) {
			squares[pos.x-1][pos.y + signY].isHighlighted(true);
		}
	}

	@Override
	public boolean canMoveTo(Square square) {
		return square.isHighlighted();
	}
	public boolean canAttack(int x, int y){
		boolean resultat = false;
		
		/*if( Math.abs(x) == Math.abs(y) && ((this.getColor() == Player.Color.WHITE && (y == -1)) || (this.getColor() != Player.Color.WHITE && (y == 1)))){
			resultat = true;
		}*/
		return resultat;
	}

	@Override
	protected boolean freeWay(int x, int y, Board b) {
		return false;
	}
	

}
