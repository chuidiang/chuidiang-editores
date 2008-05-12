package com.chuidiang.editores.comun;

import com.chuidiang.editores.primitivos.conversores.InterfaceConversorString;

/**
 * @author abellan
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public interface InterfaceContenedorConversor<Tipo> {
    // ~ Metodos
    // -----------------------------------------------------------------

    /**
     * Fija el conversor a String que debe utilizando.<br>
     * 
     * @param conversor
     *            Conversor a utilizar.<br>
     */
    public void setConversorString(InterfaceConversorString<Tipo> conversor);

    /**
     * Devuelve el conversor de String que est� utilizando.<br>
     * 
     * @return Conversor de String que se est� uttilizando.<br>
     */
    public InterfaceConversorString<Tipo> getConversorString();

}