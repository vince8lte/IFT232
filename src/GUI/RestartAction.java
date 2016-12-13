package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import classes.Board;

public class RestartAction implements ActionListener
{
    Board board;
    public RestartAction(Board board)
    {
        this.board = board;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        this.board.resetGame();
    }

}
