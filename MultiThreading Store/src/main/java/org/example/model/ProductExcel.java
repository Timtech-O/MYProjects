package org.example.model;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.IOException;

public class ProductExcel {
    Store store;
    private List<Product> products = new ArrayList<>();
    public ProductExcel(Store store){
        this.store = store;
    }
    public void Excel(String fileName) throws IOException {

            FileInputStream file = new FileInputStream(new File(fileName));
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            XSSFSheet sheet = workbook.getSheetAt(0);

            int rowNumber = sheet.getLastRowNum();
            int colNumber = sheet.getRow(1).getLastCellNum();

            for(int row = 1; row <= rowNumber; row++ ){
                String name = sheet.getRow(row).getCell(0).getStringCellValue();
                double price = sheet.getRow(row).getCell(1).getNumericCellValue();
                int unit = (int) sheet.getRow(row).getCell(2).getNumericCellValue();
                String category = sheet.getRow(row).getCell(3).getStringCellValue();
                //if (unit > 0){
                    store.setProductList(new Product(name, price, unit, category));
               // }
               // else{
                   // products.add(new Product(name, "OUT OF STOCK", category));
               // }

            }

           /* for (Row row : sheet1) {
                String name = row.getCell(0).getStringCellValue();
                double price = row.getCell(1).getNumericCellValue();
               // int unit = row.getCell(2).getStringCellValue();
                String category = row.getCell(3).getStringCellValue();


               // if (unit > 0) {
                    store.setProductList(new Product(name, price, 5, category));
                } /*else {
                    products.add(new Product(name, "OUT OF STOCK", category));
                }*/



            }

    }



