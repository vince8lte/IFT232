package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import classes.Piece;

public class ChessGame extends JPanel implements ComponentListener {
    private JFrame window;
    
    private Image background;
    private Image scaledBackground;
    private Image piece;
    private Image scaledPiece;
    
    private double squareSizeX;
    private double squareSizeY;
    private double borderSizeX;
    private double borderSizeY;
    
    private Piece[][] pieces;
    
    public ChessGame(int x, int y)
    {
        // Initialisation de la fenêtre
        window = new JFrame();
        window.setSize(x, y);
        window.setTitle("Jeu d'échec");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLocationRelativeTo(null);
        window.setResizable(true);
        window.getContentPane().add(this, BorderLayout.CENTER);
        window.setVisible(true);
        window.addComponentListener(this);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Logique pour sélectionner un pion et le déplacer à faire
                // On a accès aux variables privées de ChessGame ici
            }
        });
        
        // Initialisation du JPanel
        this.setPreferredSize(new Dimension(x, y));
        
        // Initialisation des pièces
        pieces = new Piece[8][8];
        
        for (int X = 0; X < pieces.length; ++X)
        {
            pieces[X][0] = new Piece("");
        }
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(scaledBackground, 0, 0, this);

        for (int x = 0; x < pieces.length; ++x)
        {
            for (int y = 0; y < pieces[x].length; ++y)
            {
                if (pieces[x][y] != null)
                    g.drawImage(scaledPiece, (int)(x*squareSizeX+borderSizeX), (int)(y*squareSizeY+borderSizeY), this);                     
            }
        }
    }

    @Override
    public void componentResized(ComponentEvent e)
    {
        borderSizeX = this.getWidth()*0.0625;
        borderSizeY = this.getHeight()*0.0625;
        squareSizeX = (this.getWidth()-borderSizeX*2.0)/8.0;
        squareSizeY = (this.getHeight()-borderSizeY*2.0)/8.0;
        
        if (background == null) {
            background = new ImageIcon("ressources/pictures/chessboard.jpg")
                    .getImage();
        }
        scaledBackground = background.getScaledInstance(this.getWidth(),
                this.getHeight(), Image.SCALE_FAST);
        
        if (scaledPiece == null) {
            piece = new ImageIcon("ressources/pictures/pionn.png").getImage();
        }
        scaledPiece = piece.getScaledInstance((int)(piece.getWidth(null)*((squareSizeX)/piece.getWidth(null))),
                (int)(piece.getHeight(null)*((squareSizeY)/piece.getHeight(null))), Image.SCALE_FAST);
    }

    @Override
    public void componentShown(ComponentEvent arg0){}
    @Override
    public void componentHidden(ComponentEvent e){}
    @Override
    public void componentMoved(ComponentEvent e){}
}
