/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tetrapak.processing.parts_control.materialmaster;

/**
 * Utility for partnumber validation and conversion methods
 *
 * @author SEPALMM
 */
public class Utility {

    /**
     * Block BW partnumbers belonging to services, e.g. 22-6423, 1-2779.
     *
     * Block BW partnumbers sold as service work kits, e.g. 200000001005,
     * 70000126861, X610000107, MAT_AG_UPGRADE KI.
     *
     * Block BW partnumbers not assigned, i.e. '#' or empty ''.
     *
     * @param partnumber
     * @return true if valid else false
     */
    public static boolean isValidPartnumber(String partnumber) {
        if (partnumber.matches("\\s+\\d{1,2}-\\d{4}$")
                || partnumber.matches("^[a-zA-Z].+")
                || partnumber.matches("^\\d+$")
                || partnumber.matches("^#$")
                || partnumber.isEmpty()) {

            return false;

        } else {

            return true;
        }
    }

    /**
     * Converts a part number from BW format to TP format by removing all white
     * space.
     *
     * @param materialNumberBW
     * @return materialNumberTP
     */
    public static String convertFromBWtoTPformat(String materialNumberBW) {
        String materialNumberTP = "";

        if (materialNumberBW.matches("^\\s{2}\\d{5}-\\d{4}$")
                || materialNumberBW.matches("^\\s{3}\\d{4}-\\d{4}")
                || materialNumberBW.matches("^6-.{12}$")) {
            materialNumberTP = materialNumberBW.replaceAll("\\s+", "");

        } else {
            materialNumberTP = materialNumberBW.replaceAll("\\s+", "");
        }

        return materialNumberTP;
    }

    /**
     * Converts a part number from BW format to NUM format by removing all
     * non-digits, insert zeroes after the first digit until total length
     * becomes 11.
     *
     * @param materialNumberBW
     * @return materialNumberNUM
     */
    public static String convertFromBWtoNUMformat(String materialNumberBW) {
        String materialNumberNUM = "";

        if (materialNumberBW.matches("^\\s{2}\\d{5}-\\d{4}$")
                || materialNumberBW.matches("^\\s{3}\\d{4}-\\d{4}")) {
            materialNumberNUM = materialNumberBW.replaceAll("\\D+", "");

        } else if (materialNumberBW.matches("^6-.+$")) {
            materialNumberNUM = materialNumberBW.replaceAll("\\D+", "");
            int length = materialNumberNUM.length();
            int qty = 11 - length;
            String zeroes = "";
            if (qty >= 0) {
                for (int i = 0; i < qty; i++) {
                    zeroes = new StringBuilder(zeroes).append("0").toString();
                }
            }

            materialNumberNUM = new StringBuilder(materialNumberNUM).insert(1, zeroes).toString();

        } else {
            materialNumberNUM = materialNumberBW.replaceAll("\\D+", "");
        }

        return materialNumberNUM;
    }

}
