
package simplejavatexteditor;

import javax.swing.JTextArea;

/**
 *
 * @author umutulasdemir
 */
public interface FileProcess {
    // Factory pattern.
    boolean apply(); // Implement the method and apply the process according to file process type.
    boolean getEdited(); // according to file process check edit status and ask to save when closing file.
    JTextArea text(); // share the text of file among process to apply with it.
}
