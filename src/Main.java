import java.util.Scanner;

public class Main {
    public static Scanner read = new Scanner(System.in);
    public static void main(String[] args) {
        SpanishBag bag = new SpanishBag();
        Tablero tablero = new Tablero(bag);
        Jugador jugador = new Jugador("Player1", "email@example.com");

// Dar fichas iniciales al jugador
        jugador.addCharacters(bag.get(7));
        jugador.printCharacters();
        String palabra = read.next();
// Intentar colocar una palabra
        boolean resultado = tablero.colocarPalabra(palabra, 7, 7, true, jugador);
        tablero.mostrarTablero();
        jugador.printCharacters();
    }
}