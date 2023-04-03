
package searchProcesses.newpackage;

import javax.swing.JTextArea;

/**
 *
 * @author umutulasdemir
 */
public class FindNextCommand implements searchActionCommand{

    @Override
    public int[] action(JTextArea text, String target, String newValue, int index) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    // find next command override.
    @Override
    public int[] action(JTextArea text, String target, int index) {
        int select_start = -1;
        String selection = text.getSelectedText();
        try
        {
            selection.equals("");
        }
        catch(NullPointerException e)
        {}
        try
        {
            select_start = text.getText().indexOf(selection , index);
            int select_end = select_start+selection.length();
            text.select(select_start, select_end);
            index = select_end+1;

            if(select_start == text.getText().lastIndexOf(selection.toLowerCase()))
            {
                index = 0;
            }
        }
        catch(NullPointerException e)
        {}
        return (new int[]{index, select_start});
    }

}
