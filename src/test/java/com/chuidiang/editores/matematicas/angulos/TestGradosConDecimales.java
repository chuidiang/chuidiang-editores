package com.chuidiang.editores.matematicas.angulos;

import junit.framework.TestCase;

/**
 * Test para la clase GradosConDecimales. Algunos valores según se me han
 * ido ocurriendo
 * @author chuidiang
 *
 */
public class TestGradosConDecimales extends TestCase {
    public void testGetGrados() {
        assertEquals(10, GradosConDecimales.getGrados(10.1));
        assertEquals(9, GradosConDecimales.getGrados(9.5));
        assertEquals(9, GradosConDecimales.getGrados(-9.5));
    }
    
    public void testGetGradosConDecimales() {
        assertEquals(10.5,GradosConDecimales.getGradosConDecimales(10, 30, 0, 1),1e-6);
        assertEquals(-10.5,GradosConDecimales.getGradosConDecimales(10, 30, 0, -1),1e-6);
        assertEquals(10.0+1.0/3600,GradosConDecimales.getGradosConDecimales(10, 0, 1, 1),1e-6);
        assertEquals(11,GradosConDecimales.getGradosConDecimales(10, 60, 00, 1),1e-6);
        assertEquals(11.5,GradosConDecimales.getGradosConDecimales(10, 90, 00, 1),1e-6);
    }
}
