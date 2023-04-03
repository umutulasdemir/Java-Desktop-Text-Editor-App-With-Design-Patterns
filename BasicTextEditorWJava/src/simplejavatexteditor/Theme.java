
package simplejavatexteditor;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextArea;
/**
 *
 * @author umutulasdemir
 */
public class Theme {
    private String name;
    private Color backgroundColor;
    private Color textColor;
    private String fontType;
    private int fontSize;
    private int fontStyle;

    public Theme(){
        
    }

    public Theme(String name, Color backgroundColor, Color textColor, String fontType, int fontSize, int fontStyle) {
        this.name = name;
        this.backgroundColor = backgroundColor;
        this.textColor = textColor;
        this.fontType = fontType;
        this.fontSize = fontSize;
        this.fontStyle = fontStyle;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Color getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public Color getTextColor() {
        return textColor;
    }

    public void setTextColor(Color textColor) {
        this.textColor = textColor;
    }

    public String getFontType() {
        return fontType;
    }

    public void setFontType(String fontType) {
        this.fontType = fontType;
    }

    public int getFontSize() {
        return fontSize;
    }

    public void setFontSize(int fontSize) {
        this.fontSize = fontSize;
    }

    public int getFontStyle() {
        return fontStyle;
    }

    public void setFontStyle(int fontStyle) {
        this.fontStyle = fontStyle;
    }

    @Override
    public String toString() {
        return "Theme{" + "backgroundColor=" + backgroundColor + ", textColor=" + textColor + ", fontType=" + fontType + ", fontSize=" + fontSize + ", fontStyle=" + fontStyle + '}';
    } 
    public void applyTheme(JTextArea text){
        text.setBackground(this.getBackgroundColor());
        text.setForeground(this.getTextColor());
        text.setFont(new Font(this.getFontType(), this.getFontStyle(), this.getFontSize()));
    }
}
