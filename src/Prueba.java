/**
 * Clase de prueba para verificar el funcionamiento del juego y las estadísticas.
 * Contiene métodos para iniciar una partida entre dos jugadores y comprobar las estadísticas de un jugador.
 */
public class Prueba {
    /**
     * Punto de entrada de la aplicación. Ejecuta la prueba `test00`.
     *
     * @param args Parámetros de la línea de comandos (no se utilizan en este caso).
     */
    public static void main(String[] args) {
        test00();
    }

    /**
     * Prueba de una partida entre dos jugadores. Inicializa los jugadores y comienza el juego.
     */
    public static void test00(){
        Jugador jugador1 = new Jugador("Pepe", "pepe@gmail.com");
        Jugador jugador2 = new Jugador("Paco", "paco@gmail.com");
        Partida partida = new Partida(jugador1, jugador2);
        partida.game();
    }

    /**
     * Prueba para consultar las estadísticas de un jugador.
     *
     * @param jugador El nombre del jugador cuya estadística se desea consultar.
     */
    public static void test01(){
        Estadisticas estadisticas = new Estadisticas();
        estadisticas.estad("Pepe");
    }
}
