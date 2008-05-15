package com.chuidiang.editores.primitivos;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;

import javax.swing.ComboBoxModel;
import javax.swing.event.ListDataListener;

import org.apache.log4j.Logger;

import com.chuidiang.editores.comun.InterfaceModeloEnumerado;
import com.chuidiang.editores.utilidades.ListaDeListDataListeners;

/**
 * Clase interna que adapta un Hashtable a ComboBoxModel, de forma que pueda
 * meterse un Hashtable en un JComboBox.<br>
 */
public class AdaptadorHashtableAComboBoxModel<Valor> implements
        InterfaceModeloEnumerado<Object, Valor> {
    // ~ Variables/Inicializadores estaticos
    // -------------------------------------

    private static final String ELEMENTO_NO_ESTA_EN_EL_COMBO_BOX = "{0} no esta en el ComboBox";

    /** Logger para la clase */
    private static Logger log = Logger
            .getLogger(AdaptadorHashtableAComboBoxModel.class);

    // ~ Variables de instancia
    // --------------------------------------------------

    /** El Hashtable que se quiere adaptar a ComboBoxModel */
    private Hashtable<Object, Valor> hash = null;

    /** Lista de claves contenidas en el Hashtable */
    private ArrayList<Object> claves = new ArrayList<Object>();

    /** Clave seleccionada en el ComboBoxModel */
    private Object claveSeleccionado = null;

    /** Lista de listeners que facilita guardarlos y avisarles */
    private ListaDeListDataListeners listeners = new ListaDeListDataListeners();

    // ~ Constructores
    // -----------------------------------------------------------

    /**
     * Crea un nuevo objeto de la clase AdaptadorHashtableAComboBoxModel.<br>
     * 
     * @param hash
     *            Hasttable que se quiere adaptar a ComboBoxModel.<br>
     */
    public AdaptadorHashtableAComboBoxModel(Hashtable<Object, Valor> hash) {
        this.setModel(hash);
    }

    /**
     * Crea un nuevo objeto de la clase AdaptadorHashtableAComboBoxModel.<br>
     * Este constructor se hace por comodidad, para que el EditorComboBox,
     * independientemente de que modelo se le pase, meneje un
     * AdaptadorHashtableAComboBoxModel. Construye un Hashtable con el
     * ComboBoxModel
     * 
     * @param combo
     *            Construye un Hashtable con el ComboBoxModel.<br>
     */
    public AdaptadorHashtableAComboBoxModel(ComboBoxModel combo) {
        this.setModel(combo);
    }

    // ~ Metodos
    // -----------------------------------------------------------------

    /**
     * Se le pasa la clave del item que se desea seleccionar en el ComboBox.<br>
     * Si la clave es null, se deseleccionan todos los elementos del ComboBox.
     * 
     * @param claveSeleccionado
     *            Clave del elemento que se quiere seleccionar.<br>
     */
    public void setClaveSeleccionado(Object claveSeleccionado) {
        // Si la clave no es null y no esta en la lista de claves, no se hace
        // nada.
        if (claveSeleccionado != null) {
            if (existeClave(claveSeleccionado) == false) {
                log.warn(MessageFormat.format(ELEMENTO_NO_ESTA_EN_EL_COMBO_BOX,
                        claveSeleccionado.toString()));
                return;
            }
        }

        // Tanto si la clave seleccionada es null como si es una clave valida,
        // se selecciona.
        this.claveSeleccionado = claveSeleccionado;
        this.listeners.fireContentsChanged(this, -1, -1);
    }

    /**
     * Devuelve la clave seleccionada en el JComboBox.
     * 
     * @return Returns La clave del elemento seleccionado en el JComboBox.
     */
    public Object getClaveSeleccionado() {
        return claveSeleccionado;
    }

    /**
     * Devuelve el elemento en la posicion indicada.<br>
     * Se mira en el ArrayList de claves la clave en la posicion indicada y se
     * devuelve el elemento que corresponde a dicha clave.
     * 
     * @param index
     *            Posicion del elemento.<br>
     * 
     * @return El elemento.<br>
     */
    public Object getElementAt(int index) {
        return (hash.get(claves.get(index)));
    }

    /**
     * Se le pasa un nuevo ComboBoxModel.<br>
     * Olvida el antiguo y guarda este.
     * 
     * @param modelo
     *            Un ComboBoxModel.<br>
     */
    public void setModel(ComboBoxModel modelo) {
        this.hash = new Hashtable<Object, Valor>();

        for (int i = 0; i < modelo.getSize(); i++) {
            this.hash.put(new Integer(i), (Valor) modelo.getElementAt(i));
            this.claves.add(new Integer(i));
        }
    }

    /**
     * COMENTARIO.<br>
     * 
     * @param modelo
     *            COMENTARIO.<br>
     */
    public void setModel(Hashtable<Object, Valor> modelo) {
        this.hash = modelo;

        // Se rellena el ArrayList con las claves del hash.
        Enumeration<Object> clave = this.hash.keys();

        while (clave.hasMoreElements()) {
            claves.add(clave.nextElement());
        }
    }

    /**
     * Selecciona en el JComboBox el item indicado.<br>
     * Si el item es null, hace que el ComboBox no tenga ningun item
     * seleccionado Si el item no esta en el modelo, el ComboBox permanece
     * inalterado
     * 
     * @param anItem
     *            Valor del item que se quiere seleccionar.<br>
     */
    public void setSelectedItem(Object anItem) {
        if (anItem == null) {
            this.setClaveSeleccionado(null);

            return;
        }

        for (int i = 0; i < this.claves.size(); i++) {
            if (hash.get(claves.get(i)) == anItem) {
                this.setClaveSeleccionado(claves.get(i));

                return;
            }
        }

        log.warn(MessageFormat.format(ELEMENTO_NO_ESTA_EN_EL_COMBO_BOX, anItem
                .toString()));
    }

    /**
     * Devuelve el valor del item seleccionado.<br>
     * Si no hay ningun item seleccionado devuelve null.
     * 
     * @return Valor del item seleccionado.<br>
     */
    public Object getSelectedItem() {
        if (this.claveSeleccionado == null) {
            return null;
        }

        return hash.get(this.claveSeleccionado);
    }

    /**
     * Devuelve el numero de elementos en el modelo del ComboBox.<br>
     * 
     * @return Numero de elementos.<br>
     */
    public int getSize() {
        return claves.size();
    }

    /**
     * Anade el ListDataListener a la lista de suscriptores..<br>
     * 
     * @param l
     *            ListDataListener que se desea anadir.<br>
     */
    public void addListDataListener(ListDataListener l) {
        listeners.addListDataListener(l);
    }

    /**
     * Devueleve el elemento cuya clave se pasa.<br>
     * Si la clave es null, devuelve null. Si la clave no existe en la tabla de
     * claves, devuelve null.
     * 
     * @param key
     *            La clave del elemento que se quiere obtener.<br>
     * 
     * @return El elemento.<br>
     */
    public Valor get(Object key) {
        if (key == null) {
            return null;
        }

        if (this.existeClave(key) == false) {
            log.warn(MessageFormat.format(ELEMENTO_NO_ESTA_EN_EL_COMBO_BOX, key
                    .toString()));
        }

        return this.hash.get(key);
    }

    /**
     * Elimina el ListDataListener que se le pasa de la lista de suscriptores.<br>
     * 
     * @param l
     *            ListDataListener que se desea eliminar de la lista.<br>
     */
    public void removeListDataListener(ListDataListener l) {
        listeners.removeListDataListener(l);
    }

    /**
     * Comprueba si la clave que se le pasa existe o no en la lista de claves.<br>
     * Devuelve true si la clave existe. false en caso contrario.
     * 
     * @param claveSeleccionado
     *            Clave que se quiere comprobar si esta en el ComboBox.<br>
     * 
     * @return true si la clave existe en el ComboBox.<br>
     */
    private boolean existeClave(Object claveSeleccionado) {
        for (int i = 0; i < this.claves.size(); i++) {
            if (claves.get(i) == claveSeleccionado) {
                return true;
            }
        }

        return false;
    }
}
