package com.chuidiang.editores.paneles;

import org.apache.log4j.BasicConfigurator;

import com.chuidiang.editores.primitivos.EditorBooleano;
import com.chuidiang.editores.primitivos.EditorNumerico;
import com.chuidiang.editores.utilidades.BeanParaPruebas;

import junit.framework.TestCase;

public class TestEditorPanelBean extends TestCase {
    public TestEditorPanelBean()
    {
        BasicConfigurator.configure();
    }
    public void testSetDato()
    {
        EditorPanelBean<BeanParaPruebas> editor = new EditorPanelBean<BeanParaPruebas>(BeanParaPruebas.class);
        EditorBooleano editorBooleano = new EditorBooleano();
        editor.anhadeEditor(BeanParaPruebas.BOOLEANO, "el booleano", editorBooleano);
        EditorNumerico editorNumerico = new EditorNumerico();
        editor.anhadeEditor(BeanParaPruebas.ENTERO, "el entero", editorNumerico);
        EditorNumerico editorEnteroPrimitivo = new EditorNumerico();
        editor.anhadeEditor(BeanParaPruebas.ENTERO_PRIMITIVO,"otro", editorEnteroPrimitivo);
        
        BeanParaPruebas bean = new BeanParaPruebas();
        bean.setBooleano(true);
        
        editor.setDato(bean);
        assertEquals(true, editorBooleano.isSelected());
        assertEquals("",editorNumerico.getText());
        assertEquals("0",editorEnteroPrimitivo.getText());

        
        bean.setBooleano(false);
        bean.setEnteroPrimitivo(11);
        bean.setEntero(12);
        editor.setDato(bean);
        assertEquals(false, editorBooleano.isSelected());
        assertEquals("12",editorNumerico.getText());
        assertEquals("11",editorEnteroPrimitivo.getText());
        
        editor.setDato(null);
        assertEquals(false, editorBooleano.isSelected());
        assertEquals("",editorNumerico.getText());
        assertEquals("",editorEnteroPrimitivo.getText());

    }
}

