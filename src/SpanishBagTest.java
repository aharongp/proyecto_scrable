import java.util.ArrayList;

/**
 * Clase de prueba para la clase `SpanishBag`. Esta clase tiene el objetivo de probar la funcionalidad
 * de la bolsa de caracteres `SpanishBag`, especialmente el comportamiento de extracción de fichas de la bolsa.
 */
public class SpanishBagTest {

    /**
     * Método de prueba que realiza lo siguiente:
     * - Reinicia la bolsa de caracteres.
     * - Extrae grupos de 7 caracteres de la bolsa hasta que no queden más fichas.
     * - Muestra los caracteres extraídos y el número de fichas restantes.
     */
    public void sBagTest(){
        CharactersBag spanishBag = new SpanishBag();  // Crea una nueva instancia de la bolsa de caracteres
        ArrayList<Character> temp = new ArrayList<>();  // Lista temporal para almacenar las fichas extraídas
        spanishBag.reset();  // Reinicia la bolsa, agregando las fichas iniciales
        // Extrae grupos de 7 caracteres mientras haya fichas disponibles
        while(spanishBag.remaning() > 0) {
            temp = spanishBag.get(7);  // Extrae 7 fichas
            System.out.println(temp);  // Imprime las fichas extraídas
        }
        // Muestra el número de fichas restantes en la bolsa (debería ser 0)
        System.out.println(spanishBag.remaning());
    }
}
