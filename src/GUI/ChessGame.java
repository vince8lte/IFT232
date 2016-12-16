package GUI;

import classes.Board;
import classes.Player;
import classes.Player.Color;

public class ChessGame {
    
    private Board board;
    private ChessGraphic graphic;

    private Player[] players; // Joueurs du jeu
    private int currentPlayerIndex; // Index du joueur qui joue actuellement
    
    public ChessGame(int playersCount) 
    {
        this.board = new Board();               
        this.players = new Player[playersCount]; 
        
        this.graphic = new ChessGraphic(this, board.getBoard());
    }
           
    // Change de tour du joueur
    private void changeActivePlayer(){
        currentPlayerIndex = ((currentPlayerIndex + 1) % players.length);
    }
    
    public void play(int x, int y){
        if (board.pieceIsSelected())
        {            
            Color teamColorPieceRecipient = board.getTeamColorFromPiece(x, y);
            
            // S'il n'existe pas une pièce destinataire OU 
            // Si la pièce présentement sélectionné et la pièce destinataire ne sont pas de la même équipe
            if ((teamColorPieceRecipient == Color.NONE) || 
                (teamColorPieceRecipient != board.getTeamColorFromSelectedPiece()))
            {
                // Si le déplacement de la pièce a fonctionné
                if (board.moveSelectedPieceTo(x, y, players[currentPlayerIndex].getColor()))
                {
                    changeActivePlayer();
                    graphic.paintGUI();
                }                
            }
            // Si la case sélectionné est la pièce présentement sélectionné
            else if (board.equalsPieceSelected(x, y))
                board.unselectPiece();
        }
        else
        {
            Color teamColorPieceRecipient = board.getTeamColorFromPiece(x, y);
            
            // Si la case sélectionné contient une pièce
            // et que cette pièce est de la même couleur que le joueur
            if (teamColorPieceRecipient == players[currentPlayerIndex].getColor())
            {
                board.selectPiece(x, y);                
                graphic.paintGUI();            
            }            
        }
    }
}