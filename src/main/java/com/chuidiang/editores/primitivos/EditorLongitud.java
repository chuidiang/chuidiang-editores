package com.chuidiang.editores.primitivos;


import java.text.ParseException;

import javax.swing.text.MaskFormatter;

import com.chuidiang.editores.primitivos.conversores.ConversorLongitud;
import com.chuidiang.editores.primitivos.restricciones.RestriccionRango;


/**
 * Editor de Longitud.<br>
 */
public class EditorLongitud
        extends Editor<Number>
{
   //~ Variables/Inicializadores estaticos -------------------------------------

   /** Comment for <code>serialVersionUID</code> */
   private static final long serialVersionUID = 3690198762949851445L;

   //~ Constructores -----------------------------------------------------------

   /**
    * Crea una instacia nueva de EditorLongitud.
    */
   public EditorLongitud(  )
           throws ParseException
   {
      super( new MascaraLongitud(  ) );
      this.setConversorString( new ConversorLongitud(  ) );
      this.setRestriccion( new RestriccionRango( 
            -180.0,
            180.0 ) );
      this.setDato( new Double( 0.0 ) );
   }
}


/**
 * MaskFormatter para el editor de Longitud.<br>
 */
class MascaraLongitud
        extends MaskFormatter
{
   //~ Variables/Inicializadores estaticos -------------------------------------

   /** Comment for <code>serialVersionUID</code> */
   private static final long serialVersionUID = 3689069555951874613L;

   //~ Constructores -----------------------------------------------------------

   /**
    * Crea  un nuevo objeto de la clase MascaraLongitud.<br>
    */
   public MascaraLongitud(  )
           throws ParseException
   {
      super( ConversorLongitud.MASCARA_DEFECTO );
      this.setValidCharacters( " 0123456789WwEe" );
   }
}
