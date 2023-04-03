
package searchProcesses.newpackage;

import javax.swing.JTextArea;

/**
 *
 * @author umutulasdemir
 */
public class ReplaceCommand implements searchActionCommand {

    
    // replace command action override.
    @Override 
    public int[] action(JTextArea text, String target, String newValue, int index) {
         try
        {
            SearchAction.action(new FindCommand(), text, target, index); // if not find command started yet enter here first.
            if (index != -1)
            text.replaceSelection(newValue);
        }
        catch(NullPointerException e)
        {}
        return null; 
    }

    @Override
    public int[] action(JTextArea text, String target, int index) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    
}
