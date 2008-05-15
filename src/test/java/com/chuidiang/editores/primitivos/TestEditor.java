package com.chuidiang.editores.primitivos;

import com.chuidiang.editores.primitivos.conversores.ConversorNumerico;

import junit.framework.TestCase;

public class TestEditor extends TestCase {

    /**
     * Comprobacion de que getDato() devuelve null si el dato no es
     * correcto sin modificar el contenido del textfield.
     */
    public void testGetDato() {
        Editor<Number> editor = new Editor<Number>();
        editor.setText("hola radiola");
        assertNull(editor.getDato());
        assertEquals("hola radiola",editor.getText());
        
        editor.setConversorString(new ConversorNumerico());
        assertNull(editor.getDato());
        assertEquals("hola radiola",editor.getText());
        
        editor.setText("12");
        assertEquals(12, editor.getDato().intValue());
        assertEquals("12",editor.getText());
    }

    /**
     * Comprobacion de que validaDato() no modifica el texto visible
     * en el JTextField.
     */
    public void testValidaDato() {
        Editor<Number> editor = new Editor<Number>();
        editor.setText("hola radiola");
        assertTrue(editor.validaDato(new StringBuffer()));
        assertEquals("hola radiola",editor.getText());
        
        editor.setConversorString(new ConversorNumerico());
        assertFalse(editor.validaDato(new StringBuffer()));
        assertEquals("hola radiola",editor.getText());
        
        editor.setText("11");
        assertTrue(editor.validaDato(new StringBuffer()));
        assertEquals("11",editor.getText());
    }

}
