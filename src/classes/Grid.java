package classes;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Point2D;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import Piece.Cavalier;
import Piece.Fou;
import Piece.Piece;
import Piece.Pion;
import Piece.Reine;
import Piece.Roi;
import Piece.Tour;
import classes.Player.Color;

public class Grid
{
    private Square[][] squares;
    private Board board;
    
    public Grid(int size,Board board)
    {
        squares = new Square[size][size];
        this.board = board;
        this.importGrid("ressources/grid.txt");
        board.repaint();
    }
    
    public Square getSquare(int x, int y)
    {
        if (x<0 || x>7) return null;
        if (y<0 || y>7) return null;
        return squares[x][y];
    }
    
    public void paintComponent(Point2D.Double borderSize, Point2D.Double squareSize,Graphics g)
    {
        for (int i = 0; i < squares.length; ++i) {
            for ( int j = 0; j < squares[i].length; ++j) {
                Rectangle rec = new Rectangle();
                rec.setRect(borderSize.x + squareSize.x*i, borderSize.y + squareSize.y*j, squareSize.x, squareSize.y);
                squares[i][j].paintComponent(rec, g);
            }
        }
    }
    
    public void resetSelectedSquare() {
        for (int i = 0; i < squares.length; ++i) {
            for (int j = 0; j < squares[i].length; ++j) {
                squares[i][j].isHighlighted(false);
                squares[i][j].isSelected(false);
            }
        }
    }
    
    public String exportGrid()
    {
        String text = "";
        text += squares.length+"\r\n";
        for (int i = 0; i < squares.length; ++i) {
            for (int j = 0; j < squares[i].length; ++j) {
                Square square = this.getSquare(i, j);
                text += i +","+j+",";
                if (!square.isEmpty())
                {
                    text+=square.getPiece().exportPiece();
                }
                text += "\r\n";
            }
        }
        return text;
    }
    
    public void importGrid(String filename)
    {
        BufferedReader br = null;
        String line = "";
        int size = 0;

        try 
        {

            br = new BufferedReader(new FileReader(filename));
            size = Integer.parseInt(br.readLine());
            
            // initialise tous les squares
            squares = new Square[size][size];
            for(int i = 0; i < squares.length; ++i) {
                for (int j = 0; j < squares[i].length; ++j) {
                    Square square = new Square(new Point(i, j), board);
                    squares[i][j] = square;
                }
            }
            
            //ajouter les pieces dans la grid
            while ((line = br.readLine()) != null) 
            {
                String[] data = line.split(",");
                if (data.length >= 4){
                    int x = Integer.parseInt(data[0]);
                    int y = Integer.parseInt(data[1]);
                    String classname = data[2];
                    String color = data [3];
                    
                    Piece piece = PieceFactory.getInstance().create(PieceFactory.PieceType.valueOf(classname), Player.Color.valueOf(color), board);
                    
                    squares[x][y].setPiece(piece);
                }          
            }

        } 
        catch (FileNotFoundException e) 
        {
            e.printStackTrace();
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        } 
    }
}
