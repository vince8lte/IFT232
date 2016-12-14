package classes;

import java.awt.Image;
import java.awt.geom.Point2D;

import Piece.*;

public class Board {
   // private static final long serialVersionUID = 1L;

    private final int BOARD_SIZE = 8;
    private final int NOT_SELECTED = -1;

	
    private Image background;
    private Image scaledBackground;
    private Point2D.Double borderSize;
    private Point2D.Double squareSize;
    
    private Piece[][] echequier;		//Contient la position de toute les pieces de l'échiquier
    private int selectedX;				//Indique la position de la piece selectionner en X
    private int selectedY;				//Indique la position de la piece selectionner en Y
    
    private int phase;					//Indique la phase du board en cour
	
	public Board(){
	    //FlowLayout layout = (FlowLayout)this.getLayout();
        //layout.setVgap(0);
        
        echequier = new Piece[BOARD_SIZE][BOARD_SIZE];
        selectedX = NOT_SELECTED;
        selectedY = NOT_SELECTED;
        
        phase = 0;
        
        //this.borderSize = new Point2D.Double(this.getWidth()*0.0625, this.getHeight()*0.0625);
        //this.squareSize = new Point2D.Double((this.getWidth()-borderSize.x*2.0)/8.0, this.getHeight()-borderSize.y*2.0/8.0);
	}

	public int Action(int clickedX, int clickedY){
		return phase;
	}
	
	public void highlightPossibleMoves()
	{
		PiecePatterns[] patterns = this.getSelectedPiece().getPattern();
		int x, y;		//Contient le positionnement de la vérification
		Piece tempPiece;
		int nbrSaut;
		
		//boucle sur tout les parttern de la piece
		for ( int i =0 ; i<patterns.length ; i++)
		{
			x = selectedX;
			y = selectedY;
			nbrSaut = 0; 	//Compteur de saut effectuer	
			
			//Vérifie toutes les possibilitées de mouvement selon un patter
			do
			{
				x +=patterns[i].getDirectionX();
				y += patterns[i].getDirectionY();
				tempPiece = null;
				
				if(isInChess(x, y)){
					tempPiece = getPiece(x, y);
					if (tempPiece == null)
					{
						//highlighter la piece
					}
				}
				
				nbrSaut++;
				
			}while((tempPiece == null) && (nbrSaut<patterns[i].getDistanceMax()));
		}
	}
	
	
	//****** Partie privée du code ******************//
	
	private Piece getPiece(int x, int y){
		if(isInChess(x, y)){
			return echequier[x][y];
		}else{
			return null;
		}
	}
	
	private Piece getSelectedPiece(){
		if(pieceIsSelected()){
			return echequier[selectedX][selectedY];
		}else{
			return null;
		}
	}
	
	//S'assure que la position x et y fournis soient en tout temps dans les limites du board
	private boolean isInChess(int x, int y){
		
		if((x >= 0) && (x < BOARD_SIZE) && (y >= 0) && (y < BOARD_SIZE)){
			return true;
		}
		else{
			return false;
		}
	}
	
	private boolean squareIsEmpty(int x, int y){
		return (echequier[x][y] == null);
	}
	
	private boolean pieceIsSelected(){
		return ((selectedX != NOT_SELECTED) && (selectedY != NOT_SELECTED));
	}
	
	private void movePiece(int x, int y){
		echequier[x][y] = echequier[selectedX][selectedY];
		echequier[selectedX][selectedY] = null;
	}
	
	
	
	
	
	
	
	/*
	public void click(Square square) {
		if(selectedSquare != null) {
			movePiece(square);
		}
		else {
			selectSquare(square);
		}
	}*/
	
	/*
    @Override
    protected void paintComponent(Graphics g) {        
        super.paintComponent(g);
        g.drawImage(scaledBackground, 0, 0, this);
        grid.paintComponent(borderSize, squareSize, g);
    }*/

	/*
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
    }*/
/*
	private boolean selectSquare(Square square)
	{
		Piece selectedPiece = square.getPiece();
		if (selectedPiece == null) {
			return false;
		}
		if (activePlayer.isPieceOwner(selectedPiece)) {
			selectedSquare = square;
			square.isSelected(true);
			//selectedPiece.highlightPossibleMove();
			return true;
		}
		return false;
	}*/

	/*
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
	}*/
	/*
	public void resetGame()
	{
	    grid = new Grid(BOARD_SIZE,this);
	    activePlayer = players[1];
	    repaint();
	}*/
}
