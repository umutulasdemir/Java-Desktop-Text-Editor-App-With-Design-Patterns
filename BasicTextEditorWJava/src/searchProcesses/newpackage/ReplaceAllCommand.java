
package searchProcesses.newpackage;

import javax.swing.JTextArea;

/**
 *
 * @author umutulasdemir
 */
public class ReplaceAllCommand implements searchActionCommand{

    // replace all command action override.
    @Override
    public int[] action(JTextArea text, String target, String newValue, int index) {
        text.setText(text.getText().replaceAll(target, newValue)); 
        return null;
    }
    
    @Override
    public int[] action(JTextArea text, String target, int index) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

  
}
