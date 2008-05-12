package com.chuidiang.editores.primitivos.restricciones;



/**
 * Restriccion para un enumerado.<br>
 *
 */
public class RestriccionObjectEquals
        implements InterfaceRestriccion<Object>
{
   //~ Variables de instancia --------------------------------------------------

   /** Valor valido que pasa el filtro*/
   private Object valorValido = null;

   //~ Metodos -----------------------------------------------------------------

   /**
    * Se pasa el valor valido que pasa el filtro.<br>
    * Si el valor valido es null, cualquier valor pasara el filtro.
    *
    * @param valorValido valor valido o null.<br>
    */
   public void setValorValido( Object valorValido )
   {
      this.valorValido = valorValido;
   }

   /**
    * Devuelve el valor valido que pasa el filtro.<br>
    *
    * @return El valor valido o null.<br>
    */
   public Object getValorValido(  )
   {
      return valorValido;
   }

   /* (non-Javadoc)
    * @see com.chuidiang.editores.comun.InterfaceRestriccion#pasaRestriccion(java.lang.Object, java.lang.StringBuffer)
    */
   public boolean pasaRestriccion( 
      Object valor,
      StringBuffer error )
   {
      // Si ambos son null o exactamente iguales, se pasa la restricci�n
      if( valor == valorValido )
      {
         return true;
      }

      // si valor es null y valorValido no, no se pasa la restricci�n
      if( null == valor )
      {
         return false;
      }

      // Si valorValido es null, cualquier valor para la restriccion.
      if( null == valorValido )
      {
         return true;
      }

      // Si ninguno es null, se comprueba que son iguales
      return valor.equals( valorValido );
   }
}
