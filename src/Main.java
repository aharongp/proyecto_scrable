import java.util.Scanner;

public class Main {

    public static Scanner read = new Scanner(System.in);

    public static void main(String[] args) {
        Jugador jugador1 = new Jugador("Pepe", "pepe@gmail.com");
        Jugador jugador2 = new Jugador("Paco", "paco@gmail.com");
        Partida partida = new Partida(jugador1, jugador2);
        partida.game();
    }
}