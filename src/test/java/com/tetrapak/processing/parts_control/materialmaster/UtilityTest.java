/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tetrapak.processing.parts_control.materialmaster;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author SEPALMM
 */
public class UtilityTest {

    public UtilityTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of isValidPartnumber method, of class Utility.
     */
    @Test
    public void testIsValidPartnumber() {
        System.out.println("isValidPartnumber");

        assertEquals(false, Utility.isValidPartnumber(""));
        assertEquals(false, Utility.isValidPartnumber("#"));
        assertEquals(false, Utility.isValidPartnumber("X610000107"));
        assertEquals(false, Utility.isValidPartnumber("MAT_AG_UPGRADE KI"));
        assertEquals(false, Utility.isValidPartnumber("200000037702"));
        assertEquals(false, Utility.isValidPartnumber("70000126861"));
        assertEquals(false, Utility.isValidPartnumber("1"));
        assertEquals(false, Utility.isValidPartnumber("     21-1200"));
        assertEquals(false, Utility.isValidPartnumber("      9-0099"));

        assertEquals(true, Utility.isValidPartnumber("  90005-0060"));
        assertEquals(true, Utility.isValidPartnumber("  88000-5146"));
        assertEquals(true, Utility.isValidPartnumber("   9728-0000"));
        assertEquals(true, Utility.isValidPartnumber("5945531-0952"));
        assertEquals(true, Utility.isValidPartnumber(" 342701-0205"));
        assertEquals(true, Utility.isValidPartnumber("6-1 647630 02"));
        assertEquals(true, Utility.isValidPartnumber("6-39007 0631 6"));
        assertEquals(true, Utility.isValidPartnumber("6-553685 81"));
        assertEquals(true, Utility.isValidPartnumber("6-71564 00"));
        assertEquals(true, Utility.isValidPartnumber("6-990417 72"));

    }

    /**
     * Test of convertFromBWtoTPformat method, of class Utility.
     */
    @Test
    public void testConvertFromBWtoTPformat() {
        System.out.println("convertFromBWtoTPformat");

        assertEquals("90450-0240", Utility.convertFromBWtoTPformat("  90450-0240"));
        assertEquals("9728-0000", Utility.convertFromBWtoTPformat("   9728-0000"));
        assertEquals("6-3135303813", Utility.convertFromBWtoTPformat("6-31353 0381 3"));
        assertEquals("5941716-0990", Utility.convertFromBWtoTPformat("5941716-0990"));
        assertEquals("321157-0340", Utility.convertFromBWtoTPformat(" 321157-0340"));
        assertEquals("6-22340609", Utility.convertFromBWtoTPformat("6-223406 09"));
        assertEquals("6-32300", Utility.convertFromBWtoTPformat("6-323 00"));
        assertEquals("6-150800", Utility.convertFromBWtoTPformat("6-1508 00"));
        assertEquals("6-1001000", Utility.convertFromBWtoTPformat("6-10010 00"));
        assertEquals("6-10412500", Utility.convertFromBWtoTPformat("6-104125 00"));
        assertEquals("1284500-0007", Utility.convertFromBWtoTPformat("1284500-0007"));
        assertEquals("6-1050170109", Utility.convertFromBWtoTPformat("6-105017 0109"));
        assertEquals("6-1521105229", Utility.convertFromBWtoTPformat("6-1521 105 229"));
    }

    /**
     * Test of convertFromBWtoNUMformat method, of class Utility.
     */
    @Test
    public void testConvertFromBWtoNUMformat() {
        System.out.println("convertFromBWtoNUMformat");

        assertEquals("904500240", Utility.convertFromBWtoNUMformat("  90450-0240"));
        assertEquals("97280000", Utility.convertFromBWtoNUMformat("   9728-0000"));
        assertEquals("63135303813", Utility.convertFromBWtoNUMformat("6-31353 0381 3"));
        assertEquals("59417160990", Utility.convertFromBWtoNUMformat("5941716-0990"));
        assertEquals("3211570340", Utility.convertFromBWtoNUMformat(" 321157-0340"));
        assertEquals("60022340609", Utility.convertFromBWtoNUMformat("6-223406 09"));
        assertEquals("60000032300", Utility.convertFromBWtoNUMformat("6-323 00"));
        assertEquals("60000150800", Utility.convertFromBWtoNUMformat("6-1508 00"));
        assertEquals("60001001000", Utility.convertFromBWtoNUMformat("6-10010 00"));
        assertEquals("60010412500", Utility.convertFromBWtoNUMformat("6-104125 00"));
        assertEquals("12845000007", Utility.convertFromBWtoNUMformat("1284500-0007"));
        assertEquals("61050170109", Utility.convertFromBWtoNUMformat("6-105017 0109"));
        assertEquals("61521105229", Utility.convertFromBWtoNUMformat("6-1521 105 229"));
    }

}
