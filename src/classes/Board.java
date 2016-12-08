package classes;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import Piece.*;

public class Board extends JPanel implements ComponentListener {
	private final int BOARD_SIZE = 8;

	private Square[][] squares;
	private Square selectedSquare;
    private Image background;
    private Image scaledBackground;
    private HashMap<String, Image> pieces;
    private HashMap<String, Image> scaledPieces;
    private Point2D.Double borderSize;
    private Point2D.Double squareSize;
    
	private Player[] players;
	private Player activePlayer;
	
    
    
	public Board(){
		FlowLayout layout = (FlowLayout)this.getLayout();
		layout.setVgap(0);
		
		players = new Player[2];
		players[0] = new Player(Player.Color.BLACK);
		players[1] = new Player(Player.Color.WHITE);
		activePlayer = players[1];
		
		
        this.borderSize = new Point2D.Double(this.getWidth()*0.0625, this.getHeight()*0.0625);
        this.squareSize = new Point2D.Double((this.getWidth()-borderSize.x*2.0)/8.0, this.getHeight()-borderSize.y*2.0/8.0);

		squares = new Square[BOARD_SIZE][BOARD_SIZE];
		
		for(int i = 0; i < squares.length; ++i) {
			for (int j = 0; j < squares[i].length; ++j) {
				Square square = new Square(new Point(i, j), this);
				squares[i][j] = square;
			}
		}
		// Ce code peut être remplacé lorsqu'on sera capable de charger les squares à partir d'un fichier
		for (int y = 0; y < squares.length; ++y)
		{
			if(y==1 || y==6){
				if(y%2==0){
					for(int x =0;x<squares.length;++x){
						squares[x][y].setPiece(new Pion(Player.Color.BLACK, this)); // black
					}
				}else{
					for(int x =0;x<squares.length;++x){
						squares[x][y].setPiece(new Pion(Player.Color.WHITE, this)); // white
					}
				}
			}
			else if(y==0 || y==7){
				if(y%2==0){
					squares[0][y].setPiece(new Tour(Player.Color.WHITE, this)); // all white
					squares[1][y].setPiece(new Cavalier(Player.Color.WHITE, this));
					squares[2][y].setPiece(new Fou(Player.Color.WHITE, this));
					squares[3][y].setPiece(new Roi(Player.Color.WHITE, this));
					squares[4][y].setPiece(new Reine(Player.Color.WHITE, this));
					squares[5][y].setPiece(new Fou(Player.Color.WHITE, this));
					squares[6][y].setPiece(new Cavalier(Player.Color.WHITE, this));
					squares[7][y].setPiece(new Tour(Player.Color.WHITE, this));
				}else{
					squares[0][y].setPiece(new Tour(Player.Color.BLACK, this)); // all black
					squares[1][y].setPiece(new Cavalier(Player.Color.BLACK, this));
					squares[2][y].setPiece(new Fou(Player.Color.BLACK, this));
					squares[3][y].setPiece(new Reine(Player.Color.BLACK, this));
					squares[4][y].setPiece(new Roi(Player.Color.BLACK, this));
					squares[5][y].setPiece(new Fou(Player.Color.BLACK, this));
					squares[6][y].setPiece(new Cavalier(Player.Color.BLACK, this));
					squares[7][y].setPiece(new Tour(Player.Color.BLACK, this));
				}
			}
		}
		
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            	int clickPosX = (int)((e.getX()-borderSize.x)/squareSize.x);
                int clickPosY = (int)((e.getY()-borderSize.y)/squareSize.y);
                click(squares[clickPosX][clickPosY]);
                repaint();
            }
        });
	}

	public void click(Square square) {
		if(selectedSquare != null) {
			movePiece(square);
		}
		else {
			selectSquare(square);
		}
	}
	
    @Override
    protected void paintComponent(Graphics g) {        
        super.paintComponent(g);
        g.drawImage(scaledBackground, 0, 0, this);
        for (int i = 0; i < squares.length; ++i) {
        	for ( int j = 0; j < squares[i].length; ++j) {
        		Rectangle rec = new Rectangle();
        		rec.setRect(borderSize.x + squareSize.x*i, borderSize.y + squareSize.y*j, squareSize.x, squareSize.y);
        		squares[i][j].paintComponent(rec, g);
        	}
        }
    }


    @Override
    public void componentResized(ComponentEvent e)
    {
    	this.borderSize = new Point2D.Double(this.getWidth()*0.0625, this.getHeight()*0.0625);
        this.squareSize = new Point2D.Double((this.getWidth()-borderSize.x*2.0)/8.0, (this.getHeight()-borderSize.y*2.0)/8.0);

        if (background == null) {
            background = new ImageIcon("ressources/pictures/chessboard.jpg").getImage();
        }
        scaledBackground = background.getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_FAST);
        
        /*if (scaledPieces == null) {
            pieces = new ImageIcon(squares[x][y].getImgUrl()).getImage();
        }
        piece = new ImageIcon(pieces[x][y].getImgUrl()).getImage();
        scaledPiece = piece.getScaledInstance((int)(piece.getWidth(null)*((squareSizeX)/piece.getWidth(null))),
                (int)(piece.getHeight(null)*((squareSizeY)/piece.getHeight(null))), Image.SCALE_FAST);*/
    }
    
    public Image getScaledImage(String imgUrl) {
    	Image image = new ImageIcon(imgUrl).getImage();
    	Image scaledImage = image.getScaledInstance((int)(this.getWidth()*((squareSize.x)/this.getWidth())),
                (int)(this.getHeight()*((squareSize.y)/this.getHeight())), Image.SCALE_FAST);
    	Image newimg = new ImageIcon(scaledImage).getImage();
    	return newimg;
    }
    
    public Square[][] getSquares() {
    	return squares;
    }

	private boolean selectSquare(Square square)
	{
		Piece selectedPiece = square.getPiece();
		if (selectedPiece == null) {
			return false;
		}
		if (activePlayer.isPieceOwner(selectedPiece)) {
			selectedSquare = square;
			square.isSelected(true);
			selectedPiece.highlightPossibleMove();
			return true;
		}
		return false;
	}

	private void movePiece(Square square)
	{
		Piece movingPiece = selectedSquare.getPiece();
		if (movingPiece.canMoveTo(square)) {
			selectedSquare.movePieceTo(square);
			selectedSquare = null;
			resetSelectedSquare();
			changeActivePlayer();
		}
		else if (square == selectedSquare) {
			selectedSquare = null;
			resetSelectedSquare();
		}
	}
	
	private void changeActivePlayer() {
		for (int i = 0; i < players.length; ++i) {
			if (players[i].getColor() == activePlayer.getColor()) {
				activePlayer = players[(i + 1) % 2];
			}
		}
	}
	
	private void resetSelectedSquare() {
		for (int i = 0; i < squares.length; ++i) {
			for (int j = 0; j < squares[i].length; ++j) {
				squares[i][j].isHighlighted(false);
				squares[i][j].isSelected(false);
			}
		}
	}

	@Override
	public void componentHidden(ComponentEvent e) {}
	@Override
	public void componentMoved(ComponentEvent e) {}
	@Override
	public void componentShown(ComponentEvent e) {}
}
