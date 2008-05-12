package com.chuidiang.editores.primitivos;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.text.MaskFormatter;

import com.chuidiang.editores.comun.InterfaceContenedorConversor;
import com.chuidiang.editores.comun.InterfaceContenedorRestriccion;
import com.chuidiang.editores.comun.InterfaceEdicionDatos;
import com.chuidiang.editores.primitivos.conversores.InterfaceConversorString;
import com.chuidiang.editores.primitivos.errores.ErrorConColor;
import com.chuidiang.editores.primitivos.errores.InterfaceTratamientoError;
import com.chuidiang.editores.primitivos.errores.Verificador;
import com.chuidiang.editores.primitivos.restricciones.InterfaceRestriccion;



/**
 * Editor configurable basado en JFormattedTextField.<br>
 * Se puede modificar la forma de ocnvertir de Object a String y viceversa,
 * asi como las restricciones que tiene que cumplir el Object para ser valido
 * (estar dentro de un rango, ser fecha festiva, numero primo, etc)
 */
public class Editor<Tipo>
        extends JFormattedTextField
        implements InterfaceEdicionDatos<Tipo>, 
        InterfaceContenedorConversor<Tipo>, 
        InterfaceContenedorRestriccion<Tipo>
{
   //~ Variables/Inicializadores estaticos -------------------------------------

   /** serialVersionUID */
   private static final long serialVersionUID = 3977861769710680377L;

   //~ Variables de instancia --------------------------------------------------

   /** Clase encargada de convertir de String a Object y viceversa */
   private InterfaceConversorString<Tipo> conversor = null;

   /**
    * Clase encargada de comprobar si un Object cumple determinadas condiciones
    */
   private InterfaceRestriccion<Tipo> restriccion = null;

   /** Ultimo valor correcto que ha habido en este editor. */
   private Tipo valorCorrecto = null;

   //~ Constructores -----------------------------------------------------------

   /**
    * Crea  un nuevo objeto de la clase Editor.<br>
    */
   public Editor(  )
   {
      this.ponListeners(  );
   }

   public Editor (MaskFormatter mascara)
   {
   	super (mascara);
   	this.ponListeners();
   }
   /**
    * Crea  un nuevo objeto de la clase Editor.<br>
    *
    * @param mascara M�scara que se desea para el editor tal cual la entiende
    *        MaskFormatter.<br>
    */
   public Editor( String mascara )
           throws ParseException
   {
      super( new MaskFormatter( mascara ) );
      ponListeners(  );
   }

   //~ Metodos -----------------------------------------------------------------

   /**
    * Setter for property conversor.
    *
    * @param conversor New value of property conversor.
    */
   public void setConversorString( InterfaceConversorString<Tipo> conversor )
   {
      this.conversor = conversor;
   }

   /**
    * Getter for property conversor.
    *
    * @return Value of property conversor.
    */
   public InterfaceConversorString<Tipo> getConversorString(  )
   {
      return conversor;
   }

   /**
    * Recoge el dato que se le pasa y lo muestra en el editor.<br>
    * Comprueba que el dato pasa la restricci�n. Si no es asi, no se cambia el
    * contenido del editor.
    *
    * @param valor COMENTARIO.<br>
    */
   public void setDato( Tipo valor )
   {
   	if (valor == null)
   	{
   		this.setText("");
   		return;
   	}
      if( restriccion != null )
      {
         if( restriccion.pasaRestriccion( 
               valor, null) == false )
         {
            return;
         }
      }

      if( conversor != null )
      {
         String texto = conversor.toString(valor );

         if( texto != null )
         {
            this.setText( texto );

            // Se llama al inputVerifier para eliminar el error si lo habia.
            this.getInputVerifier(  )
                .shouldYieldFocus( this );
         }
      }
      else
      {
      	this.setText(valor.toString());
        // Se llama al inputVerifier para eliminar el error si lo habia.
        this.getInputVerifier(  )
            .shouldYieldFocus( this );
      }
   }

   /**
    * Lee el contenido del JTextField lo convierte a Object y lo devuelve.
    * LLama al m�todo validaDato() y si este devuelve false, devuelve el
    * String del JTextField convertido a Object. Si hay alg�n problema en la
    * conversi�n, devuelve null.
    *
    * @param dato Lo ignora.<br>
    *
    * @return El contenido del JTextField convertido a Object.<br>
    */
   public Tipo getDato( )
   {
    this.validaDato( null );

    return valorCorrecto;
   }

   /**
    * Setter for property restriccion.
    *
    * @param restriccion New value of property restriccion.
    */
   public void setRestriccion( InterfaceRestriccion<Tipo> restriccion )
   {
      this.restriccion = restriccion;
   }

   /**
    * Getter for property restriccion.
    *
    * @return Value of property restriccion.
    */
   public InterfaceRestriccion<Tipo> getRestriccion(  )
   {
      return restriccion;
   }

   /**
    * Setter for property tratamientoError.
    *
    * @param tratamientoError New value of property tratamientoError.
    */
   public void setTratamientoError( InterfaceTratamientoError<Tipo> tratamientoError )
   {
      InputVerifier input = this.getInputVerifier(  );

      if( input instanceof Verificador )
      {
         ( (Verificador)input ).setTratamientoError( tratamientoError );
      }
   }

   /**
    * Getter for property tratamientoError.
    *
    * @return Value of property tratamientoError.
    */
   public InterfaceTratamientoError<Tipo> getTratamientoError(  )
   {
      InputVerifier input = this.getInputVerifier(  );

      if( input instanceof Verificador )
      {
         return ( (Verificador)input ).getTratamientoError(  );
      }

      return null;
   }

   /**
    * Hace el editor modificable o no.<br>
    *
    * @param editable true si se desea que sea editable.<br>
    */
   public void hazEditable( boolean editable )
   {
      this.setEditable( editable );
   }

   /**
    * Lee el contenido del JTextField y devuelve true si dicho contenido es correcto.<br>
    * Primero intenta convertir el String del JTextField a Object utilizando
    * InterfaceConversorString. Si no hay problemas, comprueba que el Object
    * cumple las restricciones  indicadas por Restriccion. Si se cumple todo,
    * devuelve true. Si hay algun problema, devuelve false y rellena error con
    * un texto que indica el error.
    *
    * @param error Devuelve un texto con el error si se produce.<br>
    *
    * @return true si el contenido del JTextField es correcto.
    */
   public boolean validaDato( StringBuffer error )
   {
      String cadena = this.getText(  );

      Tipo valor = null;

      if( conversor != null )
      {
         try
         {
            valor = conversor.parseString( 
                  cadena);
         }
         catch( Exception e )
         {
            valorCorrecto = null;

            if( error != null )
            {
               error.append( e.getMessage(  ) );
            }

            return false;
         }
      }

      if( restriccion != null )
      {
         if( restriccion.pasaRestriccion( 
               valor,
               error ) == true )
         {
            valorCorrecto = valor;
         }
         else
         {
            valorCorrecto = null;

            return false;
         }
      }

      this.valorCorrecto = valor;
      
      if (this.conversor != null)
      	this.setText( this.conversor.toString(this.valorCorrecto));

      return true;
   }

   /**
    * A�ade al editor los listeners para comprobar el valor cuando se pulsa 
    * <INTRO> o cuando se pierde el foco.<br>
    */
   private void ponListeners(  )
   {
      // Cuando se pulsa <INTRO> en el editor, se validan los datos.
      this.addActionListener( 
         new ActionListener(  )
         {
            public void actionPerformed( ActionEvent e )
            {
               if (getInputVerifier(  )
                  .shouldYieldFocus( (JComponent)e.getSource(  ) ))
               {
               	setDato(valorCorrecto);
               }
            }
         } );

      // Por defecto se hace que el editor cambie a color rojo cuando el
      // valor no sea correcto.
      this.setInputVerifier( 
         new Verificador( new ErrorConColor( 
               Color.WHITE,
               Color.RED ) ) );
   }
}
