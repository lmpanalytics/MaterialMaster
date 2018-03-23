/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tetrapak.processing.parts_control.materialmaster;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Reads data from Excel files
 *
 * INSTRUCTION: MAKE COPY PASTE OF SHEET ONE. SAVE AS NEW EXCELFILE IN WORKING
 * DIRECTORY.
 *
 * @author SEPALMM
 */
public class FileReader {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileReader.class);
    private static final String FILE_NAME_MATERIAL = "materials_raw.xlsx";
    private static final String FILE_NAME_GPL = "Pc_GPL_raw.xlsx";

    /**
     * Reads a material file and puts data into a map.
     *
     * @return map
     */
    public static Map<String, Data> readMaterialFile() {
        Map<String, Data> m = new HashMap<>();
        int rowCounter = 0;
        try {
            FileInputStream excelFile = new FileInputStream(new File(FILE_NAME_MATERIAL));
            Workbook workbook = new XSSFWorkbook(excelFile);
            Sheet datatypeSheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = datatypeSheet.iterator();

            while (rowIterator.hasNext()) {

                rowCounter++;

//                Initiate at each new row
                Row currentRow = rowIterator.next();
                Iterator<Cell> cellIterator = currentRow.iterator();
                int cellCounter = 0;

                String materialNumberNUM = "";
                String materialNumberBW = "";
                String materialNumberTP = "";
                String description = "";
                String mpg = "";
                String assortmentGroup = "";
                double pg = 0d;
                String refName = "";

                while (cellIterator.hasNext()) {

                    cellCounter++;

                    Cell currentCell = cellIterator.next();

                    if (currentCell.getCellTypeEnum() == CellType.STRING) {
                        String s = currentCell.getStringCellValue();
//                        System.out.print(s + " I'm a String ");
                        if (cellCounter == 1) {
                            materialNumberBW = s;
                        } else if (cellCounter == 2) {
                            description = s;
                        } else if (cellCounter == 3) {
                            mpg = s;
                        } else if (cellCounter == 4) {
                            assortmentGroup = s;
                        }
                    }
                }
                //   Put to map starting from row two in the Excel sheet
                if (rowCounter != Integer.MAX_VALUE
                        && rowCounter > 1
                        && Utility.isValidPartnumber(materialNumberBW)) {
                    m.put(materialNumberBW, new Data(materialNumberNUM, materialNumberBW, materialNumberTP, description, mpg, assortmentGroup, pg, refName));
                }
            }
            LOGGER.info("Read {} Material instances to map.", m.size());
        } catch (FileNotFoundException e) {
            LOGGER.error("Exception file not found {}", e.getMessage());
        } catch (IOException e) {
            LOGGER.error("Exception in IO {}", e.getMessage());
        }
        return m;
    }

    /**
     * Reads a GPL file and puts data into a map.
     *
     * @return map
     */
    public static Map<String, Data> readGPLfile() {
        Map<String, Data> m = new HashMap<>();
        int rowCounter = 0;
        try {
            FileInputStream excelFile = new FileInputStream(new File(FILE_NAME_GPL));
            Workbook workbook = new XSSFWorkbook(excelFile);
            Sheet datatypeSheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = datatypeSheet.iterator();

            while (rowIterator.hasNext()) {

                rowCounter++;

//                Initiate at each new row
                Row currentRow = rowIterator.next();
                Iterator<Cell> cellIterator = currentRow.iterator();
                int cellCounter = 0;

                String materialNumberNUM = "";
                String materialNumberBW = "";
                String materialNumberTP = "";
                String description = "";
                String mpg = "";
                String assortmentGroup = "";
                double pg = 0d;
                String refName = "";

                while (cellIterator.hasNext()) {

                    cellCounter++;

                    Cell currentCell = cellIterator.next();

                    if (currentCell.getCellTypeEnum() == CellType.STRING) {
                        String s = currentCell.getStringCellValue();
//                        System.out.print(s + " I'm a String ");
                        if (cellCounter == 2) {
                            materialNumberBW = s;
                        } else if (cellCounter == 3) {
                            materialNumberTP = s;
                        } else if (cellCounter == 4) {
                            description = s;
                        } else if (cellCounter == 5) {
                            mpg = s;
                        } else if (cellCounter == 6) {
                            assortmentGroup = s;
                        } else if (cellCounter == 8) {
                            refName = s;
                        }
                    } else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
                        Double d = currentCell.getNumericCellValue();
//                        System.out.print(d + " I'm a number ");
                        if (cellCounter == 1) {
                            long t = d.longValue();
                            materialNumberNUM = String.valueOf(t);
                        } else if (cellCounter == 7) {
                            pg = d.doubleValue();
                        }

                    }
                }
                //   Put to map starting from row two in the Excel sheet
                if (rowCounter != Integer.MAX_VALUE && rowCounter > 1) {
                    m.put(materialNumberBW, new Data(materialNumberNUM, materialNumberBW, materialNumberTP, description, mpg, assortmentGroup, pg, refName));
                }
            }
            LOGGER.info("Read {} GPL instances to map.", m.size());
        } catch (FileNotFoundException e) {
            LOGGER.error("Exception file not found {}", e.getMessage());
        } catch (IOException e) {
            LOGGER.error("Exception in IO {}", e.getMessage());
        }
        return m;
    }
}
