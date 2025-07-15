package com.example.demo.entity;

public class Textbox1 {

    private String label;
    private String content;
    private double x, y;
    private String backgroundColor;
    private boolean isHeader;
    private boolean isLabel;
    private int unitTableId = -1;
    private String objectType;
    private Double height;
    private double width;
    private String fontColor;
    private String fontType;
    private Double fontSize;
    private String textAlignment;
    private String verticalAlignment;
    private boolean isBold;
    private boolean isItalic;
    // @JsonProperty("isUnderline")    
    private boolean isUnderline;
    // @JsonProperty("isRounded")
    private boolean isRounded;
    private String borderColor;
    private int borderWidth;
    // private boolean header;
    private int slideNumber;
    

    public Textbox1(String label, String content, double x, double y, String backgroundColor) {
        this.label = label;
        this.content = content;
        this.x = x;
        this.y = y;
        this.backgroundColor = backgroundColor;
        this.isHeader = !backgroundColor.contains("255");
        this.isLabel = content.contains(":");
    }

    

    public String getObjectType() {
        return objectType;
    }



    public void setObjectType(String objectType) {
        this.objectType = objectType;
    }



    public Double getHeight() {
        return height;
    }



    public void setHeight(Double height) {
        this.height = height;
    }



    public double getWidth() {
        return width;
    }



    public void setWidth(double width) {
        this.width = width;
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



    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public String getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public boolean isHeader() {
        return isHeader;
    }

    public void setHeader(boolean isHeader) {
        this.isHeader = isHeader;
    }

    public boolean isLabel() {
        return isLabel;
    }

    public void setLabel(boolean isLabel) {
        this.isLabel = isLabel;
    }

    public int getUnitTableId() {
        return unitTableId;
    }

    public void setUnitTableId(int unitTableId) {
        this.unitTableId = unitTableId;
    }

    public int getSlideNumber() {
        return slideNumber;
    }
 
    public void setSlideNumber(int slideNumber) {
        this.slideNumber = slideNumber;
    }



    @Override
    public String toString() {
        return "Textbox1 [label=" + label + ", content=" + content + ", x=" + x + ", y=" + y + ", backgroundColor="
                + backgroundColor + ", isHeader=" + isHeader + ", isLabel=" + isLabel + ", unitTableId=" + unitTableId
                + ", objectType=" + objectType + ", height=" + height + ", width=" + width + ", fontColor=" + fontColor
                + ", fontType=" + fontType + ", fontSize=" + fontSize + ", textAlignment=" + textAlignment
                + ", verticalAlignment=" + verticalAlignment + ", isBold=" + isBold + ", isItalic=" + isItalic
                + ", isUnderline=" + isUnderline + ", isRounded=" + isRounded + ", borderColor=" + borderColor
                + ", borderWidth=" + borderWidth + ", slideNumber=" + slideNumber + "]";
    }

    // public void setHeader1(boolean header){
    //     this.header=header;
    // }

    // public boolean getHeader1(){
    //     return header;
    // }

    
    
}
