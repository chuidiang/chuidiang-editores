package com.chuidiang.editores.primitivos.conversores;

import java.text.ParseException;

/**
 * Interface para las clases que convierten un objeto Serializable a un String
 * entendible por el usuario y viceversa.<br>
 */
public interface InterfaceConversorString<Tipo> {
    // ~ Variables de instancia
    // --------------------------------------------------

    /** */
    public final String COMA = ",";

    /** */
    public final String PUNTO = ".";

    // ~ Metodos
    // -----------------------------------------------------------------

    /**
     * Convierte la cadena a objeto Serializable.<br>
     * Lanza una ParseException si no se puede hacer la conversi�n.
     * 
     * @param cadena
     *            String con la texto que se quiere convertir a Serializable.<br> *
     * @param valor
     *            Si no es null y el tipo de dato lo admite, se rellena en �l el
     *            valor contenido en el editor y se devuelve este mismo valor en
     *            el return.<br> *
     * 
     * @return Un Objeto Serializable obtenido a partir de la cadena.<br>
     * 
     */
    public Tipo parseString(String cadena) throws ParseException;

    /**
     * Se le pasa un objeto Serializable y lo conviete a String.<br>
     * 
     * @param valor
     *            Objeto Serializable.<br>
     * 
     * 
     * @return String que representa al objeto.<br>
     * 
     */
    public String toString(Tipo valor);
}
