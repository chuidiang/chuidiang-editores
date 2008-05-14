package com.chuidiang.editores.primitivos.errores;

import com.chuidiang.editores.comun.InterfaceEdicionDatos;
import com.chuidiang.editores.primitivos.Editor;


/**
 * InterfaceComun para el tratamiento de entradas de usuario erroneas en
 * los editores.<br>
 */
public interface InterfaceTratamientoError<Tipo>
{
   //~ Metodos -----------------------------------------------------------------

   /**
    * Metodo al que se llama cuando el usuario introduce una entrada valida.<br>
    * La clase que implemente este metodo debe hacer que el Editor adquiera el
    * aspecto de entrada correcta (por ejemplo, si el color de fondo rojo
    * indica entrada no valida, debe cambiar el color al color defecto).
    *
    * @param editor Editor en el que se ha producido la entrada.<br>
    */
   public void correcto( Editor<Tipo> editor );

   /**
    * Metodo al que se llama cuando la entrada de usuario en el editor no
    * es valida.<br>
    * La clase que implemente este metodo debe marcar el editor como con entrada
    * no valida (por ejemplo, fondo rojo).
    * En error se le pasa una cadena con el error producido. Puede sacar un log,
    * una ventana de aviso, etc.
    *
    * @param error Texto con el motivo por el que la entrada es erronea.<br>
    * @param editor Editor en el que se ha producido la entrada erronea.<br>
    */
   public void tomaError( 
      String error,
      InterfaceEdicionDatos<Tipo> editor );
}
