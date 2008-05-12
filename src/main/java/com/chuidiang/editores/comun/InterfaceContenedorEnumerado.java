package com.chuidiang.editores.comun;


import javax.swing.ComboBoxModel;


/**
 * Interface comun para las clases que contengan en su interior un
 * modelo de enumerado.<br>
 */
public interface InterfaceContenedorEnumerado
        
{
   //~ Metodos -----------------------------------------------------------------

   /**
    * Fija el modelo para el enumerado.<br>
    *
    * @param modelo Modelo para el enumerado.<br>
    */
   public void setModelo( ComboBoxModel modelo );

   /**
    * Devuelve el modelo del enumerado.<br>
    *
    * @return modelo del enumerado.<br>
    */
   public ComboBoxModel getModelo(  );
}
