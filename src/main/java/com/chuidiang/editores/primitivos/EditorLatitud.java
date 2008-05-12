package com.chuidiang.editores.primitivos;


import java.text.ParseException;

import javax.swing.text.MaskFormatter;

import com.chuidiang.editores.primitivos.conversores.ConversorLatitud;
import com.chuidiang.editores.primitivos.restricciones.RestriccionRango;


/**
 * Editor de Latitud.<br>
 */
public class EditorLatitud
        extends Editor<Number>
{
   //~ Variables/Inicializadores estaticos -------------------------------------

   /** Comment for <code>serialVersionUID</code> */
   private static final long serialVersionUID = 3689069555951874613L;

   //~ Constructores -----------------------------------------------------------

   /**
    * Crea una instacia nueva de EditorLatitud.
    */
   public EditorLatitud(  )
           throws ParseException
   {
      super( new MascaraLatitud(  ) );
      this.setConversorString( new ConversorLatitud(  ) );
      this.setRestriccion( new RestriccionRango( 
            -90.0,
            90.0 ) );
      this.setDato( new Double( 0.0 ) );
   }
}


/**
 * MaskFormatter para la latitud.<br>
 */
class MascaraLatitud
        extends MaskFormatter
{
   //~ Variables/Inicializadores estaticos -------------------------------------

   /** Comment for <code>serialVersionUID</code> */
   private static final long serialVersionUID = 3689069555951874613L;

   //~ Constructores -----------------------------------------------------------

   /**
    * Crea  un nuevo objeto de la clase MascaraLatitud.<br>
    */
   public MascaraLatitud(  )
           throws ParseException
   {
      super( ConversorLatitud.MASCARA_DEFECTO );
      this.setValidCharacters( " 0123456789NnSs" );
   }
}
