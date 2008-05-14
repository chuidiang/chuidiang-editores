package com.chuidiang.editores.primitivos.restricciones;

/**
 * Interface comun para poner restricciones a un valor de un Editor.<br>
 */
public interface InterfaceRestriccion<Tipo>
{
   //~ Metodos -----------------------------------------------------------------

   /**
    * Se le pasa un valor y devuelve true si dicho valor cumple con la
    * restriccion.<br>
    *
    * @param valor valor que se quiere saber si pasa la restriccion.<br>
    * @param error Un StringBuffer en el que la clase dejara el texto de error
    *        si se produce.<br>
    *
    * @return true si el valor pasa la restriccion.<br>
    */
   public boolean pasaRestriccion( 
      Tipo valor,
      StringBuffer error );
}
