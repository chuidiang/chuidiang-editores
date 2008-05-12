package com.chuidiang.editores.utilidades;

import javax.swing.AbstractListModel;


/**
 * Clase que lleva una lista de ListDataListeners y proporciona los m�todos
 * para avisarles.<br>
 * Hereda de AbstractListModel, implementa los m�todos abstractos por defecto
 * y hace p�blicos los m�todos fire.
 */
public class ListaDeListDataListeners
        extends AbstractListModel
{
   //~ Variables/Inicializadores estaticos -------------------------------------

   /** Comment for <code>serialVersionUID</code> */
   private static final long serialVersionUID = 3689069547378586418L;

   //~ Metodos -----------------------------------------------------------------

   /**
    * M�todo implementado para que la clase no sea abstracta.<br>
    * Devuelve siempre null
    *
    * @param index se ignora.<br>
    *
    * @return null.<br>
    */
   public Object getElementAt( int index )
   {
      return null;
   }

   /**
    * M�todo implementado para que la clase no sea abstracta.<br>
    * Devuelve siempre 0
    *
    * @return 0.<br>
    */
   public int getSize(  )
   {
      return 0;
   }

   /**
    * Avisa a los suscriptores de un cambio en los datos.<br>
    *
    * @param source Modelo de datos en el que se ha producido el cambio.<br>
    * @param index0 Primera fila del modelo en la que se ha producido un cambio.<br>
    * @param index1 Ultima fila del modelo en la que se ha producido un cambio.<br>
    */
   public void fireContentsChanged( 
      Object source,
      int index0,
      int index1 )
   {
      super.fireContentsChanged( 
         source,
         index0,
         index1 );
   }

   /**
    * Avisa a los suscriptores de que se han a�adido filas en el modelo de datos.<br>
    *
    * @param source Modelo de datos en el que se ha producido el cambio.<br>
    * @param index0 Primera fila a�adida.<br>
    * @param index1 Ultima fila a�adida.<br>
    */
   public void fireIntervalAdded( 
      Object source,
      int index0,
      int index1 )
   {
      super.fireIntervalAdded( 
         source,
         index0,
         index1 );
   }

   /**
    * Avisa a los suscriptores de que se han borrado elementos en el modelo
    * de datos.<br>
    *
    * @param source Modelo de datos en el que se han borrado filas.<br>
    * @param index0 primera fila borrada.<br>
    * @param index1 Ultima fila borrada.<br>
    */
   public void fireIntervalRemoved( 
      Object source,
      int index0,
      int index1 )
   {
      super.fireIntervalRemoved( 
         source,
         index0,
         index1 );
   }
}
