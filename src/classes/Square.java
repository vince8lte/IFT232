package classes;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Point2D;

import javax.swing.JComponent;

import Piece.Piece;

public class Square {
	private Piece piece;
	private Board board;
	private boolean isHighlighted;
	private boolean isSelected;
	private Point pos;
	
	public Square(Point pos, Board board) {
		this(null, pos, board);
	}
	
	public Square(Piece piece, Point pos, Board board) {
		this.board = board;
		this.pos = pos;
		setPiece(piece);
	}
	
	public void setPiece(Piece piece) {
		this.piece = piece;
		if (this.piece != null)
			this.piece.setSquare(this);
	}
	
	public void paintComponent(Rectangle rec, Graphics g) {
		if (isSelected || isHighlighted) {
			Image scaledPiece = board.getScaledImage("ressources/pictures/selected.png");
			g.drawImage(scaledPiece, rec.x, rec.y, null);
		}
		if (piece != null)
			piece.paintComponent(rec, g);
	}
	
	public void isSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}
	
	public void isHighlighted(boolean isHighlighted) {
		this.isHighlighted = isHighlighted;
	}
	
	public boolean isHighlighted() {
		return this.isHighlighted;
	}
	
	public boolean isEmpty() {
		return piece == null;
	}
	
	public Piece getPiece() {
		return piece;
	}
	
	public Point getPos() {
		return pos;
	}
	
	public void movePieceTo(Square square) {
		square.setPiece(this.piece);
		piece = null;
	}
	
	public void swapPiece(Square square) {
		Piece piece = square.getPiece();
		square.setPiece(this.piece);
		this.setPiece(piece);
	}
}
