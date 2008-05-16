package com.chuidiang.editores.utilidades;

import java.util.Date;

import org.apache.log4j.BasicConfigurator;

import junit.framework.TestCase;

public class TestUtilBeans extends TestCase {
    public TestUtilBeans()
    {
        BasicConfigurator.configure();
    }
    
    public void testSet()
    {
        BeanTestUtilBeans bean = new BeanTestUtilBeans();
        UtilBeans.set(bean, "Booleano", true);
        assertTrue(bean.isBooleano());
        
        UtilBeans.set(bean, "Entero", 3);
        assertEquals(new Integer(3),bean.getEntero());
    }
    
    public void testGet()
    {
        BeanTestUtilBeans bean = new BeanTestUtilBeans();
        Date fecha = new Date();
        bean.setFecha(fecha);
        Date fecha2 = (Date)UtilBeans.get(bean, "Fecha");
        assertEquals(fecha,fecha2);
    }
}

class BeanTestUtilBeans
{
    Boolean booleano;
    Integer entero;
    Float flotante;
    Date fecha;
    public Boolean isBooleano() {
        return booleano;
    }
    public void setBooleano(Boolean booleano) {
        this.booleano = booleano;
    }
    public Integer getEntero() {
        return entero;
    }
    public void setEntero(Integer entero) {
        this.entero = entero;
    }
    public Float getFlotante() {
        return flotante;
    }
    public void setFlotante(Float flotante) {
        this.flotante = flotante;
    }
    public Date getFecha() {
        return fecha;
    }
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
}
