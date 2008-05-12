package com.chuidiang.editores.primitivos;


import java.text.ParseException;
import java.util.Date;

import com.chuidiang.editores.primitivos.conversores.ConversorDate;


/**
 * Editor de Fecha/Hora.<br>
 */
public class EditorDate
        extends Editor
{
   //~ Variables/Inicializadores estaticos -------------------------------------

   /** serialVersionUID */
   private static final long serialVersionUID = 3257282543647864880L;

   //~ Constructores -----------------------------------------------------------

   /**
    * Crea una instacia nueva de EditorDate.<br>
    * Este editor pierde los milisegundos respecto al setDate() y getDate(),
    * puesto que no se presentan en pantalla.
    */
   public EditorDate(  )
           throws ParseException
   {
      super( ConversorDate.MASCARA_DEFECTO );
      this.setConversorString( new ConversorDate(  ) );
      this.setDato(new Date());
   }

   /**
    * Crea una instacia nueva de EditorNumerico.<br>
    * El patr�n debe ser algo que entienda MaskFormatter (ejemplo
    * "##/##/####") y debe coincidir con el patr�n que entienda ConversorDate.
    *
    * @param mascara Patr�n para mostrar la fecha/hora en el editor!
    */
   public EditorDate( String mascara )
           throws ParseException
   {
      super( mascara );
      this.setConversorString( new ConversorDate(  ) );
      this.setDato(new Date());
   }

   //~ Metodos -----------------------------------------------------------------

   /**
    * Fija el patr�n para mostrar la fecha/hora en el editor.<br>
    * Debe ser un patr�n que entienda el ConversorDate que se utilice.
    *
    * @param patron Patr�n para fecha/hora que entienda ConversorDate
    */
   public void setPattern( String patron )
   {
      if( this.getConversorString(  ) instanceof ConversorDate )
      {
         ConversorDate conversor = (ConversorDate)this.getConversorString(  );
         conversor.setPattern( patron );
      }
   }
}
