package com.chuidiang.editores.primitivos.errores;

import java.awt.Color;
import java.awt.Component;

import com.chuidiang.editores.comun.InterfaceEdicionDatos;
import com.chuidiang.editores.primitivos.Editor;



/**
 * Cambia el color de fondo del editor cuando el valor es incorrecto.
 * @author Chuidiang
 */
public class ErrorConColor<Tipo> implements InterfaceTratamientoError<Tipo>
{
    /**
     * Creates a new instance of ErrorConColor
     */
    public ErrorConColor(Color defecto, Color erroneo)
    {
        this.defecto = defecto;
        this.erroneo = erroneo;
    }

    /** Color de fondo por defecto para el editor, el que se supone
     * que se muestra cuando no hay error en el editor */
    private Color defecto;

    /** Color de fondo que mostrara el editor cuando tenga un valor
     * incorrecto. */
    private Color erroneo;

    /**
     * Pone el color por defecto en el editor.
     */
    public void correcto(Editor<Tipo> editor)
    {
        editor.setBackground(defecto);
    }

    /**
     * Pone el color erroneo como background del editor
     * @param error : Texto explicativo de por que el editor tiene un
     * valor incorrecto. Se ignora.
     * @param editor : El editor con un valor incorrecto.
     */
    public void tomaError(String error, InterfaceEdicionDatos<Tipo> editor)
    {
    	if (editor instanceof Component)
        ((Component)editor).setBackground(erroneo);
    }
    
    
}
