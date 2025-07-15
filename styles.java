package com.example.demo.entity;
 
import org.apache.poi.sl.usermodel.PaintStyle;
import org.springframework.stereotype.Component;
 
import com.fasterxml.jackson.annotation.JsonProperty;
 
@Component
public class styles {
 
    private String fontColor;
    private String fontType;
    private Double fontSize;
    private String textAlignment;
    private String verticalAlignment;
    // @JsonProperty("isBold")
    private boolean isBold;
    // @JsonProperty("isItalic")
    private boolean isItalic;
    // @JsonProperty("isUnderline")
    private boolean isUnderline;
    // @JsonProperty("isRounded")
    private boolean isRounded;
    private String borderColor;
    private int borderWidth;
   
    public String getBackgroundColor() {
        return backgroundColor;
    }
 
    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
    }
 
    private String backgroundColor;
 
 
    public styles() {
    }
 
    public styles(String fontColor, String fontType, Double fontSize, String textAlignment, String verticalAlignment,
            boolean isBold, boolean isItalic, boolean isUnderline, boolean isRounded, String borderColor,
            int borderWidth) {
        this.fontColor = fontColor;
        this.fontType = fontType;
        this.fontSize = fontSize;
        this.textAlignment = textAlignment;
        this.verticalAlignment = verticalAlignment;
        this.isBold = isBold;
        this.isItalic = isItalic;
        this.isUnderline = isUnderline;
        this.isRounded = isRounded;
        this.borderColor = borderColor;
        this.borderWidth = borderWidth;
    }
 
    public String getFontColor() {
        return fontColor;
    }
 
    public void setFontColor(String fontColor) {
        this.fontColor = fontColor;
    }
 
    public String getFontType() {
        return fontType;
    }
 
    public void setFontType(String fontType) {
        this.fontType = fontType;
    }
 
    public Double getFontSize() {
        return fontSize;
    }
 
    public void setFontSize(Double fontSize) {
        this.fontSize = fontSize;
    }
 
    public String getTextAlignment() {
        return textAlignment;
    }
 
    public void setTextAlignment(String textAlignment) {
        this.textAlignment = textAlignment;
    }
 
    public String getVerticalAlignment() {
        return verticalAlignment;
    }
 
    public void setVerticalAlignment(String verticalAlignment) {
        this.verticalAlignment = verticalAlignment;
    }
 
    public boolean isBold() {
        return isBold;
    }
 
    public void setBold(boolean isBold) {
        this.isBold = isBold;
    }
 
    public boolean isItalic() {
        return isItalic;
    }
 
    public void setItalic(boolean isItalic) {
        this.isItalic = isItalic;
    }
 
    public boolean isUnderline() {
        return isUnderline;
    }
 
    public void setUnderline(boolean isUnderline) {
        this.isUnderline = isUnderline;
    }
 
    public boolean isRounded() {
        return isRounded;
    }
 
    public void setRounded(boolean isRounded) {
        this.isRounded = isRounded;
    }
 
    public String getBorderColor() {
        return borderColor;
    }
 
    public void setBorderColor(String borderColor) {
        this.borderColor = borderColor;
    }
 
    public int getBorderWidth() {
        return borderWidth;
    }
 
    public void setBorderWidth(int borderWidth) {
        this.borderWidth = borderWidth;
    }
 
    @Override
    public String toString() {
        return "styles [fontColor=" + fontColor + ", fontType=" + fontType + ", fontSize=" + fontSize
                + ", textAlignment=" + textAlignment + ", verticalAlignment=" + verticalAlignment + ", isBold=" + isBold
                + ", isItalic=" + isItalic + ", isUnderline=" + isUnderline + ", isRounded=" + isRounded
                + ", borderColor=" + borderColor + ", borderWidth=" + borderWidth + "]";
    }
 
   
 
   
   
}