package com.chuidiang.editores.comun;

import java.util.Hashtable;

import javax.swing.ComboBoxModel;


/**
 * Interface comun para los modelos de enumerados.<br>
 */
public interface InterfaceModeloEnumerado<Clave, Valor>
        extends ComboBoxModel
{
   //~ Metodos -----------------------------------------------------------------

   /**
    * Fija la clave seleccionada.<br>
    * El objeto correspondiente a esta clave ser� el que se vea en el ComboBox
    * Si es null, el ComboBox se vacia.
    *
    * @param claveSeleccionado Clave seleecionada o null.<br>
    */
   public void setClaveSeleccionado( Clave claveSeleccionado );

   /**
    * Devuelve la clave seleccionada en el JComboBox.
    *
    * @return Returns La clave del elemento seleccionado en el JComboBox.
    */
   public Clave getClaveSeleccionado(  );

   /**
    * Se le pasa un modelo en forma de ComboBoxModel y lo guarda.<br>
    * Las claves ser�n Integer y ser�n las que se devuelvan y usen en
    * setClaveSeleccionado() y getClaveSeleccionado(). Los valores ser�n los
    * que se obtengan con el m�todo get() y lo que se vea en el ComboBox.
    *
    * @param modelo Un modelo en forma de ComboBoxModel.<br>
    */
   public void setModel( ComboBoxModel modelo );

   /**
    * Se le pasa un modelo en forma de Hastable y lo guarda.<br>
    * Las claves ser�n las que se devuelvan y usen en setClaveSeleccionado() y
    * getClaveSeleccionado(). Los valores ser�n los que se obtengan con el
    * m�todo get() y lo que se vea en el ComboBox.
    *
    * @param modelo Un modelo en forma de Hastable.<br>
    */
   public void setModel( Hashtable<Clave, Valor> modelo );

   /**
    * Devueleve el elemento cuya clave se pasa.<br>
    * Si la clave es null, devuelve null. Si la clave no existe en la tabla de
    * claves, devuelve null.
    *
    * @param key La clave del elemento que se quiere obtener.<br>
    *
    * @return El elemento.<br>
    */
   public Valor get( Clave key );
}
