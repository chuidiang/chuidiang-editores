package com.chuidiang.editores.matematicas.angulos;



/**
 * Conversion de valores en grados con decimales a otras unidades y
 * viceversa.<br>
 */
public class GradosConDecimales
{
   //~ Metodos -----------------------------------------------------------------

   /**
    * Se le pasa un angulo en grados con decimales y devuelve los grados.<br>
    *
    * @param angulo Los grados.<br>
    *
    * @return Los grados, siempre con valor positivo.<br>
    */
   public static int getGrados( double angulo )
   {
      long segundos = Math.abs( Math.round( angulo * 3600.0 ) );

      return (int)( segundos / 3600 );
   }

   /**
    * Devuelve un double que son los grados con decimales correspondientes a
    * los grados, minutos y segundos que se le pasan.<br>
    *
    * @param grados grados positivos.<br>
    * @param minutos minutos.<br>
    * @param segundos segundos.<br>
    * @param signo signo deseado para los grados, 1 o -1.<br>
    *
    * @return Los grados con decimales.<br>
    */
   public static double getGradosConDecimales( 
      int grados,
      int minutos,
      int segundos,
      int signo )
   {
      if( segundos > 59 )
      {
         minutos = minutos + ( segundos / 60 );
         segundos = segundos % 60;
      }

      if( minutos > 59 )
      {
         grados = grados + ( minutos / 60 );
         minutos = minutos % 60;
      }

      return ( ( grados + ( minutos / 60.0 ) + ( segundos / 3600.0 ) ) * signo );
   }

   /**
    * Se le pasa un angulo en grados con decimales y hace la conversion a
    * formato gg-mm-ss. Devuelve los minutos, que estaran entre 0 y 59<br>
    *
    * @param angulo El angulo en grados.<br>
    *
    * @return Los minutos de convertir en angulo a gg-mm-ss.<br>
    */
   public static int getMinutos( double angulo )
   {
      long segundos = Math.abs( Math.round( angulo * 3600.0 ) );

      return (int)( ( segundos % 3600 ) - ( segundos % 60 ) ) / 60;
   }

   /**
    * Se le pasa un angulo en grados con decimales y hace la conversi�n a
    * formato gg-mm-ss. Devuelve los segundos, que estar�n entre 0 y 59<br>
    *
    * @param angulo El �ngulo en grados.<br>
    *
    * @return Los segundos de convertir en angulo a gg-mm-ss.<br>
    */
   public static int getSegundos( double angulo )
   {
      long segundos = Math.abs( Math.round( angulo * 3600.0 ) );

      return (int)( segundos % 60 );
   }

   /**
    * Devuelve el signo del angulo.<br> 1 si es positivo, -1 si es negativo.
    *
    * @param angulo Angulo en grados con decimales.<br>
    *
    * @return signo.<br>
    */
   public static int getSigno( double angulo )
   {
      if( angulo >= 0.0 )
      {
         return 1;
      }

      return -1;
   }
}
