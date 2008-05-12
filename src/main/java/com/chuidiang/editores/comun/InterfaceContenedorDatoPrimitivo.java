package com.chuidiang.editores.comun;


/**
 * Interface comun para todas las clases que contengan un dato primtivo
 * (o similar) en su interior.<br>
 *
 */
public interface InterfaceContenedorDatoPrimitivo<Tipo>
{
	   /**
	    * Se le pasa un tipo de dato primitivo de java que entiende.<br>
	    * Puede ser Number, Boolean, String, etc, etc, seg�n el dato.
	    *
	    * @param dato dato que debe almacenar.<br>
	    */
	   public void setDatoPrimitivo( Tipo dato );

	   /**
	    * Devuelve el tipo de dato primitivo contenido en su interior.<br>
	    *
	    * @return dato primitivo.<br>
	    */
	   public Tipo getDatoPrimitivo(  );

}
