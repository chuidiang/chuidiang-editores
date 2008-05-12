package com.chuidiang.editores.primitivos.errores;

import java.awt.Color;
import java.awt.Component;

import com.chuidiang.editores.comun.InterfaceEdicionDatos;
import com.chuidiang.editores.primitivos.Editor;



/**
 * @author Administrador
 */
public class ErrorConColor implements InterfaceTratamientoError
{
    /**
     * Creates a new instance of ErrorConColor
     */
    public ErrorConColor(Color defecto, Color erroneo)
    {
        this.defecto = defecto;
        this.erroneo = erroneo;
    }

    /** DOCUMENT ME! */
    private Color defecto;

    /** DOCUMENT ME! */
    private Color erroneo;

    /**
     * DOCUMENT ME!
     *
     * @param editor DOCUMENT ME!
     */
    public void correcto(Editor editor)
    {
        editor.setBackground(defecto);
    }

    /**
     * DOCUMENT ME!
     *
     * @param error DOCUMENT ME!
     * @param editor DOCUMENT ME!
     */
    public void tomaError(String error, InterfaceEdicionDatos editor)
    {
    	if (editor instanceof Component)
        ((Component)editor).setBackground(erroneo);
    }
    
    
}
