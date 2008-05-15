package com.chuidiang.editores.paneles;


import java.awt.Component;

/**
 * Interface para tratar aquellos componentes de una ventana de edicion que
 * es obligatorio que el operador rellene.
 * Una clase que implemente esta interface podria, por ejemplo, hacer que esos
 * componentes se muestren de un color distinto o deshabilitar un boton de
 * "Aceptar" si no estan rellenos.
 *
 */
public interface InterfaceComponentesObligatorios  {    
    
	/**
	 * Recibe los componentes que son de relleno obligatorio.<br>
	 * La clase los marca de alguna manera especial y mira su contenido para
	 * habilitar o no botones de "Aceptar", etc.
	 * @param componentes
	 */
    public void eliminaComponentes (Component[] componentes);
    
    
    /**
     * Recibe los componentes que ya no son obligatorios.<br>
     * Los desmarca y no tiene en cuenta su contenido para habilitar o no 
     * botones de "aceptar".
     * @param componentes
     */
    public void anhadeComponentes(Component[] componentes);    
    
}