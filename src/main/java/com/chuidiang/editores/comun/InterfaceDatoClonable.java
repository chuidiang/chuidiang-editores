package com.chuidiang.editores.comun;

/**
 * Interface para los datos clonables.<br>
 */
public interface InterfaceDatoClonable<Tipo>
{
   //~ Metodos -----------------------------------------------------------------

   /**
    * Devuelve una copia del dato.<br>
    *
    * @return Copia del dato.<br>
    */
   public Tipo clone(  );
}
