package Action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import classes.Board;

public class RestartAction extends BoardAction
{
	private final String DEFAULT_FILE = "";
	
    public RestartAction(Board board)
    {
    	super(board);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        this.board.LoadSaveFile(DEFAULT_FILE);
    }

}
