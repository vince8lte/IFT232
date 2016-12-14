package Action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import classes.Board;

public abstract class BoardAction implements ActionListener {
	
	protected Board board;

	@Override
	public abstract void actionPerformed(ActionEvent e);
	
	public BoardAction(Board board){
		this.board = board;
	}

}
