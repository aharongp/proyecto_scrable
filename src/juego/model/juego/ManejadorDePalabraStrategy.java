package juego;

public interface ManejadorDePalabraStrategy {
    public boolean colocarPalabra(String palabra, int fila, int columna, boolean horizontal, Jugador jugador, Character[][] tablero, boolean tableroVacio);
}
