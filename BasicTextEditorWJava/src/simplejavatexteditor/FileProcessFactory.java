
package simplejavatexteditor;

import java.awt.event.ActionEvent;
import javax.swing.JTextArea;

/**
 *
 * @author umutulasdemir
 */
public class FileProcessFactory {
    // Factory pattern.
    // Create file process with factory according to given process type.
    public static FileProcess makeProcess(String processType, Boolean edited, JTextArea text){
        FileProcess process = null;
        if(processType.equals("close")){
            process = new CloseProcess(edited,text);
        }
        else if(processType.equals("new")){
            process = new NewFileProcess(edited,text);
        }
        else if(processType.equals("open")){
            process = new OpenFileProcess(edited,text);
        }
        else if(processType.equals("save")){
            process = new SaveFileProcess(edited,text);
        }
        return process;
    }
}
