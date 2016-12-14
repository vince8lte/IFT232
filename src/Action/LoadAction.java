package Action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

import classes.Board;

public class LoadAction extends BoardAction
{
    private JFrame window;
    
    public LoadAction(JFrame window, Board board)
    {
    	super(board);
        this.window = window;
    }
    
    @Override
    public void actionPerformed(ActionEvent e)
    {
        JFileChooser c = new JFileChooser();
        String filename = "";
        String dir = "";
        
        int rVal = c.showOpenDialog(window);
        if (rVal == JFileChooser.APPROVE_OPTION) 
        {
          filename = c.getSelectedFile().getName();
          dir = c.getCurrentDirectory().toString();
          String path = dir+"\\"+filename;
          
          //Lire le contenu du fichier
          
        }     
    }
}
