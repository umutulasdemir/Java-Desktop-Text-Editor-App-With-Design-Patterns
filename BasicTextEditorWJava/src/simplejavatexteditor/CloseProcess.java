
package simplejavatexteditor;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

/**
 *
 * @author umutulasdemir
 */
public class CloseProcess extends JFrame implements FileProcess{

    private boolean edited;
    private JTextArea text;

    public CloseProcess(boolean edited, JTextArea text) {
        this.edited = edited;
        this.text = text;
    }
    
    @Override
    public boolean apply() { // close file
        if (getEdited()) {
                Object[] options = {"Save and exit", "No Save and exit", "Return"};
                int n = JOptionPane.showOptionDialog(this, "Do you want to save the file ?", "Question",
                        JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[2]);
                if (n == 0) { // save and exit
                    System.out.println("burası");
                    edited = new SaveFileProcess(edited,text).apply();
                    System.exit(99);
                } else if (n == 1) {// exit without saving
                    System.exit(99);
                }
            } else {
                this.dispose();
            }
        return edited;
    }

    @Override
    public boolean getEdited() {
        return edited;
    }

  
    @Override
    public JTextArea text() {
        return text;
    }   
}
