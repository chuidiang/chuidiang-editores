package com.chuidiang.editores.utilidades;

import java.util.Enumeration;
import java.util.Hashtable;

/**
 * Utilidades con Hashtables.<br>
 *
 */
public class HashtableUtil
{
	/** Mete todos los datos de h2 en h1.<br>
	 * El metodo no vacia hashDestino, por lo que si se quiere una copia
	 * exacta debe vaciarse antes de llamar. */
	public static void copiaHashtable(Hashtable hashDestino, Hashtable hashOrigen)
	{
		 Enumeration claves = hashOrigen.keys();
		 while (claves.hasMoreElements())
		 {
		 	Object clave = claves.nextElement();
		 	hashDestino.put(clave, hashOrigen.get(clave));
		 }
	}
}
