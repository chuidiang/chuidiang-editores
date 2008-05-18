package com.chuidiang.editores.paneles;

import java.util.Enumeration;
import java.util.Hashtable;

import org.apache.log4j.Logger;

import com.chuidiang.editores.comun.InterfaceEdicionDatos;
import com.chuidiang.editores.utilidades.HashtableUtil;

public class EditorPanelHashtable extends EditorPanelGeneral<Hashtable<String,Object>> {
    /** Logger para la clase */
    private Logger log = Logger.getLogger(EditorPanelHashtable.class);

    /**
     * Presenta el dato que se le pasa en pantalla. El dato recibido debe ser un
     * Hashtable. Reparte cada uno de los valores contenidos en el Hashtable a
     * los diversos editores que tiene.
     * 
     * @param elemento
     *            El dato que se va mostrar en los editores.<br>
     */
    public void setDato(Hashtable<String,Object> elemento) {
        // Si el dato es null, vacia el editor
        if (elemento == null) {
            this.borraContenidoDeTodosLosEditores();

            return;
        }

        // Vacia el editor
        this.borraContenidoDeTodosLosEditores();

        // Copia el Hashtable original
        if (this.hashDatos == null) {
            this.hashDatos = new Hashtable();
        }

        this.hashDatos.clear();
        HashtableUtil.copiaHashtable(this.hashDatos, (Hashtable) elemento);

        reparteDatos();
    }
    /**
     * Metodo que recoge los datos de todas los editores de la ventana y los
     * devuelve en el Hashtable.
     * 
     * @param dato
     *            COMENTARIO.<br>
     * 
     * @return Hashtable con los campos del editor.<br>
     */
    protected Hashtable<String,Object> recogeDatos() {
        Hashtable<String,Object> hash = new Hashtable<String,Object>();

        String clave = null;

        Enumeration<String> claves = this.hashClaves.keys();

        while (claves.hasMoreElements()) {
            clave = claves.nextElement();

            Object c = hashClaves.get(clave);

            // Caso de un editor completo que no tiene asociada clave.

            /*
             * if( ( (String)clave ).startsWith( SINCLAVE ) ) {
             * 
             * continue; }
             */
            if (c instanceof InterfaceEdicionDatos) {
                Object datosClave = ((InterfaceEdicionDatos) c).getDato();

                if (datosClave != null) {
                    hash.put(clave, datosClave);
                }
            }
        }

        for (int i = 0; i < this.editoresSinClave.size(); i++) {
            Object c = this.editoresSinClave.get(i);

            if (c instanceof InterfaceEdicionDatos) {
                Object datoEditor = ((InterfaceEdicionDatos) c).getDato();

                if (datoEditor instanceof Hashtable) {
                    HashtableUtil.copiaHashtable(hash, (Hashtable) datoEditor);
                }
            }
        }

        return hash;
    }
    /**
     * Metodo que reparte los datos por los editores.
     */
    protected void reparteDatos() {
        Object clave = null;

        Enumeration claves = hashClaves.keys();

        while (claves.hasMoreElements()) {
            clave = claves.nextElement();

            Object c = hashClaves.get(clave);

            if ((c instanceof InterfaceEdicionDatos)
                    && hashDatos.containsKey(clave)) {
                ((InterfaceEdicionDatos) c).setDato(hashDatos.get(clave));
            }
        }

        for (int i = 0; i < this.editoresSinClave.size(); i++) {
            Object c = this.editoresSinClave.get(i);

            if (c instanceof InterfaceEdicionDatos) {
                ((InterfaceEdicionDatos) c).setDato(hashDatos);
            }
        }
    }
    /**
     * Recoge los datos de la ventana y los mete en un Hashtable.<br>
     * Si el dato que se le pasa es un Hastable, le a�ade los datos recogidos
     * del editor. NO vacia el Hashstable. Tambi�n devuelve el mismo Hashtable
     * en el return.<br>
     * Si el dato que se le pasa no es un Hashtable, crea uno nuevo, lo rellena
     * con los datos y lo devuelve.
     * 
     * @return Devuelve un Hashtable con los campos recogidos del editor.<br>
     */
    public Hashtable<String,Object> getDato() {
        if (hashClaves == null) {
            return null;
        }

        return recogeDatos();
    }

}
