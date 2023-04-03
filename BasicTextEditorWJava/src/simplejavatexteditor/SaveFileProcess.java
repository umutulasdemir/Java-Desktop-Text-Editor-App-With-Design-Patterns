package simplejavatexteditor;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JTextArea;

/**
 *
 * @author umutulasdemir
 */
public class SaveFileProcess extends JFrame implements FileProcess{

    private boolean edited;
    private JTextArea text;

    public SaveFileProcess(boolean edited, JTextArea text) {
        this.edited = edited;
        this.text = text;
    }
    
    @Override
    public boolean apply() {
        JFileChooser fileChoose = new JFileChooser(); // choose the file
        int option = fileChoose.showSaveDialog(this); // with approve

        if (option == JFileChooser.APPROVE_OPTION) { // apply if approve
            try {
                File openFile = fileChoose.getSelectedFile();
                BufferedWriter out = new BufferedWriter(new FileWriter(openFile.getPath()));
                out.write(text.getText());
                out.close();
                edited = false; // set default edited when saved.
            } catch (Exception ex) { // again, catch any exceptions and...
                System.err.println(ex.getMessage());
            }
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


