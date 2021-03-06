package com.chuidiang.editores.primitivos.errores;

import java.awt.Component;

import javax.swing.JOptionPane;

import com.chuidiang.editores.comun.InterfaceEdicionDatos;
import com.chuidiang.editores.primitivos.Editor;


/**
 * Clase de error que saca un aviso en pantalla cuando se produce.<br>
 *
 */
public class ErrorConAviso<Tipo>
        implements InterfaceTratamientoError<Tipo>
{
   //~ Metodos -----------------------------------------------------------------

   /**
    * No hace nada.
    *
    * @param editor El editor
    */
   public void correcto( Editor<Tipo> editor )
   {
   }

   /**
    * Muestra un  mensaje de error indicando que el valor en el editor no es correcto
    *
    * @param error El error que se ha producido
    * @param editor El editor en el que esta el error.
    */
   public void tomaError( 
      String error,
      InterfaceEdicionDatos<Tipo> editor )
   {
   	if (editor instanceof Component)
      JOptionPane.showMessageDialog( 
         (Component)editor,
         error );
   	else
   		JOptionPane.showMessageDialog(null,error);
   }
}
