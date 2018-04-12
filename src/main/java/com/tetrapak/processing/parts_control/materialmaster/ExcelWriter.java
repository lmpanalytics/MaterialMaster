/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tetrapak.processing.parts_control.materialmaster;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author SEPALMM
 */
public class ExcelWriter {

    private static final String FILE_NAME = "MaterialMaster.xlsx";
    private static final Logger LOGGER = LoggerFactory.getLogger(ExcelWriter.class);

    /**
     * Writes an Excel file
     *
     * @param map to read from and write to file
     */
    public static void writeExcel(Map<String, Data> map) {

        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Material");

        LOGGER.info("Creating excel");

//        Write header
        String[] header = {"materialNumberNUM", "materialNumberBW", "materialNumberTP", "description", "mpg", "assortmentGroup", "pg", "familyName"};

        Row headerRow = sheet.createRow(0);
        for (int i = 0; i < header.length; i++) {
            String s = header[i];
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(s);
        }

//        Write table contents
        Data[] data = map.values().toArray(new Data[0]);

        for (int tableRow = 0; tableRow < data.length; tableRow++) {
            Data d = data[tableRow];
            Row row = sheet.createRow(tableRow + 1);
            for (int col = 0; col < 8; col++) {
                Cell cell = row.createCell(col);
                switch (col) {
                    case 0:
                        cell.setCellValue(d.getMaterialNumberNUM());
                        break;
                    case 1:
                        cell.setCellValue(d.getMaterialNumberBW());
                        break;
                    case 2:
                        cell.setCellValue(d.getMaterialNumberTP());
                        break;
                    case 3:
                        cell.setCellValue(d.getDescription());
                        break;
                    case 4:
                        cell.setCellValue(d.getMpg());
                        break;
                    case 5:
                        cell.setCellValue(d.getAssortmentGroup());
                        break;
                    case 6:
                        cell.setCellValue(d.getPg());
                        break;
                    case 7:
                        cell.setCellValue(d.getFamilyName());
                        break;
                    default:
                        break;
                }
            }
        }
        try {
            FileOutputStream outputStream = new FileOutputStream(FILE_NAME);
            workbook.write(outputStream);
            workbook.close();
        } catch (FileNotFoundException e) {
            LOGGER.error("FileNotFoundException {}.", e.getMessage());
        } catch (IOException e) {
            LOGGER.error("IOException {}.", e.getMessage());
        }

        LOGGER.info("Wrote {} rows to file {}.", map.size(), FILE_NAME);
    }
}
