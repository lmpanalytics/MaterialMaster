/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tetrapak.processing.parts_control.materialmaster;

import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Merge the three data sources of GPL, BO material orders, and Part families
 * from NPP into one excel file.
 *
 * @author SEPALMM
 */
public class Main implements Runnable {

    private static Map<String, Data> globalMap;
    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);
    private static Map<String, Data> gplMap;
    private static Map<String, Data> mtrlMap;
    private static Map<String, String> nppMap;

    private static Map<String, Data> mergeMaps(
            Map<String, Data> mtrlMap, Map<String, Data> gplMap) {

//        Merge the maps
        mtrlMap.forEach((k, v) -> gplMap.putIfAbsent(k, v));
//        gplMap now has data from mtrlMap

//        Update missing material numbers and family names in gplMap
        updateMissingNumbers(gplMap);
        updateFamilyNames(gplMap);

        return gplMap;
    }

    private static void updateMissingNumbers(Map<String, Data> gplMap) {
        //        Update missing material numbers in gplMap
        gplMap.values().parallelStream().filter(v -> v.getMaterialNumberNUM().equals(""))
                .forEach((c) -> {
                    String bw = c.getMaterialNumberBW();
                    c.setMaterialNumberNUM(Utility.convertFromBWtoNUMformat(bw));
                    c.setMaterialNumberTP(Utility.convertFromBWtoTPformat(bw));
                });
    }

    private static void updateFamilyNames(Map<String, Data> map) {
        //        Update family names in map
        map.values().parallelStream().filter(v -> v.getFamilyName().equals("")||v.getFamilyName().equals("dummy"))
                .forEach((c) -> {
                    String fam = c.getFamilyName();
                    String bw = c.getMaterialNumberBW();
                    c.setFamilyName(nppMap.get(bw));
                });
    }

    @Override
    public void run() {
        mtrlMap = FileReader.readMaterialFile();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        globalMap = new HashMap<>();

        LOGGER.info("Start read from excel files");

        (new Thread(new Main())).start();

        gplMap = FileReader.readGPLfile();
        nppMap = FileReader.readNPPfile();

        // Merge into one map
        globalMap = mergeMaps(mtrlMap, gplMap);

        LOGGER.info("Size of gplMap after merge is {}.", gplMap.size());

        // Export gplMap values as Excel file 
        ExcelWriter.writeExcel(globalMap);
    }

}
