package com.chuidiang.editores.paneles;

/**
 * Interface que implementan aquellos editores que tienen campos de rellenado
 * obligatorio
 *
 */
public interface InterfaceEditorComponentesObligatorios
 {
     /**
      * Recibe la clase encargada de gestionar los componentes de rellenado
      * obligatorio.
      * Las clases hijas deben pasar a ese gestor los componentes que son de
      * relleno obligatorio.
      */
     public void tomaGestorComponentesObligatorios (
        InterfaceComponentesObligatorios gestor);
}
