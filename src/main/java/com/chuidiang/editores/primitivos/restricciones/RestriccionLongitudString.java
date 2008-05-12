package com.chuidiang.editores.primitivos.restricciones;



/**
 * Restriccion de longitud en una cadena de caracteres.<br>
 *
 */
public class RestriccionLongitudString
        implements InterfaceRestriccion
{
   //~ Variables de instancia --------------------------------------------------

   /** Si la restriccion est� o no activa. Si no est� activa, es v�lida cualquier
    * longitud de cadena.*/
   private boolean activo = false;

   /** Longitud m�xima permitida */
   private int longitud = 0;

   //~ Constructores -----------------------------------------------------------

   /**
    * Crea  un nuevo objeto de la clase RestriccionLongitudString.<br>
    * No est� activo por defecto.
    */
   public RestriccionLongitudString(  )
   {
   }

   /**
    * Crea  un nuevo objeto de la clase RestriccionLongitudString.<br>
    * Esta activo por defecto.
    *
    * @param longitud Longitud m�xima para la cadena.<br>
    */
   public RestriccionLongitudString( int longitud )
   {
   		this.setActivo(true);
      this.longitud = longitud;
   }

   //~ Metodos -----------------------------------------------------------------

   /**
    * Activa el filtro.<br>
    *
    * @param activo true para activarlo.<br>
    */
   public void setActivo( boolean activo )
   {
      this.activo = activo;
   }

   /**
    * Devuelve si el filtro esta activo.<br>
    *
    * @return true si esta activo.<br>
    */
   public boolean isActivo(  )
   {
      return activo;
   }

   /**
    * Se le pasa la longitud maxima para la cadena.<br>
    * No comprueba el valor que se le pasa.
    *
    * @param longitud Longitud maxima para la cadena.<br>
    */
   public void setLongitud( int longitud )
   {
      this.longitud = longitud;
   }

   /**
    * Devuelve la longitud maxima para la cadena.<br>
    *
    * @return Longitud maxima.<br>
    */
   public int getLongitud(  )
   {
      return longitud;
   }

   /**
    * @see com.chuidiang.editores.primitivos.restricciones.InterfaceRestriccion#pasaRestriccion(java.lang.Object,
    *      java.lang.StringBuffer)
    */
   public boolean pasaRestriccion( 
      Object valor,
      StringBuffer error )
   {
      if( ( valor == null ) || !isActivo(  ) )
      {
         return true;
      }

      if( valor.toString(  )
               .length(  ) > getLongitud(  ) )
      {
         error.delete( 
            0,
            error.length(  ) );
         error.append( "La cadena excede " + getLongitud(  ) + " caracteres" );

         return false;
      }

      return true;
   }
}
