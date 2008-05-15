package com.chuidiang.editores.primitivos.restricciones;

import java.text.MessageFormat;

import org.apache.log4j.Logger;

import com.chuidiang.editores.utilidades.CadenaCaracteresEditoresDeprecated;

/**
 * Modelo de filtro para strings.<br>
 * Comprueba que el String cumple un patron con e ?, similar al del comando ls
 * de unix para listar ficheros.
 */
public class RestriccionMascaraString implements InterfaceRestriccion<String> {
    // ~ Variables/Inicializadores estaticos
    // -------------------------------------

    /** Mensaje de warning */
    private static final String EL_ELEMENTO_PASADO_NO_ES_STRING = "El elemento pasado no es String";

    /** Caracter para el mascara */
    public static final String ASTERISCO = new String("*");

    /** Caracter interrogante para el mascara */
    public static final String INTERROGANTE = new String("?");

    /**
     * Prefijo que hay que insertar delante del asterisco y del interrogante
     * para que funcione String.matches()
     */
    public static final String PREFIJO = new String(".");

    // ~ Variables de instancia
    // --------------------------------------------------

    /** Logger para la clase */
    private Logger log = Logger.getLogger(RestriccionMascaraString.class);

    /**
     * Patron con el que se compara para.<br>
     * El patron es con e ?. Por defecto todo pasa el filtro
     */
    private String mascara = null;

    // ~ Metodos
    // -----------------------------------------------------------------

    /**
     * Se le pasa la mascara de filtrado.<br>
     * La mascara es un String con e ?. Si es null, el filtro queda desactivado
     * y cualquier cadena lo pasa.
     * 
     * @param mascara
     *            La mascara
     */
    public void setMascara(String mascara) {
        this.mascara = mascara;
    }

    /**
     * Devuelve la mascara con la que se esta filtrando.<br>
     * 
     * @return Mascara
     */
    public String getMascara() {
        return mascara;
    }

    /**
     * @see com.chuidiang.editores.filtros.InterfaceModeloFiltroGenerico#pasaFiltro(java.lang.Object)
     */
    public boolean pasaRestriccion(String cadena, StringBuffer errores) {
        if (!(cadena instanceof String)) {
            log.warn(EL_ELEMENTO_PASADO_NO_ES_STRING);
            errores.delete(0, errores.length());
            errores.append(EL_ELEMENTO_PASADO_NO_ES_STRING);

            return false;
        }

        if (mascara == null) {
            return true;
        }

        if (CadenaCaracteresEditoresDeprecated.pasaFiltro((String) cadena,
                mascara)) {
            return true;
        }

        errores.delete(0, errores.length());
        errores.append(MessageFormat.format(
                "La cadena -{0}- no cumple el patron -{1}-", cadena, mascara));

        return false;
    }
}
