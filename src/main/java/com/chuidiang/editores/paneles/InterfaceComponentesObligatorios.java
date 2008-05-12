package com.chuidiang.editores.paneles;


import java.awt.Component;

/**
 * Interface para tratar aquellos componentes de una ventana de edici�n que
 * es obligatorio que el operador rellene.
 * Una clase que implemente esta interface podr�a, por ejemplo, hacer que esos
 * componentes se muestren de un color distinto o deshabilitar un bot�n de
 * "Aceptar".
 *
 * RESPONSABILIDADES:
 * - Interface com�n para tratamiento de componentes que es obligatorio rellenar
 * en un editor.
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