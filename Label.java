package com.example.demo.entity;

public class Label {


    private String label;
    private String objectType;
    private double x;
    private double y;
    private double height;
    private double width;
    private String content;
    private boolean isEditable;
    private styles style;

    public Label() {
    }

    public Label(String label, String objectType, double x, double y, double height, double width, String content,
            boolean isEditable, styles style) {
        this.label = label;
        this.objectType = objectType;
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
        this.content = content;
        this.isEditable = isEditable;
        this.style = style;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getObjectType() {
        return objectType;
    }

    public void setObjectType(String objectType) {
        this.objectType = objectType;
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

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isEditable() {
        return isEditable;
    }

    public void setEditable(boolean isEditable) {
        this.isEditable = isEditable;
    }

    public styles getStyle() {
        return style;
    }

    public void setStyle(styles style) {
        this.style = style;
    }

    @Override
    public String toString() {
        return "Label [label=" + label + ", objectType=" + objectType + ", x=" + x + ", y=" + y + ", height=" + height
                + ", width=" + width + ", content=" + content + ", isEditable=" + isEditable + ", style=" + style + "]";
    }

    
    
    
}
