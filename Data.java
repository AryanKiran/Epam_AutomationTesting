package com.example.demo.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.example.demo.entity.Style;
import com.example.demo.entity.Textbox;

@Service
public class Data {

    @Autowired
    private Extractdata extractdata;

    public byte[] processExcel(MultipartFile file) throws IOException {
        Workbook workbook = new XSSFWorkbook(file.getInputStream());
        Sheet sheet = workbook.getSheetAt(0);
          List<Textbox> textBoxes = new ArrayList<>();
        // List<Textbox1> textboxes1=new ArrayList<>();
        Iterator rowIterator = sheet.iterator();
        Row headerRow = (Row) rowIterator.next();

        Map<String, Integer> colMap = new HashMap<>();
        for (Cell cell : headerRow) {
            colMap.put(cell.getStringCellValue(), cell.getColumnIndex());
        }

        while (rowIterator.hasNext()) {
            Row row = (Row) rowIterator.next();

            String label = row.getCell(colMap.get("Label")).getStringCellValue();
            String content = row.getCell(colMap.get("Content")).getStringCellValue();
            double x = row.getCell(colMap.get("X")).getNumericCellValue();
            double y = row.getCell(colMap.get("Y")).getNumericCellValue();
            String bgColor = row.getCell(colMap.get("Background Color")).getStringCellValue();
            String objectType = row.getCell(colMap.get("Object Type")).getStringCellValue();
            Double height = row.getCell(colMap.get("Height")).getNumericCellValue();
            double width = row.getCell(colMap.get("Width")).getNumericCellValue();
            String fontColor = row.getCell(colMap.get("Font Color")).getStringCellValue();
            String fontType = row.getCell(colMap.get("Font Type")).getStringCellValue();
            Double fontSize = row.getCell(colMap.get("Font Size")).getNumericCellValue();
            String textAlignment = row.getCell(colMap.get("Text Alignment")).getStringCellValue();
            String verticalAlignment = row.getCell(colMap.get("Vertical Alignment")).getStringCellValue();
            boolean isBold = row.getCell(colMap.get("Is Bold")).getBooleanCellValue();
            boolean isItalic = row.getCell(colMap.get("Is Italic")).getBooleanCellValue();
            boolean isUnderline = row.getCell(colMap.get("Is Underline")).getBooleanCellValue();
            boolean isRounded = row.getCell(colMap.get("Is Rounded")).getBooleanCellValue();
            boolean isHeader = row.getCell(colMap.get("Is Header")).getBooleanCellValue();
            String borderColor = row.getCell(colMap.get("Border Color")).getStringCellValue();
            int borderWidth = (int) row.getCell(colMap.get("Border Width")).getNumericCellValue();
            int slide = (int) row.getCell(colMap.get("Slide Number")).getNumericCellValue();

            // textbox textbox = new Textbox1(label, content, x, y, bgColor);
            Textbox box=new Textbox();
            Style st=new Style();
            box.setLabel(label);
            box.setObjectType(objectType);
            box.setContent(content);
            box.setX(x);
            box.setY(y);
            box.setHeight(height);
            box.setWidth(width);
            st.setFontColor(fontColor);
            st.setFontType(fontType);
            st.setFontSize(fontSize);
            st.setTextAlignment(textAlignment);
            st.setVerticalAlignment(verticalAlignment);
            st.setBold(isBold);
            st.setItalic(isItalic);
            st.setUnderline(isUnderline);
            st.setRounded(isRounded);
            st.setBorderColor(borderColor);
            st.setBorderWidth(borderWidth);
            st.setBackgroundColor(bgColor);
            box.setSlideNumber(slide);
            box.setHeader(isHeader);
            box.setStyle(st);;
            if (!textBoxes.contains(box)) {
                textBoxes.add(box);
            }
        }

    
            int unit=0;
           for(Textbox tb:textBoxes){
            if(tb.isHeader()){
                unit++;
                tb.setUnitTableId(unit);
                if(tb.getUnitTableId()!=1){
                tb.setObjectType("unicell");
                }
                double yData=tb.getY();
                double xData=tb.getX();
                for(Textbox tb1:textBoxes){
                    if(tb1.getY()==yData){
                        tb1.setUnitTableId(unit);
                        tb1.setObjectType("unicell");
                    }
                    else if(Math.abs(tb1.getX()-xData)<1 && tb1.getY()>yData  && Math.abs(tb1.getY()-yData)<35){
                        double getY=tb1.getY();
                        tb1.setUnitTableId(unit);
                        for(Textbox tb2:textBoxes){
                           if(Math.abs(tb2.getY()-getY)<1){
                             tb2.setUnitTableId(unit);
                             tb2.setObjectType("unicell");
                           }
                        }
                    }
                }
                
            }
            else if(tb.getStyle().getBorderWidth()==0){
                unit++;
                tb.setUnitTableId(unit);
                tb.setObjectType("LabelBox");
                double xData=tb.getX();
                double yData=tb.getY();
                double check=30.00;
                for(Textbox tb1:textBoxes){
                    if(Math.abs(xData-tb1.getX())<4 && tb1.getY() > yData &&(tb1.getY()-yData)<check){
                        tb1.setUnitTableId(unit);
                    }
                    else if(tb1.getX()==xData && tb1.getY()>yData && tb1.getY()-yData<23 ){
                     tb1.setUnitTableId(unit);
                   }
                }
                
        
                
            }
        }
          
       

        // Write to new Excel
        Workbook outWorkbook = new XSSFWorkbook();
        Sheet outSheet = outWorkbook.createSheet("Processed");

        String[] headers = {
            "Label", "Content", "X", "Y", "isHeader", "isEditable", "unitTableId",
            "Object Type", "Height", "Width", "Slide Number", "Font Color", "Font Type", "Font Size",
            "Text Alignment", "Vertical Alignment",  "Border Color", "Border Width", "Background Color",
            "Is Underline","Is Bold","Is Rounded", "Is Italic"
            
        };

        Row outHeader = outSheet.createRow(0);
        for (int i = 0; i < headers.length; i++) {
            outHeader.createCell(i).setCellValue(headers[i]);
        }

        int rowNum = 1;
        for (Textbox tb1 : textBoxes) {
            Row row = outSheet.createRow(rowNum++);
            row.createCell(0).setCellValue(tb1.getLabel());
            row.createCell(1).setCellValue(tb1.getContent());
            row.createCell(2).setCellValue(tb1.getX());
            row.createCell(3).setCellValue(tb1.getY());
            row.createCell(4).setCellValue(tb1.isHeader());
            if(tb1.isHeader()==true || tb1.getStyle().getBorderWidth()==0){
                row.createCell(5).setCellValue(false);
            }else{
                row.createCell(5).setCellValue(true);
            }
            row.createCell(6).setCellValue(tb1.getUnitTableId() != 0 ? tb1.getUnitTableId() : -1);
            row.createCell(7).setCellValue(tb1.getObjectType());
            row.createCell(8).setCellValue( tb1.getHeight() );
            row.createCell(9).setCellValue(tb1.getWidth());
            row.createCell(10).setCellValue(tb1.getSlideNumber());
            row.createCell(11).setCellValue(tb1.getStyle().getFontColor());
            row.createCell(12).setCellValue(tb1.getStyle().getFontType());
            row.createCell(13).setCellValue(tb1.getStyle().getFontSize() != null ? tb1.getStyle().getFontSize() : 0);
            row.createCell(14).setCellValue(tb1.getStyle().getTextAlignment());
            row.createCell(15).setCellValue(tb1.getStyle().getVerticalAlignment());
            row.createCell(16).setCellValue(tb1.getStyle().getBorderColor());
             row.createCell(17).setCellValue(tb1.getStyle().getBorderWidth());
             row.createCell(18).setCellValue(tb1.getStyle().getBackgroundColor());
             row.createCell(19).setCellValue(tb1.getStyle().isUnderline());
            row.createCell(20).setCellValue(tb1.getStyle().isBold());
            row.createCell(21).setCellValue(tb1.getStyle().isRounded());
            row.createCell(22).setCellValue(tb1.getStyle().isItalic());
        }

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        outWorkbook.write(out);
        outWorkbook.close();
        workbook.close(); // Close the input workbook as well
        return out.toByteArray();
    }
}
