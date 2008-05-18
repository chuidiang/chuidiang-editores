package com.chuidiang.editores.utilidades;

import java.util.Date;

/**
 * Bean para usar en los test
 * @author chuidiang
 *
 */
public class BeanParaPruebas
{
    public static final String BOOLEANO = "booleano";
    public static final String ENTERO="entero";
    public static final String ENTERO_PRIMITIVO = "enteroPrimitivo";
    boolean booleano;
    int enteroPrimitivo;
    Integer entero;
    Float flotante;
    Date fecha;
    public boolean isBooleano() {
        return booleano;
    }
    public void setBooleano(boolean booleano) {
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
    public int getEnteroPrimitivo() {
        return enteroPrimitivo;
    }
    public void setEnteroPrimitivo(int enteroPrimitivo) {
        this.enteroPrimitivo = enteroPrimitivo;
    }
}
