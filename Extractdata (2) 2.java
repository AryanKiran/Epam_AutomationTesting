package com.example.demo.service;
 
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.sl.usermodel.ShapeType;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
 
import java.awt.Color;
import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xslf.usermodel.XSLFAutoShape;
import org.apache.poi.xslf.usermodel.XSLFShape;
import org.apache.poi.xslf.usermodel.XSLFSimpleShape;
import org.apache.poi.xslf.usermodel.XSLFSlide;
import org.apache.poi.xslf.usermodel.XSLFTextParagraph;
import org.apache.poi.xslf.usermodel.XSLFTextRun;
import org.apache.poi.xslf.usermodel.XSLFTextShape;
import org.apache.poi.xssf.model.Styles;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.example.demo.entity.Label;
import com.example.demo.entity.styles;
import com.example.demo.entity.textbox;
import com.example.demo.entity.unicell;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
 
@Service
public class Extractdata {
     
    int pptSize=0;
    public byte[]  getData(MultipartFile file) throws IOException {
        List<textbox> listData=new ArrayList<>();
        List<unicell> listCell=new ArrayList<>();
        List<Label> labelData=new ArrayList<>();
        List allData=new ArrayList<>();
        List finalData=new ArrayList<>();
         try (InputStream inputStream = file.getInputStream();
             XMLSlideShow ppt = new XMLSlideShow(inputStream)) {
                int i=0;
            for (XSLFSlide slide : ppt.getSlides()) {
                    i++;
                for (XSLFShape shape : slide.getShapes()) {
                     unicell cell=new unicell();
                     styles styless=new styles();
                     textbox box=new textbox();
                     Label label=new Label();
                     if (!(shape instanceof XSLFTextShape textShape)) continue;
                    for (XSLFTextParagraph para : textShape.getTextParagraphs()) {
                        for (XSLFTextRun run : para.getTextRuns()) {
                            String shapeName = shape.getShapeName();
                            String shapeText = shape.getShapeName();
                            styless.setFontType(run.getFontFamily());
                            styless.setFontSize(run.getFontSize());
                            styless.setFontColor(Extractdata.getFontColor(textShape));
                            styless.setBold(run.isBold());
                            styless.setItalic(run.isItalic());
                            styless.setUnderline(run.isUnderlined());
  
                            if (para.getTextAlign() != null){
                                styless.setTextAlignment(para.getTextAlign().name());
                            }
                            else{
                                styless.setTextAlignment("LEFT");
                            }
                            if (textShape.getVerticalAlignment() != null){
                                styless.setVerticalAlignment(textShape.getVerticalAlignment().name());
                            }
                            else{
                                styless.setVerticalAlignment("TOP");
                            }
                            boolean isRounded = shape.getClass().getSimpleName().toLowerCase().contains("round") ||
                                    (shape instanceof XSLFAutoShape auto && auto.getShapeType() == ShapeType.ROUND_RECT);
                         
                            styless.setRounded(isRounded);
 
                            if (shape instanceof XSLFSimpleShape simpleShape){
                                int bd=(int)simpleShape.getLineWidth();
                                styless.setBorderWidth(bd);
                            }
                            else{
                                styless.setBorderWidth(0);
                            }
                           Color borderColor= textShape.getLineColor();
                           if (borderColor != null) {
                                    int r = borderColor.getRed();
                                    int g = borderColor.getGreen();
                                    int b = borderColor.getBlue();
                                    System.out.println("Border color RGB: " + r + ", " + g + ", " + b);
                                    styless.setBorderColor(r + ", " + g + ", " + b);
                                } else {
                                    styless.setBorderColor(null);
                                    System.out.println("No border color set.");
                                }
 
                                Color fillColor = textShape.getFillColor();
                                if (fillColor != null) {
                                    int red = fillColor.getRed();
                                    int green = fillColor.getGreen();
                                    int blue = fillColor.getBlue();
                                    String bgColorRGB = String.format("rgb(%d, %d, %d)", red, green, blue);
                                    styless.setBackgroundColor(bgColorRGB);
                                    System.out.println("Background color RGB: " + bgColorRGB);
                                } else {
                                    styless.setBackgroundColor("rgb(255, 255, 255)");
                                    System.out.println("No background color set.");
                                }
 
 
                                 box.setSlideNumber(i);
                                 box.setLabel(shapeName);
                                 box.setObjectType("textbox");
                                box.setContent(((org.apache.poi.xslf.usermodel.XSLFTextShape) shape).getText());
                                double x1 = shape.getAnchor().getX();
                                double y1 = shape.getAnchor().getY();
                                box.setX(x1);
                                box.setY(y1);
                                box.setHeight(shape.getAnchor().getHeight());
                                box.setWidth(shape.getAnchor().getWidth());
                                box.setEditable(!"rgb(255, 255, 255)".equals(styless.getBackgroundColor()) && styless.getBackgroundColor() != null ? false : true);
 
                                box.setStyle(styless);
                                if(!listData.contains(box)){
                                   listData.add(box);
                                }
                                    
                }
                   }
   
               }
           }
        }
        


          Workbook workbook = new XSSFWorkbook();
        Sheet sheet =  workbook.createSheet("Textboxes");
 
        String[] headers = {
            "Label", "Object Type", "X", "Y", "Height", "Width", "Content", "Is Editable",
            "Font Color", "Font Type", "Font Size", "Text Alignment", "Vertical Alignment",
            "Is Bold", "Is Italic", "Is Underline", "Is Rounded", "Border Color", "Border Width", "Background Color", "Is Header","Slide Number"
        };
 
        Row headerRow =  sheet.createRow(0);
        for (int i = 0; i < headers.length; i++) {
            headerRow.createCell(i).setCellValue(headers[i]);
        }
 
        int rowNum = 1;
        int i=0;
        for (textbox tb : listData) {
            Row row = sheet.createRow(rowNum++);
            styles style = tb.getStyle();

 
            row.createCell(0).setCellValue(tb.getLabel());
            row.createCell(1).setCellValue(tb.getObjectType());
            row.createCell(2).setCellValue(tb.getX());
            row.createCell(3).setCellValue(tb.getY());
            row.createCell(4).setCellValue(tb.getHeight());
            row.createCell(5).setCellValue(tb.getWidth());
            row.createCell(6).setCellValue(tb.getContent());
            row.createCell(7).setCellValue(tb.isEditable());
            
            row.createCell(8).setCellValue(style.getFontColor());
            row.createCell(9).setCellValue(style.getFontType());
            row.createCell(10).setCellValue(style.getFontSize());
            row.createCell(11).setCellValue(style.getTextAlignment());
            row.createCell(12).setCellValue(style.getVerticalAlignment());
            row.createCell(13).setCellValue(style.isBold());
            row.createCell(14).setCellValue(style.isItalic());
            row.createCell(15).setCellValue(style.isUnderline());
            row.createCell(16).setCellValue(style.isRounded());
            row.createCell(17).setCellValue(style.getBorderColor());
            row.createCell(18).setCellValue(style.getBorderWidth());
            row.createCell(19).setCellValue(style.getBackgroundColor());
            row.createCell(20).setCellValue("rgb(255, 255, 255)".equals(style.getBackgroundColor()) || style.getBackgroundColor() == null ? false : true);
            row.createCell(21).setCellValue(tb.getSlideNumber());
        }
 
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        workbook.write(out);
        workbook.close();
         System.out.println(listData.size());
           return out.toByteArray();
    }
 
    private static String getFontColor(XSLFTextShape shape) {
        try {
            var paint = shape.getTextParagraphs().get(0).getTextRuns().get(0).getFontColor();
            Color color = null;
            if (paint instanceof org.apache.poi.sl.usermodel.PaintStyle.SolidPaint solidPaint) {
                color = org.apache.poi.sl.draw.DrawPaint.applyColorTransform(solidPaint.getSolidColor());
            }
            return color != null ? "rgb(" + color.getRed() + "," + color.getGreen() + "," + color.getBlue() + ")" : "rgb(0,0,0)";
        } catch (Exception e) {
            return "rgb(0,0,0)";
        }
    }        
           
 
}
 