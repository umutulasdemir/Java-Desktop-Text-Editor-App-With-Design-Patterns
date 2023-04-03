package simplejavatexteditor;


import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

/**
 *
 * @author umutulasdemir
 */
public class NewFileProcess extends JFrame implements FileProcess{

    private boolean edited;
    private JTextArea text;

    public NewFileProcess(boolean edited, JTextArea text) {
        this.edited = edited;
        this.text = text;
    }
    
    @Override
    public boolean apply() { // new file
        if (edited) {
                Object[] options = {"Save", "No Save", "Return"};
                int alert = JOptionPane.showOptionDialog(this, "Do you want to save the file at first ?", "Question",
                        JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[2]);
                if (alert == 0) {// save
                    new SaveFileProcess(edited,text).apply();
                    edited = false;
                } else if (alert == 1) {
                    edited = false;
                    text.setText("");
                }
            } else {
                text.setText("");
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


