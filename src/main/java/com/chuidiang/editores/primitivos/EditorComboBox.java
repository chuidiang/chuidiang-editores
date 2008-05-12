package com.chuidiang.editores.primitivos;

import java.util.Hashtable;

import javax.swing.ComboBoxModel;
import javax.swing.JComboBox;

import org.apache.log4j.Logger;

import com.chuidiang.editores.comun.InterfaceEdicionDatos;
import com.chuidiang.editores.comun.InterfaceModeloEnumerado;



/**
 * Editor JComboBox.<br>
 * Implementa InterfaceEdicionDatos y admite como modelo un Hashtable, de
 * forma que los items seleccionados, en vez de por un indice, se pueden
 * identificar por cualquier Object como clave.
 */
public class EditorComboBox<Clave, Valor>
        extends JComboBox
        implements InterfaceEdicionDatos<Clave>
{
   //~ Variables/Inicializadores estaticos -------------------------------------

	/**
	 * Construye una instancia de EditorComboBox.
	 * Garantiza que el modelo de datos que se usa es un 
	 * AdaptadorHashtableAComboBoxModel
	 */
	public EditorComboBox()
	{
		super(new AdaptadorHashtableAComboBoxModel(new Hashtable<Clave,Valor>()));
	}
	
	/** Construye una instancia de esta clase usando como modelo el Hashtable
	 * que se le pasa.
	 * @param modelo Modelo del JComboBox.
	 */
	public EditorComboBox (Hashtable<Clave,Valor> modelo)
	{
		super (new AdaptadorHashtableAComboBoxModel(modelo));
	}
	
	/**
	 * Construye una instancia de esta clase usando como modelo el ComboBoxModel
	 * que se le pasa.
	 * 
	 * @param modelo Modelo del JComboBox
	 */
	public EditorComboBox (ComboBoxModel modelo)
	{
		super();
		this.setModel(modelo);
	}
	
   /** Comment for <code>serialVersionUID</code> */
   private static final long serialVersionUID = 3689069547378586418L;

   //~ Metodos -----------------------------------------------------------------

   /**
    * Se le pasa la clave del elemento que se quiere ver seleccionado en el
    * JComboBox.<br>
    *
    * @param clave Clave del elemento que se quiere ver seleccionado.<br>
    */
   public void setDato( Clave clave )
   {
      ComboBoxModel modelo = super.getModel(  );

      if( modelo instanceof InterfaceModeloEnumerado )
      {
         InterfaceModeloEnumerado<Clave,Valor> hash = (InterfaceModeloEnumerado<Clave,Valor>)modelo;
         Valor elemento = hash.get( clave );

         // Si dato es null, hay que vaciar el ComboBox.
         // Si el elemento no es null, hay que seleccionarlo
         if( ( clave == null ) || ( elemento != null ) )
         {
            modelo.setSelectedItem( elemento );
         }
      }
   }

   /**
    * Devuelve la clave del elemento seleccionado.<br>
    * Si el modelo es un Hashtable, devuelve la clave. Si el modelo es un
    * ComboBoxModel, devuelve un Integer con la posici�n del elemento.
    *
    * @param dato Se ignora el campo.<br>
    *
    * @return Devuelve la clave del elemento seleccionado.<br>
    */
   public Clave getDato(  )
   {
      ComboBoxModel modelo = super.getModel(  );

      if( modelo instanceof InterfaceModeloEnumerado )
      {
         return ( (InterfaceModeloEnumerado<Clave,Valor>)modelo ).getClaveSeleccionado(  );
      }

      return null;
   }

   /**
    * Admite el Hashtable como modelo del JComboBox.<br>
    * Situa los valores del Hashtable en el men�. Los m�todos getDato() y
    * setDato() admitir�n a partir de este momento la clave del Hashtable
    * correspondiente al valor seleccionado.
    *
    * @param aModel Un Hashtable que hace de modelo del ComboBox.<br>
    */
   public void setModel( Hashtable<Clave,Valor> aModel )
   {
      super.setModel( new AdaptadorHashtableAComboBoxModel( aModel ) );
   }

   /**
    * Admite un ComboBoxModel como modelo del JComboBox.<br>
    * Los m�todos setDato() y getDato() admiten a partir de ahora un Integer
    * correspondiente al indice del elemento seleccionado en el ComboBoxModel.
    *
    * @param aModel El modelo para el JComboBox.<br>
    */
   public void setModel( ComboBoxModel aModel )
   {
   	if (aModel instanceof AdaptadorHashtableAComboBoxModel)
   		super.setModel(aModel);
   	else
      super.setModel( new AdaptadorHashtableAComboBoxModel( aModel ) );
   }

   /**
    * Hace o no editable el JComboBox.<br>
    *
    * @param editable Si es true, se hace editable.<br>
    */
   public void hazEditable( boolean editable )
   {
      super.setEditable( editable );
   }

   /**
    * Devuelve siempre true.<br>
    *
    * @param error Ignora este campo.<br>
    *
    * @return true.<br>
    */
   public boolean validaDato( StringBuffer error )
   {
      return true;
   }
}


