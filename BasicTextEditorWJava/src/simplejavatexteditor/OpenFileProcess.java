package simplejavatexteditor;

import java.io.File;
import java.io.FileReader;
import java.util.Scanner;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JTextArea;

/**
 *
 * @author umutulasdemir
 */
public class OpenFileProcess extends JFrame implements FileProcess{

    private boolean edited;
    private JTextArea text;

    public OpenFileProcess(boolean edited, JTextArea text) {
        this.edited = edited;
        this.text = text;
    }
    
    @Override
    public boolean apply() {
        JFileChooser open = new JFileChooser(); // open up a file chooser (a dialog for the user to  browse files to open)
            if( !(text.getText().equals("")) ) {
                edited = new SaveFileProcess(edited,text).apply();
            }
            
            // if user approve the file opening process, apply read process.
            int option = open.showOpenDialog(this); 

            if (option == JFileChooser.APPROVE_OPTION) {
                text.setText(""); // clear the text before applying the file contents
                try { // open and read the file.
                    File openFile = open.getSelectedFile();
                    Scanner scan = new Scanner(new FileReader(openFile.getPath()));
                    while (scan.hasNext()) {
                        text.append(scan.nextLine() + "\n");
                    }
                } catch (Exception ex) {}
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

