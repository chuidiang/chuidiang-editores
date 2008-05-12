package com.chuidiang.editores.primitivos;

import javax.swing.JCheckBox;

import org.apache.log4j.Logger;

import com.chuidiang.editores.comun.InterfaceEdicionDatos;



/**
 * Editor booleano que implemente InterfaceEdicionDatos.<br>
 * El dato que maneja en setDato() y getDato() es un Boolean.
 */
public class EditorBooleano
        extends JCheckBox
        implements InterfaceEdicionDatos<Boolean>
{
   //~ Variables/Inicializadores estaticos -------------------------------------

   /** Logger de la clase */
   private static Logger log = Logger.getLogger( EditorBooleano.class );

   /** Comment for <code>serialVersionUID</code> */
   private static final long serialVersionUID = 3544394694999226681L;

   //~ Metodos -----------------------------------------------------------------

   /**
    * Admite un Boolean y lo refleja en el editor.<br>
    *
    * @param dato El dato.<br>
    */
   public void setDato( Boolean dato )
   {
   	// Si el dato es null, se vacia el editor
   	if (dato == null)
   	{
   		this.setDato (new Boolean(false));
   		return;
   	}

      boolean valor = dato.booleanValue(  );
      this.setSelected( valor );
   }

   /**
    * Devuelve un Boolean con el estado del JCheckBox.<br>
    *
    * @param dato se ignora.<br>
    *
    * @return un Boolean con el estado del JCheckBox.<br>
    */
   public Boolean getDato( )
   {
      return new Boolean( this.isSelected(  ) );
   }

   /**
    * Hace editable el JCheckBox.<br>
    *
    * @param editable true para hacerlo editable.<br>
    */
   public void hazEditable( boolean editable )
   {
      this.setEnabled( editable );
   }

   /**
    * Devuelve siempre true.<br>
    *
    * @param error Se ignora.<br>
    *
    * @return true.<br>
    */
   public boolean validaDato( StringBuffer error )
   {
      return true;
   }
}
