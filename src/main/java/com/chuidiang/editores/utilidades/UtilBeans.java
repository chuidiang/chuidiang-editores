package com.chuidiang.editores.utilidades;

import java.lang.reflect.Method;
import java.text.MessageFormat;

import org.apache.log4j.Logger;

/**
 * Métodos útiles para manejo de beans.<br>
 * Para tres métodos tontos que voy a usar, prefiero no meter dependencias
 * con cosas como bean-utils.
 * @author chuidiang
 *
 */
public class UtilBeans {
    /** logger para la clase */
    private final static Logger log = Logger.getLogger(UtilBeans.class);
    /**
     * Hace el setPropiedad(valor) del bean que se le pasa
     * @param bean el bean
     * @param propiedad el nombre de la propiedad
     * @param valor el valor
     */
    public static void set (Object bean, String propiedad, Object valor)
    {
        Method metodo = null;
        try {
            metodo = bean.getClass().getMethod("set"+propiedad, valor.getClass());
            metodo.invoke(bean,valor);
        } catch (Exception e) {
            log.warn(MessageFormat.format("No se encuentra metodo set{0}",propiedad),e);
            return;
        }
    }
    
    /**
     * Devuelve el resultado de llamar a getPropiedad() del bean que se
     * le pasa.<br>
     * Prueba tanto getPropiedad() como isPropiedad(), por si es Boolean.
     * @param bean el bean
     * @param propiedad la propiedad
     * @return el valor de la propiedad
     */
    public static Object get (Object bean, String propiedad)
    {
        Method metodo = null;
        try
        {
            metodo = bean.getClass().getMethod("get"+propiedad);
            return metodo.invoke(bean);
        } catch (Exception e)
        {
            log.warn(MessageFormat.format("No se puede llamar a get{0}",propiedad),e);
            return null;
        }
        
    }
    
    /**
     * Crea y devuelve una instancia del bean cuyo Class se le pasa.<br>
     * En realidad basta con que sea una clase con un constructor sin
     * parámetros.
     * @param bean El Class del bean
     * @return Una intancia del bean
     */
    public static Object instacia (Class bean)
    {
        return null;
    }
}
