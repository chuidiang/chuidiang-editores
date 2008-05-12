package com.chuidiang.editores.primitivos.restricciones;


import org.apache.log4j.Logger;

import com.chuidiang.editores.utilidades.CadenaCaracteresEditoresDeprecated;


/**
 * Modelo de filtro para strings.<br>
 * Comprueba que el String cumple un patr�n con  e ?, similar al del comando
 * ls de unix para listar ficheros.
 */
public class RestriccionMascaraString
        implements InterfaceRestriccion<String>
{
   //~ Variables/Inicializadores estaticos -------------------------------------

   /** Caracter  para el mascara */
   public static final String ASTERISCO = new String( "*" );

   /** Caracter interrogante para el mascara */
   public static final String INTERROGANTE = new String( "?" );

   /**
    * Prefijo que hay que insertar delante del asterisco y del  interrogante
    * para que funcione String.matches()
    */
   public static final String PREFIJO = new String( "." );

   //~ Variables de instancia --------------------------------------------------

   /** Logger para la clase */
   private Logger log = Logger.getLogger( RestriccionMascaraString.class );

   /**
    * Patron con el que se compara para.<br>
    * El patr�n es con  e ?. Por defecto todo pasa el filtro
    */
   private String mascara = null;

   //~ Metodos -----------------------------------------------------------------

   /**
    * Se le pasa la m�scara de filtrado.<br>
    * La m�scara es un String con  e ?. Si es null, el filtro queda
    * desactivado y cualquier cadena lo pasa.
    *
    * @param mascara La m�scara
    */
   public void setMascara( String mascara )
   {
      this.mascara = mascara;
   }

   /**
    * Devuelve la m�scara con la que se est� filtrando.<br>
    *
    * @return M�scara
    */
   public String getMascara(  )
   {
      return mascara;
   }

   /**
    * @see com.chuidiang.editores.filtros.InterfaceModeloFiltroGenerico#pasaFiltro(java.lang.Object)
    */
   public boolean pasaRestriccion( 
      String cadena,
      StringBuffer errores )
   {
      if( !( cadena instanceof String ) )
      {
         log.warn( "El elemento pasado no es String" );
         errores.delete( 
            0,
            errores.length(  ) );
         errores.append( "El elemento pasado no es String" );

         return false;
      }

      if( mascara == null )
      {
         return true;
      }

      if( CadenaCaracteresEditoresDeprecated.pasaFiltro( 
            (String)cadena,
            mascara ) )
      {
         return true;
      }

      errores.delete( 
         0,
         errores.length(  ) );
      errores.append( 
         "La cadena -" + cadena + "- no cumple el mascara -" + mascara + "-" );

      return false;
   }
}
