
package searchProcesses.newpackage;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;


/**
 *
 * @author umutulasdemir
 */
public class FindCommand implements searchActionCommand{

    @Override
    public int[] action(JTextArea text, String target, String newValue, int index) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    // find command override
    @Override
    public int[] action(JTextArea text, String target, int index) {
        int select_start = text.getText().indexOf(target.toLowerCase());
        if(select_start == -1)
        {
            index = 0;
            JOptionPane.showMessageDialog(null, "Could not find \"" + target + "\"!");
            return (new int[]{0,-1});
        }
        if(select_start == text.getText().lastIndexOf(target.toLowerCase()))
        {
            index = 0;
        }
        int select_end = select_start + target.length();
        text.select(select_start, select_end);
        return (new int[]{index, select_start});
    }

}
