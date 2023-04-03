
package searchProcesses.newpackage;

import javax.swing.JTextArea;

/**
 *
 * @author umutulasdemir
 */
public interface searchActionCommand {
    // command pattern
    // action to replace
    int[] action(JTextArea text,String target, String newValue, int index);
    // action to find
    int[] action(JTextArea text,String target, int index);
}
