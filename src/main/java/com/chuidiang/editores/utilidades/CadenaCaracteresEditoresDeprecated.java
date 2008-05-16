package com.chuidiang.editores.utilidades;

/**
 * Metodos utiles para tratamiento de cadenas de caracteres.<br>
 *
 */
public class CadenaCaracteresEditoresDeprecated
{
   //~ Variables/Inicializadores estaticos -------------------------------------

   /** Para la mascara de caracteres */
   public static final String ASTERISCO = new String( "*" );

   /** Para la mascara de un caracter */
   public static final String INTERROGANTE = new String( "?" );

   /** Prefijo a insetar en * e ? para que funcione String.matches() */
   public static final String PREFIJO = new String( "." );

   //~ Metodos -----------------------------------------------------------------

   /**
    * Este metodo devuelve un String con el caracter pasado como parametros
    * repetido tantas veces como se indique en el parametro num. Util para
    * rellenar cadena con espacios.
    *
    * @param caracter COMENTARIO.<br>
    * @param num COMENTARIO.<br>
    *
    * @return COMENTARIO.<br>
    */
   public static String dameCaracterNveces( 
      char caracter,
      int num )
   {
      if( num <= 0 )
      {
         return null;
      }

      if( num == 1 )
      {
         return Character.toString( caracter );
      }

      char[] cadena = new char[num];

      for( int i = 0;i < num;i++ )
      {
         cadena[i] = caracter;
      }

      return String.valueOf( cadena );
   }

   /**
    * Este metodo devuelve un String con el numero de espacios indicado en el
    * parametro.
    *
    * @param num COMENTARIO.<br>
    *
    * @return COMENTARIO.<br>
    */
   public static String dameEspacios( int num )
   {
      if( num <= 0 )
      {
         return "";
      }

      // Si es uno se devuelve directamente.
      if( num == 1 )
      {
         return " ";
      }

      // Se crea el StringBuffer del tama�o indicado.
      StringBuffer string = new StringBuffer( num );

      // Se divide el tama�o entre 2, para ir insertando espacios de 
      // 2 en 2.
      int mitad = (int)num / 2;

      for( int i = 0;i < mitad;i++ )
      {
         string.append( "  " );
      }

      // Si no es cero se a�ade un espacio que nos falta.
      if( ( num % 2 ) != 0 )
      {
         string.append( " " );
      }

      return string.toString(  );
   }

   /**
    * Metodo que recibe un array de String y devuelve un texto html con saltos
    * de linea despues de cada elemento del array. Valido para hacer
    * componentes con textos multilinea: ej. ToolTip.
    *
    * @param lineas COMENTARIO.<br>
    *
    * @return COMENTARIO.<br>
    */
   public static String dameMultilineHtml( String[] lineas )
   {
      if( ( lineas == null ) || ( lineas.length == 0 ) )
      {
         return null;
      }

      StringBuffer multiLine = new StringBuffer( "<html>" );

      for( int i = 0;i < lineas.length;i++ )
      {
         if( ( lineas[i] == null ) || ( lineas[i].length(  ) == 0 ) )
         {
            continue;
         }

         multiLine.append( lineas[i] );

         // Si es la ultima linea no se pone el salto
         if( i < ( lineas.length - 1 ) )
         {
            multiLine.append( "<br>" );
         }
      }

      multiLine.append( "</html>" );

      return multiLine.toString(  );
   }

   /*
      Recorta o expande la cadena pasada para que se encuentre entre los valores m�ximo y minimo.
      Si se debe recortar se eliminan los caracteres que sobran hasta adecuarse al valor m�ximo
      Si se debe expandir se a�ade el caracter pasado como relleno tantas veces como se necesario para llegar al valor minimo
    */
   public static String formateaCadena( 
      String cadenaEntrada,
      int numeroMinimoCaracteres,
      int numeroMaximoCaracteres,
      char caracterRelleno )
   {
      if( cadenaEntrada == null )
      {
         return null;
      }

      if( cadenaEntrada.length(  ) < numeroMinimoCaracteres )
      {
         String cadenaExpansion = CadenaCaracteresEditoresDeprecated.dameCaracterNveces( 
               caracterRelleno,
               numeroMinimoCaracteres - cadenaEntrada.length(  ) );

         return ( cadenaEntrada + cadenaExpansion );
      }
      else if( cadenaEntrada.length(  ) > numeroMaximoCaracteres )
      {
         final int CERO = 0;

         return cadenaEntrada.substring( 
            CERO,
            numeroMaximoCaracteres - 1 );
      }

      return cadenaEntrada;
   }

   /**
    * Inserta la cadena prefijo antes de la cadena ocurrencia. La sustituci�n
    * se realiza en toda la cadena, las veces que sea necesario.
    *
    * @param cadenaOriginal COMENTARIO.<br>
    * @param ocurrencia COMENTARIO.<br>
    * @param prefijo COMENTARIO.<br>
    */
   public static void insertaPrefijo( 
      StringBuffer cadenaOriginal,
      String ocurrencia,
      String prefijo )
   {
      int offset = 0;
      int posicion = -1;

      while( ( posicion = cadenaOriginal.indexOf( 
               ocurrencia,
               offset ) ) != -1 )
      {
         cadenaOriginal.insert( 
            posicion,
            prefijo );
         offset = posicion + prefijo.length(  ) + 1;
      }
   }

   /* sustituye varios espacios en blanco entre palabras por un espacio
    * ejemplo : Sustituye "a   b" por "a b"
    */
   public static String itrim( String cadena )
   {
      if( cadena == null )
      {
         return "";
      }

      return cadena.replaceAll( 
         "\\b\\s{2,}\\b",
         " " );
   }

   /**
    * elimina los espacios en blanco de la izquierda
    *
    * @param cadena COMENTARIO.<br>
    *
    * @return COMENTARIO.<br>
    */
   public static String ltrim( String cadena )
   {
      if( cadena == null )
      {
         return "";
      }

      return cadena.replaceAll( 
         "^\\s+",
         "" );
   }

   /**
    * Este metodo devuelve el numero de ocurrencias de un caracter dado en un
    * String.
    *
    * @param cadena COMENTARIO.<br>
    * @param caracter COMENTARIO.<br>
    *
    * @return COMENTARIO.<br>
    */
   public static int numeroOcurrencias( 
      String cadena,
      int caracter )
   {
      if( ( cadena == null ) || ( cadena.length(  ) == 0 ) )
      {
         return 0;
      }

      int ocurre = -1;
      int ocurrencias = 0;

      while( ( ocurre = cadena.indexOf( 
               caracter,
               ocurre + 1 ) ) >= 0 )
      {
         ocurrencias++;
      }

      return ocurrencias;
   }

   /**
    * Indica si el String pasado como primer par�metro pasa el filtro indicado
    * por el segundo(del tipo cosa? o similar).
    *
    * @param cadena COMENTARIO.<br>
    * @param cadenaFiltro COMENTARIO.<br>
    *
    * @return COMENTARIO.<br>
    */
   public static boolean pasaFiltro( 
      String cadena,
      String cadenaFiltro )
   {
      StringBuffer patron = null;

      if( cadenaFiltro != null )
      {
         patron = new StringBuffer( cadenaFiltro );
         CadenaCaracteresEditoresDeprecated.insertaPrefijo( 
            patron,
            ASTERISCO,
            PREFIJO );
         CadenaCaracteresEditoresDeprecated.insertaPrefijo( 
            patron,
            INTERROGANTE,
            PREFIJO );
      }

      boolean pasaSimbolo = ( patron == null ) ||
         ( ( cadena != null ) && cadena.matches( patron.toString(  ) ) );

      return pasaSimbolo;
   }

   /**
    * elimina los espacios en blanco de la derecha
    *
    * @param cadena COMENTARIO.<br>
    *
    * @return COMENTARIO.<br>
    */
   public static String rtrim( String cadena )
   {
      if( cadena == null )
      {
         return "";
      }

      return cadena.replaceAll( 
         "\\s+$",
         "" );
   }

   /**
    * Este metodo se encarga de pasar el contenido del primer parametro, un
    * String, al segundo parametro, un array de caracteres pero respetando el
    * tamanho del array. Es decir, se rellena el exceso con el caracter \0.
    * Pero se respeta el tamanho del array. Esto cambia con respecto al metodo
    * toCharArray de la clase String que devuelve un array de caracteres pero
    * con el tamanho que tuviera el String, de forma que al realizar la
    * asignacion el array pasaba a ser otro y de otro tamanho.
    *
    * @param cadena COMENTARIO.<br>
    * @param arrayCaracteres COMENTARIO.<br>
    */
   public static void toCharArray( 
      String cadena,
      char[] arrayCaracteres )
   {
      if( ( cadena == null ) || ( cadena.length(  ) == 0 ) ||
         ( arrayCaracteres == null ) )
      {
         return;
      }

      construyeArray( 
         cadena,
         arrayCaracteres );
   }

   /**
    * Este metodo construye un array de caracteres del tamanho indicado por el
    * segundo parametro, y copia el contenido en caracteres del primer
    * parametro, un String, a dicho array de caracteres, rellenando el exceso
    * con el caracter \0.
    *
    * @param cadena COMENTARIO.<br>
    * @param longitudArrayCaracteres COMENTARIO.<br>
    *
    * @return COMENTARIO.<br>
    */
   public static char[] toCharArray( 
      String cadena,
      int longitudArrayCaracteres )
   {
      if( ( cadena == null ) || ( cadena.length(  ) == 0 ) ||
         ( longitudArrayCaracteres == 0 ) )
      {
         return null;
      }

      char[] arrayCaracteres = new char[longitudArrayCaracteres];

      construyeArray( 
         cadena,
         arrayCaracteres );

      return arrayCaracteres;
   }

   /**
    * Este metodo copia efectivamente el contenido en caracteres del primer
    * parametro, un String, al segundo parametro, rellenando el exceso con el
    * caracter \0.
    *
    * @param cadena COMENTARIO.<br>
    * @param arrayCaracteres COMENTARIO.<br>
    */
   private static void construyeArray( 
      String cadena,
      char[] arrayCaracteres )
   {
      int i;

      for( i = 0;i < arrayCaracteres.length;i++ )
      {
         if( i < cadena.length(  ) )
         {
            arrayCaracteres[i] = cadena.charAt( i );
         }
         else
         {
            arrayCaracteres[i] = 0;
         }
      }
   }
}
