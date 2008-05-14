package com.chuidiang.editores.primitivos.errores;


import javax.swing.InputVerifier;
import javax.swing.JComponent;

import com.chuidiang.editores.primitivos.Editor;


/**
 * Hace que un editor primitivo no pueda perder el foco hasta que el
 * dato sea correcto.<br>
 * Pasando este Verificador a cualquier Editor que herede de 
 * <code>Editor</code>, el Editor no perderá el foco hasta que el
 * dato escrito en el sea correcto.
 */
public class Verificador<Tipo>
        extends InputVerifier
{
   //~ Variables de instancia --------------------------------------------------

   /**
    * Clase a la que se llamara cuando se produzca un error en la entrada de datos.<br>
    * Esta clase puede poner el editor de color rojo, sacar una ventana de
    * aviso, etc.
    */
   private InterfaceTratamientoError<Tipo> tratamientoError;

   //~ Constructores -----------------------------------------------------------

   /**
    * Crea un nuevo Verificador
    *
    * @param tratamientoError Clase a la que llamara cuando se produzca alguna
    *        entrada erronea por parte del operador
    */
   public Verificador( InterfaceTratamientoError<Tipo> tratamientoError )
   {
      this.tratamientoError = tratamientoError;
   }

   //~ Metodos -----------------------------------------------------------------

   /**
    * Setter for property tratamientoError.
    *
    * @param tratamientoError New value of property tratamientoError.
    */
   public void setTratamientoError( InterfaceTratamientoError<Tipo> tratamientoError )
   {
      this.tratamientoError = tratamientoError;
   }

   /**
    * Getter for property tratamientoError.
    *
    * @return Value of property tratamientoError.
    */
   public InterfaceTratamientoError<Tipo> getTratamientoError(  )
   {
      return tratamientoError;
   }

   /**
    * Verifica que la entrada en el JComponent que se le pasa es correcto.<br>
    * Devuelve true si dicha entrada es correcta.<br>
    * Llama a InterfaceTratamientoError.tomaError() si la entrada es incorrecta y devuelve false.
    *
    * @param input JComponent en el que el usuario introduce su valor.
    *
    * @return true si el valor introducido en el JComponent es correcto.
    */
   public boolean verify( JComponent input )
   {
       if (!(input instanceof Editor))
           return true;
           
      Editor<Tipo> editor = (Editor<Tipo>)input;
      StringBuffer error=new StringBuffer();
      

      if( editor.validaDato( error ) == true )
      {
         tratamientoError.correcto( editor );

         return true;
      }
      else
      {
         editor.setInputVerifier( null );
         tratamientoError.tomaError( 
            error.toString(),
            editor );
         editor.setInputVerifier( this );

         return false;
      }
   }
}
