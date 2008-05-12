package com.chuidiang.editores.paneles;


import java.awt.event.ActionListener;

import com.chuidiang.editores.comun.InterfaceEdicionDatos;


/**
 * Interface comun para las ventanas con bot�n de aceptar y cancelar y que
 * tengan un panel de edicion en su interior.<br>
 */
public interface InterfaceAceptarCancelarEdicion
        extends InterfaceEdicionDatos
{
   //~ Variables/Inicializadores estaticos -------------------------------------

   /** Para suscribirse al bot�n de aceptar */
   public static final int ACEPTAR = 0;

   /** Para suscribirse al bot�n de cancelar */
   public static final int CANCELAR = 1;

   //~ Metodos -----------------------------------------------------------------

   /**
    * Pone el panel de edici�n dentro de la ventana de acpetar/cancelar.<br>
    *
    * @param componenteInterno Un Component que implemente
    *        InterfaceEdicionDatos.<br>
    */
   public void setComponenteInterno( InterfaceEdicionDatos componenteInterno );

   /**
    * Devuelve el Component que implementa InterfaceEdicionDatos.<br>
    *
    * @return El panel de edicion.<br>
    */
   public InterfaceEdicionDatos getComponenteInterno(  );

   /**
    * A�ade el actionListener a la acci�n de aceptar o cancelar la edici�n.<br>
    * El parametro aceptarOCancelar debe ser InterfaceAceptarCancelarEdicion.ACEPTAR
    * o InterfaceAceptarCancelarEdicon.CANCELAR
    *
    * @param l El actionListener.<br>
    * @param aceptarOCancelar Si se suscribe a aceptar o cancelar.<br>
    */
   public void addActionListener( 
      ActionListener l,
      int aceptarOCancelar );

   /**
    * Elimina el listener que se le pasa de la lista de listeners a la accion
    * de aceptar o cancelar.<br>
    * El parametro aceptarOCancelar debe ser InterfaceAceptarCancelarEdicion.ACEPTAR
    * o InterfaceAceptarCancelarEdicon.CANCELAR
    *
    * @param l Listener.<br>
    * @param aceptarOCancelar si se borra de aceptar o cancelar.<br>
    */
   public void removeActionListener( 
      ActionListener l,
      int aceptarOCancelar );
}
