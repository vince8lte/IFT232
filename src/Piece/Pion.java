package Piece;

import java.awt.Point;

import classes.Board;
import classes.Grid;
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
	protected void initPattern() {
		// TODO Auto-generated method stub
		this.patterns= new PiecePatterns[4];
		if(this.getColor() ==Player.Color.BLACK)
		{
			patterns[0] = new PiecePatterns(1,-1,1,true);
			patterns[1] = new PiecePatterns(-1,-1,1,true);
			patterns[2] = new PiecePatterns(0,-2,1,false);
			patterns[3] = new PiecePatterns(0,-1,1,false);
		}
		else
		{
			patterns[0] = new PiecePatterns(1,1,1,true);
			patterns[1] = new PiecePatterns(-1,1,1,true);
			patterns[2] = new PiecePatterns(0,2,1,false);
			patterns[3] = new PiecePatterns(0,1,1,false);
		}

	}

	/*@Override
	public void highlightPossibleMove() {
		Grid grid = board.getGrid();
		Point pos = this.square.getPos();
		int signY = (this.getColor() == Player.Color.WHITE) ? 1 : -1;
		// Un coup par en avant
		Square square = grid.getSquare(pos.x, pos.y+signY);
		if (square != null && square.isEmpty()){
		    square.isHighlighted(true);
			// deux coup par en avant
		    square = grid.getSquare(pos.x,pos.y + signY*2);
			if (square != null && square.isEmpty() && coupSpecial == true) {
				square.isHighlighted(true);
			}
		}

		// Un coup en diagonale (capturer)
		square = grid.getSquare(pos.x-1,pos.y + signY);
		if (square != null && !square.isEmpty() &&
				this.getColor() != square.getPiece().getColor()) {
			square.isHighlighted(true);
		}

		// Un coup dans l'autre diagonale (capturer)
		square = grid.getSquare(pos.x+1,pos.y + signY);
		if (square != null && !square.isEmpty() &&
                this.getColor() != square.getPiece().getColor()) {
            square.isHighlighted(true);
        }
	}*/

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
	public void hasMoved()
	{
		if(this.coupSpecial)
		{
			this.coupSpecial = false;
			PiecePatterns [] patternTemp= new PiecePatterns[3];
			patternTemp[0] = patterns[0];
			patternTemp[1] = patterns[1];
			patternTemp[2] =patterns[3];
			this.patterns = patternTemp;
		}
		
	}
}
