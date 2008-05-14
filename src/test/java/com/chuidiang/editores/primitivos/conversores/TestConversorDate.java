package com.chuidiang.editores.primitivos.conversores;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import junit.framework.TestCase;

public class TestConversorDate extends TestCase {

    public void testSetPattern() {
        ConversorDate conversor = new ConversorDate();
        conversor.setPattern("dd MMM yyyy");
        Calendar fecha2 = Calendar.getInstance();
        fecha2.set(Calendar.YEAR, 1994);
        fecha2.set(Calendar.MONTH, 1);
        fecha2.set(Calendar.DAY_OF_MONTH, 11);
        fecha2.set(Calendar.HOUR_OF_DAY, 15);
        fecha2.set(Calendar.MINUTE, 3);
        fecha2.set(Calendar.SECOND, 33);
        fecha2.set(Calendar.MILLISECOND,0);
        assertEquals("11 feb 1994", conversor.toString(fecha2.getTime()));
        
        conversor.setPattern("hh mm ss");
        assertEquals("03 03 33",conversor.toString(fecha2.getTime()));
    }

    public void testParseString() {
        ConversorDate conversor = new ConversorDate();

        Date fecha=null;
        try {
            fecha = conversor.parseString("11/02/1994 12:03:33");
        } catch (ParseException e) {
            fail("Parse Exception " + e);
        }

        Calendar fecha2 = Calendar.getInstance();
        fecha2.set(Calendar.YEAR, 1994);
        fecha2.set(Calendar.MONTH, 1);
        fecha2.set(Calendar.DAY_OF_MONTH, 11);
        fecha2.set(Calendar.HOUR_OF_DAY, 12);
        fecha2.set(Calendar.MINUTE, 3);
        fecha2.set(Calendar.SECOND, 33);
        fecha2.set(Calendar.MILLISECOND,0);
        assertEquals(fecha2.getTime(), fecha);
        
        try {
            fecha = conversor.parseString("11/02/1a94 12:03:33");
        } catch (ParseException e) {
            return;
        }
        
        fail("Parsea correctamente un año con a");
    }

    public void testToStringDate() {
        Calendar fecha2 = Calendar.getInstance();
        fecha2.set(Calendar.YEAR, 1994);
        fecha2.set(Calendar.MONTH, 1);
        fecha2.set(Calendar.DAY_OF_MONTH, 11);
        fecha2.set(Calendar.HOUR_OF_DAY, 12);
        fecha2.set(Calendar.MINUTE, 3);
        fecha2.set(Calendar.SECOND, 33);
        fecha2.set(Calendar.MILLISECOND,0);
        
        ConversorDate conversor = new ConversorDate();
        assertEquals("11/02/1994 12:03:33", conversor.toString(fecha2.getTime()));
    }

}
