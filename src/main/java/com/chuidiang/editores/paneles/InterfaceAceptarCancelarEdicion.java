package com.chuidiang.editores.paneles;


import java.awt.event.ActionListener;

import com.chuidiang.editores.comun.InterfaceEdicionDatos;


/**
 * Interface comun para las ventanas con boton de aceptar y cancelar y que
 * tengan un panel de edicion en su interior.<br>
 */
public interface InterfaceAceptarCancelarEdicion<Tipo>
        extends InterfaceEdicionDatos<Tipo>
{
   //~ Variables/Inicializadores estaticos -------------------------------------

   /** Para suscribirse al boton de aceptar */
   public static final int ACEPTAR = 0;

   /** Para suscribirse al boton de cancelar */
   public static final int CANCELAR = 1;

   //~ Metodos -----------------------------------------------------------------

   /**
    * Pone el panel de edicion dentro de la ventana de acpetar/cancelar.<br>
    *
    * @param componenteInterno Un Component que implemente
    *        InterfaceEdicionDatos.<br>
    */
   public void setComponenteInterno( InterfaceEdicionDatos<Tipo> componenteInterno );

   /**
    * Devuelve el Component que implementa InterfaceEdicionDatos.<br>
    *
    * @return El panel de edicion.<br>
    */
   public InterfaceEdicionDatos<Tipo> getComponenteInterno(  );

   /**
    * Anade el actionListener a la accion de aceptar o cancelar la edicion.<br>
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
