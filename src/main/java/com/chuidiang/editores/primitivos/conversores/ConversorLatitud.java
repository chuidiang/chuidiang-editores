package com.chuidiang.editores.primitivos.conversores;



/**
 * Convierte un Double que representa una latitud en grados a String y
 * viceversa.<br>
 */
public class ConversorLatitud
        extends ConversorEjeGeografico
{
   //~ Variables/Inicializadores estaticos -------------------------------------

   /** Mascara correspondiente a PATRON_DEFECTO */
   public static String MASCARA_DEFECTO = "##"+ConversorEjeGeografico.SIMBOLO_GRADOS+"##''##''''U";

   //~ Metodos -----------------------------------------------------------------

   /**
    * COMENTARIO.<br>
    *
    * @return COMENTARIO.<br>
    */
   protected String getCadenaEjeNegativo(  )
   {
      return "S";
   }

   /**
    * COMENTARIO.<br>
    *
    * @return COMENTARIO.<br>
    */
   protected String getCadenaEjePositivo(  )
   {
      return "N";
   }

   /**
    * COMENTARIO.<br>
    *
    * @return COMENTARIO.<br>
    */
   protected String getNombreEje(  )
   {
      return "latitud";
   }

   /**
    * COMENTARIO.<br>
    *
    * @return COMENTARIO.<br>
    */
   protected String getPatternGrados(  )
   {
      return "00";
   }
}
