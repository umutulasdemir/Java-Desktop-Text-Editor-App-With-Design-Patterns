

package searchProcesses.newpackage;

import java.awt.Color;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Find extends JFrame implements ActionListener {

    int[] currentIndexes = new int[]{0,-1}; // startIndex, selectedIndex
    
    JLabel findLabel, replaceLabel;
    JTextField findText, replaceText;
    JButton findButton, findeNextButton, replaceButton, replaceAllButton, cancelButton;
    private JTextArea textArea;

    public Find(JTextArea text) {
        this.textArea = text;

        // set labels and buttons
        findLabel = new JLabel("Find:");
        replaceLabel = new JLabel("Replace:");
        findText = new JTextField(30);
        replaceText = new JTextField(30);
        findButton = new JButton("Find");
        findeNextButton = new JButton("Find Next");
        replaceButton = new JButton("Replace");
        replaceAllButton = new JButton("Replace All");
        cancelButton = new JButton("Cancel");


        // create labels, buttons and their listeners
        setLayout(null);
        int labelWidth = 80;
        int labelHeight = 20;
        findLabel.setBounds(10,10, labelWidth, labelHeight);
        add(findLabel);
        findText.setBounds(10+labelWidth, 10, 120, 20);
        add(findText);
        replaceLabel.setBounds(10, 10+labelHeight+10, labelWidth, labelHeight);
        add(replaceLabel);
        replaceText.setBounds(10+labelWidth, 10+labelHeight+10, 120, 20);
        add(replaceText);
        findButton.setBounds(225, 6, 115, 20);
        add(findButton);
        findButton.addActionListener(this);
        findeNextButton.setBounds(225, 28, 115, 20);
        add(findeNextButton);
        findeNextButton.addActionListener(this);
        replaceButton.setBounds(225, 50, 115, 20);
        add(replaceButton);
        replaceButton.addActionListener(this);
        replaceAllButton.setBounds(225, 72, 115, 20);
        add(replaceAllButton);
        replaceAllButton.addActionListener(this);
        cancelButton.setBounds(225, 94, 115, 20);
        add(cancelButton);
        cancelButton.addActionListener(this);


        setSize(350,150);
        textArea.setSelectedTextColor(Color.MAGENTA);
        setLocationRelativeTo(textArea);
        setVisible(true); // make visible when opened
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // set default close
    }

    public void actionPerformed(ActionEvent e) {
        // command pattern.
        // run the command according to source of action event.
        if(e.getSource() == findButton)
        {
           currentIndexes = SearchAction.action(new FindCommand(), textArea, findText.getText(), currentIndexes[0]);      
        }
        else if(e.getSource() == findeNextButton)
        {
           currentIndexes = SearchAction.action(new FindNextCommand(), textArea, findText.getText(), currentIndexes[0]);  
        }
        else if(e.getSource() == replaceButton)
        {
            SearchAction.action(new ReplaceCommand(), textArea, findText.getText(), replaceText.getText(), currentIndexes[1]);
        }
        else if(e.getSource() == replaceAllButton)
        {
            SearchAction.action(new ReplaceAllCommand(),textArea, findText.getText(),replaceText.getText(), currentIndexes[0]);
        }
        else if(e.getSource() == cancelButton)
        {
           this.setVisible(false);
        }
   }

}