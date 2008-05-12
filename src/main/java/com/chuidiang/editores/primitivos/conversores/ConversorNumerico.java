package com.chuidiang.editores.primitivos.conversores;

import java.text.DecimalFormat;




/**
 * Convierte un Number a String y viceversa.<br>
 */
public class ConversorNumerico
        implements InterfaceConversorString<Number>
{
   //~ Variables/Inicializadores estaticos -------------------------------------

   //~ Variables de instancia --------------------------------------------------

   /** Clase para la conversion de Number a String y viceversa. */
   private DecimalFormat formato = new DecimalFormat(  );

   //~ Metodos -----------------------------------------------------------------

   /**
    * Se le pasa el patron deseado para la conversion de String a Number y
    * viceversa.<br>
    *
    * @param patron Patron tal cual lo entiende la clase DecimalFormat.<br>
    */
   public void setPattern( String patron )
   {
      formato.applyPattern( patron );
   }

   /**
    * Convierte el String que se le pasa a Number.<br>
    * Lanza una ParseException si no se puede hacer la conversion.
    *
    * @param cadena String que contiene un numero.<br>
    * @param valor Ignora este campo.<br>
    *
    * @return Devuelve un Number.<br>
    */
   public Number parseString( 
      String cadena)
           throws java.text.ParseException
   {
      return formato.parse( cadena );
   }

   /**
    * Se le pasa un Number y lo convierte a String.<br>
    *
    * @param valor Number con el valor que se quiere convertir a String.<br>
    *
    * @return String con el valor. null si valor no es Number<br>
    */
   public String toString( Number valor )
   {
       if (valor==null)
           return null;
      return formato.format(valor.doubleValue(  ) );
   }
}
