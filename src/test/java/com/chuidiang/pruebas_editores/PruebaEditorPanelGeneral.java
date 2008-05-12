package com.chuidiang.pruebas_editores;

import java.text.ParseException;
import java.util.Hashtable;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import com.chuidiang.editores.paneles.EditorPanelGeneral;
import com.chuidiang.editores.primitivos.EditorBooleano;
import com.chuidiang.editores.primitivos.EditorComboBox;
import com.chuidiang.editores.primitivos.EditorDate;
import com.chuidiang.editores.primitivos.EditorLatitud;
import com.chuidiang.editores.primitivos.EditorLongitud;
import com.chuidiang.editores.primitivos.EditorNumerico;

public class PruebaEditorPanelGeneral {

    /**
     * @param args
     */
    public static void main(String[] args) {
        
        // Construccion del editor
        EditorPanelGeneral panel = new EditorPanelGeneral();
        panel.anhadeEditor("entero", "el entero", new EditorNumerico(-10.0,10.0));
        panel.anhadeEditor("booleano", "el flag", new EditorBooleano());
        try {
            panel.anhadeEditor("latitud","la latitud", new EditorLatitud());
            panel.anhadeEditor("longitud","la longitud", new EditorLongitud());
            panel.anhadeEditor("fecha", "la fecha", new EditorDate());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        
        // Un menú para el editor
        Hashtable<Integer,String> enumerado = new Hashtable<Integer,String>();
        enumerado.put(1, "uno");
        enumerado.put(2, "dos");
        enumerado.put(3, "tres");
        panel.anhadeEditor("enumerado", "el enumerado", new EditorComboBox<Integer, String>(enumerado));
        
        
        // Se muestra la ventana
        JFrame v = new JFrame("Prueba EditorPanelGeneral");
        v.getContentPane().add(panel);
        v.pack();
        v.setVisible(true);
        v.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        
        // Se le pasa un dato
        Hashtable<String, Object> datos = new Hashtable<String, Object>();
        datos.put("entero", 6);
        datos.put("booleano",true);
        datos.put("latitud",new Double(36.0));
        panel.setDato(datos);
        
    }

}
