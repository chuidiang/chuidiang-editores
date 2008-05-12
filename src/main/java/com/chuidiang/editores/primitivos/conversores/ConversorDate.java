package com.chuidiang.editores.primitivos.conversores;


import org.apache.log4j.Logger;


import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Calendar;
import java.util.Date;


/**
 * Convierte un Date a String y viceversa.<br>
 */
public class ConversorDate
        implements InterfaceConversorString<Date>
{
   //~ Variables/Inicializadores estaticos -------------------------------------

   /** Patron por defecto para fecha/hora */
   public static String PATRON_DEFECTO = "dd/MM/yyyy HH:mm:ss";

   /** Mascara correspondiente a PATRON_DEFECTO */
   public static String MASCARA_DEFECTO = "##/##/#### ##:##:##";

   //~ Variables de instancia --------------------------------------------------

   /** Formateador por defecto. El patron que utiliza es dd/MM/yyyy HH:mm:ss */
   private SimpleDateFormat formato = new SimpleDateFormat( PATRON_DEFECTO );

   //~ Metodos -----------------------------------------------------------------

   /**
    * Cambia el patron de fecha/hora para presentarla de distinta manera.<br>
    *
    * @param patron patron tal cual lo entiende SimpleDateFormat.<br>
    */
   public void setPattern( String patron )
   {
      formato = new SimpleDateFormat( patron );
   }

   /**
    * Recoge la cadena y trata de convertirla en Date.<br>
    * Utiliza la clase SimpleDateFormat para la conversi�n.<br>
    * Lanza una ParseException si no se puede convertir la cadena a una
    * fecha/hora
    *
    * @param cadena Fecha/hora en formato String.<br>
    * @param valor Ignora el contenido de este campo.<br>
    *
    * @return Date con la fecha/hora contenida en el String.<br>
    */
   public Date parseString( 
      String cadena )
           throws java.text.ParseException
   {
      Date fecha = formato.parse( cadena );
      Calendar calendario = Calendar.getInstance(  );
      calendario.setTime( fecha );

      if( calendario.get( Calendar.YEAR ) >= 10000 )
      {
         throw new ParseException( "Fecha superior a 31/12/9999",0 );
      }

      return fecha;
   }

   /**
    * Recibe un Date y lo convierte a String.<br>
    *
    * @param valor Date que se quiere convertir a String.<br>
    *
    * @return String con el Date. null valor no es Date.<br>
    */
   public String toString( Date valor )
   {
      return formato.format( (Date)valor );
   }
}
