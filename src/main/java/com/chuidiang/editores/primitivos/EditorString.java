package com.chuidiang.editores.primitivos;

import com.chuidiang.editores.primitivos.restricciones.RestriccionLongitudString;


/**
 * Editor de String con restriccion de longitud.<br>
 * Si no se va a usar ninguna restriccion de longitud, puede usarse
 * directamente la clase padre Editor como editor de String sin restricciones
 */
public class EditorString
        extends Editor
{
   //~ Variables/Inicializadores estaticos -------------------------------------

   /** Comment for <code>serialVersionUID</code> */
   private static final long serialVersionUID = 3544394703555474227L;

   //~ Constructores -----------------------------------------------------------

   /**
    * Crea  un nuevo objeto de la clase EditorString.<br>
    * El editor no tiene ninguna restriccion de longitud de la cadena.
    */
   public EditorString(  )
   {
   }

   /**
    * Crea  un nuevo objeto de la clase EditorString.<br>
    * La longitud de la cadena queda restringida a longitudMaxima
    *
    * @param longitudMaxima Longitud maxima para la cadena.<br>
    */
   public EditorString( int longitudMaxima )
   {
      this.setLongitudMaxima( longitudMaxima );
   }

   //~ Metodos -----------------------------------------------------------------

   /**
    * Fija la longitud maxima de caracteres que admitir� el editor.<br>
    *
    * @param longitudMaxima Longitud maxima para la cadena.<br>
    */
   public void setLongitudMaxima( int longitudMaxima )
   {
      Object posibleRestriccion = this.getRestriccion(  );

      if( posibleRestriccion instanceof RestriccionLongitudString )
      {
         ( (RestriccionLongitudString)posibleRestriccion ).setLongitud( 
            longitudMaxima );
         this.setRestriccion( (RestriccionLongitudString)posibleRestriccion );
      }
      else
      {
         this.setRestriccion( new RestriccionLongitudString( longitudMaxima ) );
      }
   }
}
