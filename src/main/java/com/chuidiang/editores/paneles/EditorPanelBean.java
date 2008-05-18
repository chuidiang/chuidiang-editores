package com.chuidiang.editores.paneles;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.log4j.Logger;

import com.chuidiang.editores.comun.InterfaceEdicionDatos;

/**
 * Panel editor que admite y devuelve beans.
 * 
 * @author chuidiang
 * 
 */
public class EditorPanelBean<Tipo> extends EditorPanelGeneral<Tipo> {
    /**
     * serial uid
     */
    private static final long serialVersionUID = -4061474163219707651L;

    /** Logger de la clase */
    private static final Logger log = Logger.getLogger(EditorPanelBean.class);

    private Class claseDelBean;

    public EditorPanelBean(Class bean) {
        this.claseDelBean = bean;
    }

    /**
     * Construye un bean, lo rellena con lo que hay en los editores y lo
     * devuelve
     */
    public Tipo getDato() {
        Tipo dato = null;
        try {
            dato = (Tipo) claseDelBean.newInstance();
        } catch (Exception e) {
            log.warn("No se puede instanciar el bean ", e);
            return null;
        }

        Map<String, Object> propiedades;
        try {
            propiedades = PropertyUtils.describe(dato);
            Iterator<Entry<String, Object>> propiedadesBean = propiedades
                    .entrySet().iterator();
            while (propiedadesBean.hasNext()) {
                Entry<String, Object> propiedad = propiedadesBean.next();
                Object editor = dameEditorConClave(propiedad.getKey());
                if (editor instanceof InterfaceEdicionDatos)
                    PropertyUtils.setProperty(dato, propiedad.getKey(),
                            ((InterfaceEdicionDatos) editor).getDato());
            }
        } catch (Exception e2) {
            log.warn("No se puede rellenar el bean", e2);

        }
        return dato;
    }

    /**
     * Recoge el dato que se le pasa y lo muestra en los editores. Si el
     * dato es null, vacia los editores.
     */
    public void setDato(Tipo dato) {
        if (dato == null) {
            borraContenidoDeTodosLosEditores();
            return;
        }

        Map<String, Object> propiedades;
        try {
            propiedades = PropertyUtils.describe(dato);
            Iterator<Entry<String, Object>> propiedadesBean = propiedades
                    .entrySet().iterator();
            while (propiedadesBean.hasNext()) {
                Entry<String, Object> propiedad = propiedadesBean.next();
                log.debug(propiedad.toString());
                Object editor = dameEditorConClave(propiedad.getKey());
                if (editor instanceof InterfaceEdicionDatos)
                    ((InterfaceEdicionDatos) editor).setDato(PropertyUtils
                            .getProperty(dato, propiedad.getKey()));
            }
        } catch (Exception e) {
            log.warn("No se puede analizar bean ", e);
            return;
        }
    }

}
