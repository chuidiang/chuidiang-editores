package com.chuidiang.editores.comun;

import com.chuidiang.editores.primitivos.restricciones.InterfaceRestriccion;

/**
 * COMENTARIO.<br>
 */
public interface InterfaceContenedorRestriccion<Tipo>
{
   //~ Metodos -----------------------------------------------------------------

   /**
    * Se fijan las restricciones para el dato.<br>
    *
    * @param restriccion restricciones deseadas.<br>
    */
   public void setRestriccion( InterfaceRestriccion<Tipo> restriccion );

   /**
    * Devuelve las reestricciones que tiene el dato.<br>
    *
    * @return restricciones.<br>
    */
   public InterfaceRestriccion<Tipo> getRestriccion(  );
}
