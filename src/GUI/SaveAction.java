package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import classes.Board;


public class SaveAction implements ActionListener
{
    private Board board;
    private JFrame window;
    
    public SaveAction(JFrame window, Board board)
    {
       this.window = window;
       this.board = board;
    }
    
    @Override
    public void actionPerformed(ActionEvent e)
    {
        JFileChooser c = new JFileChooser();
        String filename = "";
        String dir = "";
        
        int rVal = c.showSaveDialog(window);
        if (rVal == JFileChooser.APPROVE_OPTION) 
        {
          filename = c.getSelectedFile().getName();
          dir = c.getCurrentDirectory().toString();     
          try
          {
              String path = dir+"\\"+filename;
              PrintWriter writer = new PrintWriter(path, "UTF-8");
              writer.print(board.getGrid().exportGrid());
              writer.close();
          } 
          catch (IOException exception) 
          {
             // do something
          }
        }        
    }

}
