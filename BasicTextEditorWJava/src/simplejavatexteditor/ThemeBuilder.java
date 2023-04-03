
package simplejavatexteditor;


import java.awt.Color;

/**
 *
 * @author umutulasdemir
 */
public class ThemeBuilder {
    
    // Builder pattern

    private String name;
    private Color backgroundColor;
    private Color textColor;
    private String fontType;
    private int fontSize;
    private int fontStyle;

    public static ThemeBuilder startBuild(String name){
        ThemeBuilder builder = new ThemeBuilder();
        builder.name = name;
        return builder;
    }
    public static ThemeBuilder startBuild(Color backgroundColor, Color textColor) {
        ThemeBuilder builder = new ThemeBuilder();
        builder.backgroundColor = backgroundColor;
        builder.textColor = textColor;
        return builder;
    }
    public Theme build(){
        Theme theme = new Theme();
        theme.setName(name);
        theme.setBackgroundColor(backgroundColor);
        theme.setTextColor(textColor);
        theme.setFontType(fontType);
        theme.setFontSize(fontSize);
        theme.setFontStyle(fontStyle);
        return theme;
    }
    public ThemeBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public ThemeBuilder setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
        return this;
    }

    public ThemeBuilder setTextColor(Color textColor) {
        this.textColor = textColor;
        return this;
    } 

    public ThemeBuilder setFontType(String fontType) {
        this.fontType = fontType;
        return this;
    }

  
    public ThemeBuilder setFontSize(int fontSize) {
        this.fontSize = fontSize;
        return this;
    }

    public ThemeBuilder setFontStyle(int fontStyle) {
        this.fontStyle = fontStyle;
        return this;
    }

    @Override
    public String toString() {
        return "Theme{" + "backgroundColor=" + backgroundColor + ", textColor=" + textColor + ", fontType=" + fontType + ", fontSize=" + fontSize + ", fontStyle=" + fontStyle + '}';
    } 
}

