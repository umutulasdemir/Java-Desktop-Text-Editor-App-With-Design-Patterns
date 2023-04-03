
package simplejavatexteditor;

import searchProcesses.newpackage.Find;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

public class TextEditorUI extends JFrame implements ActionListener {

    String[] fonts;
    ArrayList<String> textHistory = new ArrayList<String>();
    private final JTextArea textArea;
    private final JMenuBar menuBar;
    private final JComboBox<String> themeTypes;
    private final JComboBox<Integer> fontSize;
    private final JMenu fileMenu, editMenu, searchMenu;
    private final JMenuItem newFileMenuItem, openFileMenuItem, saveFileMenuItem,
            closeMenuItem,  clearFileMenuItem, findMenuItem;
    JButton randomThemeButton;
    private final JToolBar toolBar;

    // the icons.
    private final ImageIcon randomThemeIcon = new ImageIcon(TextEditorUI.class.getResource("icons/random.png"));
    private final ImageIcon newIcon = new ImageIcon(TextEditorUI.class.getResource("icons/new.png"));
    private final ImageIcon openIcon = new ImageIcon(TextEditorUI.class.getResource("icons/open.png"));
    private final ImageIcon saveIcon = new ImageIcon(TextEditorUI.class.getResource("icons/save.png"));
    private final ImageIcon closeIcon = new ImageIcon(TextEditorUI.class.getResource("icons/close.png"));
    private final ImageIcon clearIcon = new ImageIcon(TextEditorUI.class.getResource("icons/clear.png"));
    private final ImageIcon searchIcon = new ImageIcon(TextEditorUI.class.getResource("icons/search.png"));
    private final ImageIcon backIcon = new ImageIcon(TextEditorUI.class.getResource("icons/bold.png"));
    
    private boolean edit = false; // check if editing the text.
    private ArrayList<Theme> themes; // theme list to offer the user choices about themes.
    
    public TextEditorUI() {
        setSize(800, 500); // main page, initial size of program.
        setTitle("NotePad"); // set the title of program.
        setDefaultCloseOperation(EXIT_ON_CLOSE); // default exit.
        setLocationRelativeTo(null); // adjust centered for screen.

        // Set default font and size for text area.
        textArea = new JTextArea("", 0, 0);
        textArea.setFont(new Font("Century Gothic", Font.PLAIN, 12));
        textArea.setTabSize(2);

        // check if editing and make concurrent length display service.
        textArea.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                setTitle("NotePad" + "     [ Length: " + textArea.getText().length()
                        + "    Lines: " + (textArea.getText() + "|").split("\n").length
                        + "    Words: " + textArea.getText().trim().split("\\s+").length + " ]");
            }

            @Override
            public void keyPressed(KeyEvent ke) {
                edit = true;
            }
        });

        // adjust the text area with scrollpane.
        JScrollPane scrollPane = new JScrollPane(textArea);
        textArea.setWrapStyleWord(true);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        getContentPane().setLayout(new BorderLayout()); // the BorderLayout bit makes it fill it automatically
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(scrollPane);
        getContentPane().add(panel);

        // initalize the theme list.
        themes = new ArrayList<Theme>();
        
        // set the menus
        fileMenu = new JMenu("File");
        editMenu = new JMenu("Edit");
        searchMenu = new JMenu("Search");

        // set the menu items.
        newFileMenuItem = new JMenuItem("New", newIcon);
        openFileMenuItem = new JMenuItem("Open", openIcon);
        saveFileMenuItem = new JMenuItem("Save", saveIcon);
        closeMenuItem = new JMenuItem("Quit", closeIcon);
        clearFileMenuItem = new JMenuItem("Clear", clearIcon);
        findMenuItem = new JMenuItem("Quick", searchIcon);
        
        
        menuBar = new JMenuBar();
        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(searchMenu);

        this.setJMenuBar(menuBar);


        // Adding menu items to the menus.
        newFileMenuItem.addActionListener(this); 
        fileMenu.add(newFileMenuItem); 
        openFileMenuItem.addActionListener(this);
        fileMenu.add(openFileMenuItem);
        saveFileMenuItem.addActionListener(this);
        fileMenu.add(saveFileMenuItem);    
        closeMenuItem.addActionListener(this);
        fileMenu.add(closeMenuItem);
        clearFileMenuItem.addActionListener(this);
        editMenu.add(clearFileMenuItem);
        findMenuItem.addActionListener(this);
        searchMenu.add(findMenuItem);        

        toolBar = new JToolBar(); // set toolbar
        this.add(toolBar, BorderLayout.NORTH);
        themeTypes = new JComboBox<String>();

        // get all possible fonts.
        fonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();     
        createThemes(); // create themes.

        for (Theme theme : themes) {
            //Adding font family names to font[] array
            themeTypes.addItem(theme.getName());
        }
        
        //Setting maximize size of the fontType ComboBox
        themeTypes.setMaximumSize(new Dimension(170, 30));
        themeTypes.setToolTipText("Theme Type");
        toolBar.add(themeTypes);
        toolBar.addSeparator();
        themeTypes.addActionListener(this);
        fontSize = new JComboBox<Integer>();
        for (int i = 5; i <= 200; i++) {
            fontSize.addItem(i);
        }
        fontSize.setMaximumSize(new Dimension(70, 30));
        fontSize.setToolTipText("Font Size");
        toolBar.add(fontSize);
        fontSize.addActionListener(this);
        toolBar.addSeparator();
        randomThemeButton = new JButton(randomThemeIcon);
        randomThemeButton.setToolTipText("Random Theme");
        randomThemeButton.addActionListener(this);
        toolBar.add(randomThemeButton);

        // initalize with default theme.
        applyTheme(themes.get(0),false); 
        fontSize.setSelectedItem(themes.get(0).getFontSize());
    }

    @Override
    protected void processWindowEvent(WindowEvent e) {
        if (e.getID() == WindowEvent.WINDOW_CLOSING) {
            if (edit) {
                Object[] options = {"Save and exit", "No Save and exit", "Return"};
                int n = JOptionPane.showOptionDialog(this, "Do you want to save the file ?", "Question",
                        JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
                if (n == 0) {// save and exit
                    edit = new SaveFileProcess(edit,textArea).apply();
                    this.dispose();// dispose all resources and close the application
                } else if (n == 1) {// no save and exit
                    this.dispose();// dispose all resources and close the application
                }
            } else {
                System.exit(99); // close program.
            }
        }
    }
 
    // Builder Pattern
    // apply single theme with builder.
    public Theme applyTheme(Theme theme,boolean random){
        if(!random) theme.applyTheme(textArea);
        else{
            Random rand = new Random();
            theme =  ThemeBuilder.startBuild("random").
                    setBackgroundColor(new Color(rand.nextFloat(), rand.nextFloat(), rand.nextFloat())).
                    setTextColor(new Color(rand.nextFloat(), rand.nextFloat(), rand.nextFloat())).
                    setFontType(fonts[rand.nextInt(fonts.length)]).
                    setFontSize(rand.nextInt(50)+10).
                    setFontStyle(rand.nextInt(3)). // PLAIN, BOLD, ITALIC
                    build();   
            theme.applyTheme(textArea);
            fontSize.setSelectedItem(theme.getFontSize());
        }
        return theme;
    }
    
    public void createThemes(){        
        // Builder pattern.
        // Thanks to builder themes can be created very easily and clearly.
        Theme theme1 = ThemeBuilder.startBuild("Default Theme").
                setBackgroundColor(Color.WHITE)
                .setTextColor(Color.BLACK)
                .setFontType("Century Gothic")
                .setFontSize(12)
                .setFontStyle(0)//PLAIN
                .build();
        Theme theme2 = ThemeBuilder.startBuild("Custom-1").
                setBackgroundColor(Color.BLUE)
                .setTextColor(Color.YELLOW)
                .setFontType("Bodoni 72 Oldstyle")
                .setFontSize(25)
                .setFontStyle(1)//ITALIC
                .build();
        Theme theme3 = ThemeBuilder.startBuild("Custom-2").
                setBackgroundColor(Color.BLACK)
                .setTextColor(Color.WHITE)
                .setFontType("Rockwell")
                .setFontSize(20)
                .build();
        Theme theme4 = ThemeBuilder.startBuild("Custom-3").
                setBackgroundColor(Color.CYAN)
                .setTextColor(Color.ORANGE)
                .setFontType("Times New Roman")
                .setFontSize(38)
                .build();
        // add the themes just created
        themes.add(theme1);
        themes.add(theme2);
        themes.add(theme3);
        themes.add(theme4);
    }

    public void actionPerformed(ActionEvent e) {
        // If the source of the event was our "close" option
        String fileProcessType = "";
        if (e.getSource() == closeMenuItem) {
            fileProcessType = "close";
        } // If the source was the "new" file option
        else if (e.getSource() == newFileMenuItem) {
           fileProcessType = "new";
        } // If the source was the "open" option
        else if (e.getSource() == openFileMenuItem) {
            fileProcessType = "open";
        } // If the source of the event was the "save" option
        else if (e.getSource() == saveFileMenuItem) {
            fileProcessType = "save";
        }
        
        // Clear File (Code)
        else if (e.getSource() == clearFileMenuItem) {
            Object[] options = {"Yes", "No"};
            int n = JOptionPane.showOptionDialog(this, "Are you sure to clear the text Area ?", "Question",
                    JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
            if (n == 0) {// clear
                textArea.setText("");
            }
        }
        // Find
        else if (e.getSource() == findMenuItem) {
            new Find(textArea);
        } 
        else if(e.getSource() == themeTypes){
            //Getting the selected theme and apply changes.
            applyTheme(themes.get(themeTypes.getSelectedIndex()), false);
            fontSize.setSelectedItem(themes.get(themeTypes.getSelectedIndex()).getFontSize());
        }else if(e.getSource() == fontSize){
            int sizeOfFont = Integer.parseInt(fontSize.getSelectedItem().toString());
            Font font = textArea.getFont();                             
            textArea.setFont(new Font(font.getFamily(), font.getStyle(), sizeOfFont));
        }
        else if(e.getSource() == randomThemeButton){
            //Getting the selected theme and apply changes.
            applyTheme(null, true);
        }
        // check for file processes.
        if(!fileProcessType.equals("")){
            // Factory pattern.
            // Create the file process from file process factory.
            FileProcess fileProcess = FileProcessFactory.makeProcess(fileProcessType, edit, textArea);
            edit = fileProcess.apply();
        }
    }
}
