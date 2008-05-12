package com.chuidiang.editores.primitivos;

import com.chuidiang.editores.primitivos.conversores.ConversorNumerico;
import com.chuidiang.editores.primitivos.restricciones.RestriccionRango;


/**
 * Editor para valores num�ricos.<br>
 * Admite un m�nimo y un m�ximo, de forma que el valor escrito por el usuario
 * no exceda dichso l�mites.
 */
public class EditorNumerico
        extends Editor<Number>
{
   //~ Variables/Inicializadores estaticos -------------------------------------

   /** Comment for <code>serialVersionUID</code> */
   private static final long serialVersionUID = 3257282517978788409L;

   //~ Constructores -----------------------------------------------------------

   /**
    * Crea una instacia nueva de EditorNumerico.
    */
   public EditorNumerico(  )
   {
   	this.setColumns(10);
      this.setConversorString( new ConversorNumerico(  ) );
      this.setRestriccion( new RestriccionRango(  ) );
   }

   /**
    * Crea una instacia nueva de EditorNumerico.
    *
    * @param minimo Valor m�nimo que admite el editor
    * @param maximo Valor m�ximo que admite el editor
    */
   public EditorNumerico( 
      double minimo,
      double maximo )
   {
      this(  );
      this.setMinimoMaximo( 
         minimo,
         maximo );
   }

   //~ Metodos -----------------------------------------------------------------

   /**
    * Fija los valores m�nimo y m�ximo que admite el editor.
    *
    * @param minimo Valor m�nimo que admite el editor.
    * @param maximo Valor m�ximo que admite el editor.
    */
   public void setMinimoMaximo( 
      double minimo,
      double maximo )
   {
      RestriccionRango restriccion = (RestriccionRango)this.getRestriccion(  );
      restriccion.setMinimoMaximo( 
         minimo,
         maximo );
   }

   /**
    * Fija el patr�n para el n�mero que puede escribir el usuario.<br>
    * El patr�n debe ser un String que entienda la clase DecimalFormat o, si
    * se modifica el Conversor por medio del m�todo setConversor(), un patr�n
    * que el nuevo conversor entienda.
    *
    * @param patron Cadena con el patr�n.<br>
    */
   public void setPattern( String patron )
   {
      ConversorNumerico conversor = (ConversorNumerico)this.getConversorString(  );
      conversor.setPattern( patron );
   }

   /**
    * Devuelve el double con el valor contenido en el editor.<br>
    *
    * @return El double contenido en el editor.<br>
    */
   public double dameDouble(  )
   {
      Number valor = (Number)this.getDato(  );

      return valor.doubleValue(  );
   }

   /**
    * Habilita o no la restricci�n de rango.<br>
    * Si se deshabilita la restricci�n de rango, el editor admitir� cualquier
    * n�mero.
    *
    * @param habilita true para habilitar la comprobaci�n de rango.
    */
   public void habilitaRango( boolean habilita )
   {
      RestriccionRango restriccion = (RestriccionRango)this.getRestriccion(  );
      restriccion.setActivo( habilita );
   }

   /**
    * Muestra el valor double que se le pasa en el editor.<br>
    *
    * @param valor El valor que se quiere mostrar en el editor.<br>
    */
   public void tomaDouble( double valor )
   {
      this.setDato( new Double( valor ) );
   }
}
