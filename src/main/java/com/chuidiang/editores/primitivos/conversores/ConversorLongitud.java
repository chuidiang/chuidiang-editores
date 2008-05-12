package com.chuidiang.editores.primitivos.conversores;

/**
 * Convierte un Double que representa una longitud en grados a String y
 * viceversa.<br>
 */
public class ConversorLongitud extends ConversorEjeGeografico {
    // ~ Variables/Inicializadores estaticos
    // -------------------------------------

    /** Mascara correspondiente a PATRON_DEFECTO */
    public static String MASCARA_DEFECTO = "###"
            + ConversorEjeGeografico.SIMBOLO_GRADOS + "##''##''''U";

    // ~ Metodos
    // -----------------------------------------------------------------

    /**
     * COMENTARIO.<br>
     * 
     * @return COMENTARIO.<br>
     */
    protected String getCadenaEjeNegativo() {
        return "W";
    }

    /**
     * COMENTARIO.<br>
     * 
     * @return COMENTARIO.<br>
     */
    protected String getCadenaEjePositivo() {
        return "E";
    }

    /**
     * COMENTARIO.<br>
     * 
     * @return COMENTARIO.<br>
     */
    protected String getNombreEje() {
        return "longitud";
    }

    /**
     * COMENTARIO.<br>
     * 
     * @return COMENTARIO.<br>
     */
    protected String getPatternGrados() {
        return "000";
    }
}
