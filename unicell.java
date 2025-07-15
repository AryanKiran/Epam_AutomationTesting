package com.example.demo.entity;

import org.springframework.stereotype.Component;

@Component
public class unicell {

    private String label;
    private String objectType;
    private double x;
    private double y;
    private double height;
    private double width;
    private int unitableId;
    private boolean isHeader;
    private String content;
    private boolean isEditable;
    private styles style;

    public unicell() {
    }

    public unicell(String label, String objectType, double x, double y, double height, double width, int unitableId,
            boolean isHeader, String content, boolean isEditable, styles style) {
        this.label = label;
        this.objectType = objectType;
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
        this.unitableId = unitableId;
        this.isHeader = isHeader;
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

    public int getUnitableId() {
        return unitableId;
    }

    public void setUnitableId(int unitableId) {
        this.unitableId = unitableId;
    }

    public boolean isHeader() {
        return isHeader;
    }

    public void setHeader(boolean isHeader) {
        this.isHeader = isHeader;
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
        return "unicell [label=" + label + ", objectType=" + objectType + ", x=" + x + ", y=" + y + ", height=" + height
                + ", width=" + width + ", unitableId=" + unitableId + ", isHeader=" + isHeader + ", content=" + content
                + ", isEditable=" + isEditable + ", style=" + style + "]";
    }

    

    
}
