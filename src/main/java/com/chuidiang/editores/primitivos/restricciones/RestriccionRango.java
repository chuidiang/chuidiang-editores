package com.chuidiang.editores.primitivos.restricciones;

import org.apache.log4j.Logger;




/**
 * Rango para un Number.<br>
 * Comprueba que un Number esta entre un maximo y un minimo.
 */
public class RestriccionRango
        implements InterfaceRestriccion<Number>
{
   //~ Variables de instancia --------------------------------------------------

	/** Logger de la clase */
	private static Logger log = Logger.getLogger(RestriccionRango.class);
   /**
    * Si este flag es false, la clase siempre devolvera que el valor este
    * dentro de rango
    */
   private boolean activo;

   /** Valor maximo al que no debe superar el Number */
   private double maximo = 0.0;

   /** Valor minimo por debajo del cual no debe estar el Number */
   private double minimo = 0.0;

   //~ Constructores -----------------------------------------------------------

   /**
    * Crea  un nuevo objeto de la clase RestriccionRango.<br>
    *
    * @param minimo Valor m�nimo para el rango.<br>
    * @param maximo Valor m�ximo para el rango.<br>
    */
   public RestriccionRango( 
      double minimo,
      double maximo )
   {
      this.setMinimoMaximo( 
         minimo,
         maximo );
      this.setActivo( true );
   }

   /**
    * Crea  un nuevo objeto de la clase RestriccionRango.<br>
    */
   public RestriccionRango(  )
   {
      this.activo = false;
   }

   //~ Metodos -----------------------------------------------------------------

   /**
    * Setter for property activo.
    *
    * @param activo New value of property activo.
    */
   public void setActivo( boolean activo )
   {
      this.activo = activo;
   }

   /**
    * Getter for property activo.
    *
    * @return Value of property activo.
    */
   public boolean isActivo(  )
   {
      return activo;
   }

   /**
    * Getter for property maximo.
    *
    * @return Value of property maximo.
    */
   public double getMaximo(  )
   {
      return maximo;
   }

   /**
    * Getter for property minimo.
    *
    * @return Value of property minimo.
    */
   public double getMinimo(  )
   {
      return minimo;
   }

   /**
    * Fija los valores minimo y m�xmo para el rango.<br>
    * Si se pone minimo mas grande que m�ximo, el m�todo los pone
    * correctamente.
    *
    * @param minimo Valor minimo para el rango.<br>
    * @param maximo Valor maximo para el rango.<br>
    */
   public void setMinimoMaximo( 
      double minimo,
      double maximo )
   {
      if( minimo < maximo)
      {
         this.minimo = minimo;
         this.maximo = maximo;
      }
      else
      {
         this.minimo = maximo;
         this.maximo = minimo;
      }
      this.setActivo(true);
   }

   /**
    * Comprueba si el valor que se le pasa cumple la restricci�n.<br>
    *
    * @param valor El Number que se quiere comprobar si pasa la restriccion.
    * @param error Un StringBuffer en el que se pondra un texto de porqu� no
    *        pasa la restricci�n.<br>
    *
    * @return true si el valor cumple la restricci�n, false en caso contrario.
    */
   public boolean pasaRestriccion( 
      Number valor,
      StringBuffer error )
   {
      if( !activo )
      {
         return true;
      }

      if( !( valor instanceof Number ) )
      {
         log.warn( 
            "El valor recibido no es Number" );

         return false;
      }

      double aux = valor.doubleValue(  );

      if( ( minimo > aux ) || ( maximo < aux ) )
      {
         if( error != null )
         {
            error.append( 
               "Fuera de rango. Debe estar entre " + minimo + " y " + maximo );
         }

         return false;
      }

      return true;
   }
}
