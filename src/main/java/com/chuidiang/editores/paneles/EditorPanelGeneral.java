package com.chuidiang.editores.paneles;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.LinkedList;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import org.apache.log4j.Logger;

import com.chuidiang.editores.comun.InterfaceEdicionDatos;

/**
 * Panel que admite y coloca automaticamente varios editores.<br>
 * Al anadir un editor que implemente InterfaceEdicionDatos, se pasa tambien una
 * clave y una etiqueta. El panel asociara la clave con el editor y lo mostrara
 * precedido de la etiqueta en cuestion.<br>
 * El panel en su setDato() y getDato() admite Hashtables. Cada dato del
 * Hashtable ira al editor cuya clave coincida. Si un editor no tiene ninguna
 * clave asociada, se le pasara el Hashtable entero.<br>
 */
public abstract class EditorPanelGeneral<Tipo> extends JPanel implements
        InterfaceEdicionDatos<Tipo>, InterfaceEditorComponentesObligatorios {
    // ~ Variables/Inicializadores estaticos
    // -------------------------------------

    /** Comment for <code>serialVersionUID</code> */
    private static final long serialVersionUID = 3617860780561937715L;

    // ~ Variables de instancia
    // --------------------------------------------------

    /** Atributo para las gridBagConstraints de los editores. */
    protected GridBagConstraints gridBagConstraints = new GridBagConstraints();

    /**
     * Atributo en el cual se guardan las claves y los editores asociados a las
     * mismas.
     */
    protected Hashtable<String, Component> hashClaves;

    /** Editores que no tienen clave */
    protected LinkedList<Component> editoresSinClave = new LinkedList<Component>();

    /**
     * Atributo en el cual se guardan los datos que llegan a la ventana a traves
     * del metodo tomaDatos(Object).
     */
    protected Hashtable<String, Object> hashDatos;

    /**
     * Atributo que indica si el editor que se inserta debe ocupar toda la fila.
     */
    protected boolean editorGrande = false;

    /**
     * Atributo que guarda el numero de columna en el cual esta el ultimo editor
     * insertado en la ventana. Sirve para colocar los editores en la ventana.
     */
    protected int contadorColumna = 0;

    /**
     * Atributo que guarda el numero de fila en el cual esta el ultimo editor
     * insertado en la ventana. Sirve para colocar los editores en la ventana.
     */
    protected int contadorFila = 0;

    /**
     * Atributo que guarda el numero de la ultima columna, sirve para colocar
     * los editores en la ventana.
     */
    protected int ultimaColumna;

    /** Editores que son editables por el operador */
    protected LinkedList<Component> editoresEditables = new LinkedList<Component>();

    /** Editores que son obligatorios de rellenar por parte del operador */
    protected LinkedList<Component> editoresObligatorios = new LinkedList<Component>();

    /**
     * Clase encargada de cambiar de color los componentes obligatorios de
     * rellenar
     */
    protected InterfaceComponentesObligatorios gestor = null;

    /** Logger para la clase */
    private Logger log = Logger.getLogger(EditorPanelGeneral.class);

    // ~ Constructores
    // -----------------------------------------------------------

    /**
     * Instancia esta clase y pone todos los editores que se le pasen en una
     * unica columna.
     */
    public EditorPanelGeneral() {
        this(1);
    }

    /**
     * Crea un panel vacio con tantas columnas como se le indiquen.
     * numeroColumnas debe valer al menos 1 y cualquier valor inferior se
     * considerara como 1.
     * 
     * @param numeroColumnas
     *            Numero de columnas que se quieren en el panel.<br>
     */
    public EditorPanelGeneral(int numeroColumnas) {
        super();

        ultimaColumna = 2 * numeroColumnas;

        setLayout(new GridBagLayout());

        /**
         * anhadeEditor( "", "", new JLabel( ) ); sumaFila( );
         */
    }

    // ~ Metodos
    // -----------------------------------------------------------------

    /**
     * Anhade el editor que se le pasa en la siguiente posicion. La clave es la
     * clave del Hashtable correspondiente al dato que se le debe pasar al
     * editor. Cuando se llame al tomaDatos() de esta clase, se extraera del
     * Hashtable el valor correspondiente a dicha clave y se le pasara al
     * editor. Si el editor es null, se dejara un hueco en el panel y se pasar�
     * a la siguiente posicion. Si el editor no implementa
     * InterfaceEdicionDatos, simplemente se dibujara, pero no se le pasara ni
     * se le pedira ningun dato. El texto es una etiqueta que se colocara
     * delante del editor que se pasa. Si es null, se colocara el editor, pero
     * sin etiqueta delante. Si la clave es null, se pasara al editor el
     * Hashtable completo que se le pasa en el tomaDatos()
     * 
     * @param clave
     *            Clave para el editor.<br>
     * @param texto
     *            Etiqueta que se ponde delante del editor.<br>
     * @param editor
     *            El editor.<br>
     */
    public void anhadeEditor(String clave, String texto, Component editor) {
        editorGrande = false;

        if ((clave == null) && (texto == null) && (editor == null)) {
            log.error("Todos los parametros de entrada son null.");

            return;
        }

        if ((clave != null) && (editor != null)) {
            anhadeEditorConClave(clave, texto, editor);

            return;
        }

        if ((clave == null) && (editor != null)) {
            anhadeEditorSinClave(texto, editor);

            return;
        }

        if (editor == null) {
            saltaPosicionSiguiente();

            return;
        }
    }

    /**
     * Anhade el editor que se le pasa en la siguiente posicion. La clave es la
     * clave del Hashtable correspondiente al dato que se le debe pasar al
     * editor. Cuando se llame al tomaDatos() de esta clase, se extraera del
     * Hashtable el valor correspondiente a dicha clave y se le pasara al
     * editor. Si el editor es null, se dejara un hueco en el panel y se pasar�
     * a la siguiente posicion. Si el editor no implementa
     * InterfaceEdicionDatos, simplemente se dibujara, pero no se le pasara ni
     * se le pedira ningun dato. El texto es una etiqueta que se colocara
     * delante del editor que se pasa. Si es null, se colocara el editor, pero
     * sin etiqueta delante. Si la clave es null, se pasara al editor el
     * Hashtable completo que se le pasa en el tomaDatos()
     * 
     * @param clave
     *            Clave del editor.<br>
     * @param texto
     *            Etiqueta para el editor.<br>
     * @param editor
     *            El editor.<br>
     * @param editorGrande
     *            Si debe ocupar todas las columnas del panel.<br>
     */
    public void anhadeEditor(String clave, String texto, Component editor,
            boolean editorGrande) {
        if (!editorGrande) {
            anhadeEditor(clave, texto, editor);
        }

        this.editorGrande = editorGrande;
        sumaFila();

        if ((clave == null) && (texto == null) && (editor == null)) {
            log.warn("Todos los parametros de entrada son null");

            return;
        }

        if ((clave != null) && (editor != null)) {
            anhadeEditorConClave(clave, texto, editor);
        }

        if ((clave == null) && (editor != null)) {
            anhadeEditorSinClave(texto, editor);
        }

        if (editor == null) {
            saltaPosicionSiguiente();
        }

        sumaFila();
    }

    /**
     * Anhade el editor que se le pasa en la siguiente posicion. El campo
     * obligatorio indica si dicho editor es de relleno obligatorio, con lo que
     * sus campos se dibujaran de otro color. El campo editable de momento no
     * esta implementado. El resto de los campos se pasan tal cual al metodo
     * anhadeEditor (clave, texto, editor)
     * 
     * @param clave
     *            Clave del editor.<br>
     * @param texto
     *            Etiqueta para el editor.<br>
     * @param obligatorio
     *            Si el editor es obligatorio de rellenar.<br>
     * @param editable
     *            Si el editor es editable o debe estar deshabilitado.<br>
     * @param editor
     *            El editor.<br>
     */
    public void anhadeEditor(String clave, String texto, boolean obligatorio,
            boolean editable, Component editor) {
        // Si el editor anadido es de relleno obligatorio, se anade a la lista
        // de editores obligatorios y se le pasa el gestor de componentes
        // obligatorios (que cambia su color).
        if (obligatorio) {
            if (editor instanceof InterfaceEditorComponentesObligatorios) {
                editoresObligatorios.add(editor);

                ((InterfaceEditorComponentesObligatorios) editor)
                        .tomaGestorComponentesObligatorios(null);

                // Se le pasa el nuevo gestor.
                if (this.gestor != null) {
                    ((InterfaceEditorComponentesObligatorios) editor)
                            .tomaGestorComponentesObligatorios(this.gestor);
                }
            }
        }

        // Si el editor se puede modificar, se anade en la lista de editores
        // modificables por el operador.
        if (editable && (editor != null)) {
            editoresEditables.add(editor);
        }

        // Si el editor no es editable, no se anade en la lista y ademas
        // se deshabilita.
        if ((!editable) && (editor != null)) {
            if (editor instanceof InterfaceEdicionDatos) {
                ((InterfaceEdicionDatos) editor).hazEditable(false);
            } else {
                editor.setEnabled(false);
            }
        }

        this.anhadeEditor(clave, texto, editor);
    }

    /**
     * Devuelve el editor asociado a la clave que se le pasa.
     * 
     * @param clave
     *            La clave asociada al editor
     * 
     * @return El editor
     */
    public Component dameEditorConClave(Object clave) {
        if (this.hashClaves != null) {
            return (Component) this.hashClaves.get(clave);
        }

        return null;
    }

    /**
     * Hace la ventana editable o no, seg�n el boolean. Llama al hazEditable()
     * de todos los editores que contiene en su interior.
     * 
     * @param editable
     *            Si debe ser o no editable.<br>
     */
    public void hazEditable(boolean editable) {
        int i;

        for (i = 0; i < editoresEditables.size(); i++) {
            Object aux = editoresEditables.get(i);

            if (aux instanceof InterfaceEdicionDatos) {
                ((InterfaceEdicionDatos) aux).hazEditable(editable);
            } else if (aux instanceof Component) {
                ((Component) aux).setEnabled(editable);
            }
        }
    }

    /**
     * Pasa el gestor de componentes obligatorios a todos los editores que son
     * obligatorios. El gestor es el encargado de hacer que los componentes
     * obligatorios se pinten de otro color. Si el gestor es null, se eliminan
     * los componentes del ultimo gestor que se tuviera (recuperar�n su color
     * por defecto).
     * 
     * @param gestor
     *            Clase que decide qu� hacer con los componentes obligatorios de
     *            rellenar (cambio de color, deshabilitar bot�n de aceptar,
     *            etc).<br>
     */
    public void tomaGestorComponentesObligatorios(
            InterfaceComponentesObligatorios gestor) {
        int i;

        // Se recorren todos los editores que tienes campos de rellenado
        // obligatorio
        for (i = 0; i < editoresObligatorios.size(); i++) {
            InterfaceEditorComponentesObligatorios aux;
            aux = (InterfaceEditorComponentesObligatorios) editoresObligatorios
                    .get(i);

            // Se pasa primero null para que se quiten los componentes del
            // antiguo
            // gestor y luego se pasa el actual si no es null.
            aux.tomaGestorComponentesObligatorios(null);

            // Se les pasa el nuevo gestor.
            if (gestor != null) {
                aux.tomaGestorComponentesObligatorios(gestor);
            }
        }

        this.gestor = gestor;
    }

    /**
     * Verifica que los datos escritos por el operador son correctos. Llama a
     * todos los validarDatos() de los editores que contiene. Si alguno le
     * devuelve false, devuelve false. Devuelve true si todos lo devuelven true.
     * 
     * @param error
     *            Se rellena con un texto descriptivo del error.<br>
     * 
     * @return true si los datos introducidos por el usuario son correctos.<br>
     */
    public boolean validaDato(StringBuffer error) {
        Object clave = null;

        Enumeration claves = hashClaves.keys();

        while (claves.hasMoreElements()) {
            clave = claves.nextElement();

            Object c = hashClaves.get(clave);

            /*
             * if( ( (String)clave ).startsWith( SINCLAVE ) ) { if( c instanceof
             * InterfaceEdicionDatos ) { if( ( (InterfaceEdicionDatos)c
             * ).validaDato( error ) == false ) { return false; } } }
             */
            if (c instanceof InterfaceEdicionDatos) {
                if (((InterfaceEdicionDatos) c).validaDato(error) == false) {
                    return false;
                }
            }
        }

        for (int i = 0; i < this.editoresSinClave.size(); i++) {
            Object c = this.editoresSinClave.get(i);

            if (c instanceof InterfaceEdicionDatos) {
                boolean resultado = ((InterfaceEdicionDatos) c)
                        .validaDato(error);

                if (!resultado) {
                    return resultado;
                }
            }
        }

        return true;
    }

    /**
     * Metodo que recibe una clave y un componente e inserta los mismos en el
     * hastable de claves.
     * 
     * @param clave
     *            Clave para el editor.<br>
     * @param editor
     *            El editor.<br>
     */
    protected void anhadeClave(String clave, Component editor) {
        if (hashClaves == null) {
            hashClaves = new Hashtable<String, Component>();
        }

        hashClaves.put(clave, editor);
    }

    /**
     * Metodo que recibe una clave, una etiqueta para un Label y un componente.
     * Anade la etiqueta y el componente a la ventana. Anade la clave al
     * hashtable de Claves.
     * 
     * @param clave
     *            Clave para el editor.<br>
     * @param texto
     *            Etiqueta para el editor.<br>
     * @param editor
     *            El editor.<br>
     */
    protected void anhadeEditorConClave(String clave, String texto,
            Component editor) {
        anhadeEditorEnPanel(editor, texto);

        anhadeClave(clave, editor);
    }

    /**
     * Metodo que recibe un componente y una etiqueta. Inserta en el panel un
     * label con la etiqueta y un componente.
     * 
     * @param editor
     *            Editor.<br>
     * @param etiqueta
     *            Etiqueta para el editor.<br>
     */
    protected void anhadeEditorEnPanel(Component editor, String etiqueta) {
        if (editor == null) {
            sumaColumna();
            sumaColumna();

            return;
        }

        if (!editorGrande) {
            if (etiqueta == null) {
                sumaColumna();
            } else {
                add(this.dameLabel(etiqueta), dameConstraints(contadorFila,
                        contadorColumna, 0.0));

                sumaColumna();
            }
        } else {
            if (editor instanceof JComponent && (etiqueta != null)) {
                ((JComponent) editor).setBorder(new TitledBorder(etiqueta));
            }
        }

        add(editor, dameConstraints(contadorFila, contadorColumna, 1.0));

        sumaColumna();
    }

    /**
     * Metodo que recibe una etiqueta para un Label y un componente. A�ade la
     * etiqueta y el componente a la ventana. A�ade una clave generica para este
     * componente.
     * 
     * @param texto
     *            Etiqueta para el editor.<br>
     * @param editor
     *            Editor.<br>
     */
    protected void anhadeEditorSinClave(String texto, Component editor) {
        anhadeEditorEnPanel(editor, texto);

        this.editoresSinClave.add(editor);

        /*
         * StringBuffer sinClave = new StringBuffer( SINCLAVE );
         * 
         * sinClave.append( ++contadorSinClave );
         * 
         * anhadeClave( sinClave.toString( ), editor );
         */
    }

    /**
     * Metodo que recibe los parametros fila y columna. Devuelve un
     * GridBagConstraints con los datos de posicionamiento en el panel.
     * 
     * @param fila
     *            fila en la que se quiere insertar el editor.<br>
     * @param columna
     *            columna en la que se quiere insertar el editor.<br>
     * @param weightx
     *            peso en x para el editor.<br>
     * 
     * @return El GridBagConstraints para a�adir el editor donde se ha indicado
     *         en los par�metros.<br>
     */
    protected GridBagConstraints dameConstraints(int fila, int columna,
            double weightx) {
        gridBagConstraints.gridx = columna;
        gridBagConstraints.gridy = fila;
        gridBagConstraints.gridheight = 1;

        if (editorGrande) {
            gridBagConstraints.gridwidth = GridBagConstraints.REMAINDER;
            gridBagConstraints.fill = GridBagConstraints.BOTH;
            gridBagConstraints.weightx = 1.0;
            gridBagConstraints.weighty = 1.0;
            gridBagConstraints.insets.right = 4;
        } else {
            gridBagConstraints.gridwidth = 1;

            if (weightx == 1.0) {
                gridBagConstraints.fill = GridBagConstraints.NONE;
                gridBagConstraints.anchor = GridBagConstraints.WEST;
                gridBagConstraints.insets.right = 10;
            } else {
                gridBagConstraints.fill = GridBagConstraints.NONE;
                gridBagConstraints.anchor = GridBagConstraints.WEST;
                gridBagConstraints.insets.right = 4;
            }

            gridBagConstraints.weighty = 0.0;
            gridBagConstraints.weightx = 0.0; // weightx;
        }

        gridBagConstraints.insets.left = 2;
        gridBagConstraints.insets.bottom = 4;
        gridBagConstraints.insets.top = 2;

        return gridBagConstraints;
    }

    /**
     * Metodo que devuelve un JLabel con la etiqueta que se pasa como parametro.
     * Si la etiqueta es null, devuelve null.
     * 
     * @param etiqueta
     *            Etiqueta del editor.<br>
     * 
     * @return Componente asociado al editor.<br>
     */
    protected Component dameLabel(String etiqueta) {
        if (etiqueta == null) {
            return null;
        }

        JLabel label = new JLabel(etiqueta);

        label.setHorizontalTextPosition(SwingConstants.LEFT);

        return label;
    }

    /**
     * Borra los editores asociados a las claves que se pasan en la lista.<br>
     * 
     * @param lista
     *            Lista de claves.<br>
     */
    protected void borraContenidoEditores(java.util.List<String> lista) {
        for (int i = 0; i < lista.size(); i++) {
            Object editor = hashClaves.get(lista.get(i));

            if ((editor != null) && (editor instanceof InterfaceEdicionDatos)) {
                ((InterfaceEdicionDatos) editor).setDato(null);

                hashDatos.put(lista.get(i), ((InterfaceEdicionDatos) editor)
                        .getDato());
            }
        }
    }

    /**
     * Metodo que reparte los nulos por los editores, para inicializarlos.
     */
    protected void borraContenidoDeTodosLosEditores() {
        Object clave = null;

        Enumeration<String> claves = hashClaves.keys();

        while (claves.hasMoreElements()) {
            clave = claves.nextElement();

            Object c = hashClaves.get(clave);

            if (c instanceof InterfaceEdicionDatos) {
                ((InterfaceEdicionDatos) c).setDato(null);
            }
        }

        for (int i = 0; i < this.editoresSinClave.size(); i++) {
            Object c = this.editoresSinClave.get(i);

            if (c instanceof InterfaceEdicionDatos) {
                ((InterfaceEdicionDatos) c).setDato(null);
            }
        }
    }

    /**
     * Metodo que sirve para saltarse una posicion en la ventana y que el
     * siguiente componente que se inserte sea una posicion despues.
     */
    protected void saltaPosicionSiguiente() {
        anhadeEditorEnPanel(null, null);
    }

    /**
     * Metodo que se llama cada vez que se anade un componente al panel para
     * saber donde hay que colocar el siguiente.
     */
    protected void sumaColumna() {
        if (contadorColumna == (ultimaColumna - 1)) {
            contadorColumna = 0;
            contadorFila++;
        } else {
            contadorColumna++;
        }
    }

    /**
     * Metodo
     */
    /**
     * Metodo que se llama cada vez que se anade un componente al panel para
     * saber donde hay que colocar el siguiente.
     */
    protected void sumaFila() {
        contadorColumna = 0;
        contadorFila++;
    }
}
