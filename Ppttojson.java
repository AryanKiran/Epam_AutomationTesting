package com.example.demo.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entity.textbox;
import com.example.demo.service.ComparisonService;
import com.example.demo.service.Data;
import com.example.demo.service.Extractdata;
import com.example.demo.service.Extractppt;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class Ppttojson {

      @Autowired 
      private Extractdata extractdata;

      @Autowired
      private  Data service;

      @Autowired
      private Extractppt extractppt;

       @Autowired
      private ComparisonService comparisonService;
     
     @PostMapping("/ppt/extract-data")
      public ResponseEntity extractShapeNamesAndText(@RequestParam("file") MultipartFile file) throws IOException {
           try {
            byte[] excelData = extractdata.getData(file);
            ByteArrayResource resource = new ByteArrayResource(excelData);
 
                     return  ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=textboxes.xlsx")
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(resource);
 
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    
     @PostMapping("/ppt/updated-excel")
    public ResponseEntity processExcel(@RequestParam("file") MultipartFile file) {
        try {
            byte[] data = service.processExcel(file);
            ByteArrayResource resource = new ByteArrayResource(data);
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=output_processed.xlsx")
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .contentLength(data.length)
                    .body(resource);
 
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

     

     @PostMapping("/ppt/to-json")
       public ResponseEntity convertExcelToJsonFile(@RequestParam("file") MultipartFile file) {
        try {

             java.io.File jsonFile=  extractppt.convertToJsonAndSaveFile(file);
            FileSystemResource resource = new FileSystemResource(jsonFile);
 
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + jsonFile.getName())
                    .contentType(MediaType.TEXT_PLAIN)
                    .body(resource);
 
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    
 
    @PostMapping("/ppt/comparison")
    public ResponseEntity compareFiles(
            @RequestParam("excelFile") MultipartFile excelFile,
            @RequestParam("jsonFile") MultipartFile jsonFile) {
        try {
            byte[] result = comparisonService.compareFiles(excelFile, jsonFile).toByteArray();
 
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=comparison_result.xlsx")
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(("Error: " + e.getMessage()).getBytes());
        }
    }
      
}
    
