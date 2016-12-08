package classes;

import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import Piece.*;

public class Board extends JPanel implements ComponentListener {
	/**
     * 
     */
    private static final long serialVersionUID = 1L;

    private final int BOARD_SIZE = 8;

	private Grid grid;
	private Square selectedSquare;
    private Image background;
    private Image scaledBackground;
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
        
        grid = new Grid(BOARD_SIZE,this);
        
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int clickPosX = (int)((e.getX()-borderSize.x)/squareSize.x);
                int clickPosY = (int)((e.getY()-borderSize.y)/squareSize.y);
                click(grid.getSquare(clickPosX, clickPosY));
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
        grid.paintComponent(borderSize, squareSize, g);
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
    }
    
    public Image getScaledImage(String imgUrl) {
    	Image image = new ImageIcon(imgUrl).getImage();
    	Image scaledImage = image.getScaledInstance((int)(this.getWidth()*((squareSize.x)/this.getWidth())),
                (int)(this.getHeight()*((squareSize.y)/this.getHeight())), Image.SCALE_FAST);
    	Image newimg = new ImageIcon(scaledImage).getImage();
    	return newimg;
    }
    
    public Grid getGrid()
    {
        return grid;
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
			grid.resetSelectedSquare();
			changeActivePlayer();
		}
		else if (square == selectedSquare) {
			selectedSquare = null;
			grid.resetSelectedSquare();
		}
	}
	
	private void changeActivePlayer() 
	{
	    if (activePlayer.getColor() == Player.Color.WHITE) activePlayer = players[0];
	    else activePlayer = players[1];
	}
	
	public void resetGame()
	{
	    grid = new Grid(BOARD_SIZE,this);
	    activePlayer = players[1];
	    repaint();
	}
	

	@Override
	public void componentHidden(ComponentEvent e) {}
	@Override
	public void componentMoved(ComponentEvent e) {}
	@Override
	public void componentShown(ComponentEvent e) {}
}
