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
 *
 * @author SEPALMM
 */
public class Main {

    private static Map<String, Data> map;
    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    private static Map<String, Data> mergeMaps(
            Map<String, Data> mtrlMap, Map<String, Data> gplMap) {

//        Merge the maps
        mtrlMap.forEach((k, v) -> gplMap.putIfAbsent(k, v));

//        Update missing material numbers in gplMap
        updateMissingNumbers(gplMap);

        return gplMap;
    }

    private static void updateMissingNumbers(Map<String, Data> gplMap) {
        //        Update missing material numbers in gplMap
        gplMap.values().stream().filter(v -> v.getMaterialNumberNUM().equals(""))
                .forEach((c) -> {
                    String bw = c.getMaterialNumberBW();
                    c.setMaterialNumberNUM(Utility.convertFromBWtoNUMformat(bw));
                    c.setMaterialNumberTP(Utility.convertFromBWtoTPformat(bw));
                });
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        map = new HashMap<>();
        Map<String, Data> mtrlMap = FileReader.readMaterialFile();
        Map<String, Data> gplMap = FileReader.readGPLfile();

        // Merge into one map
        map = mergeMaps(mtrlMap, gplMap);
        LOGGER.info("Size of gplMap after merge is {}.", gplMap.size());

        // Export gplMap values as Excel file
    }

}