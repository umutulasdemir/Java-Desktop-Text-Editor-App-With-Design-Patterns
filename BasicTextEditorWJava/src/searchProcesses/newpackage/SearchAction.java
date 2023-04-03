
package searchProcesses.newpackage;

import javax.swing.JTextArea;
import searchProcesses.newpackage.searchActionCommand;

/**
 *
 * @author umutulasdemir
 */
public class SearchAction {
    
    
    // action without changing value.
    public static int[] action(searchActionCommand s, JTextArea text, String target, int index){
        return s.action(text, target, index);
    }
    // action with changing value.
    public static int[] action(searchActionCommand s, JTextArea text, String target, String newValue, int index){
        return s.action(text, target, newValue, index);
    }
}
