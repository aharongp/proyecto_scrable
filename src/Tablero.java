public class Tablero {
    private static final int TAMANO = 15;
    private char[][] tablero;

    public Tablero() {
        tablero = new char[TAMANO][TAMANO];
        inicializarTablero();
    }

    private void inicializarTablero() {
        for (int i = 0; i < TAMANO; i++) {
            for (int j = 0; j < TAMANO; j++) {
                tablero[i][j] = ' ';
            }
        }
    }

    public void mostrarTablero() {
        for (int i = 0; i < TAMANO; i++) {
            for (int j = 0; j < TAMANO; j++) {
                System.out.print("|" + tablero[i][j]);
            }
            System.out.println("|");
        }
    }

    public boolean colocarPalabra(String palabra, int fila, int columna, boolean horizontal) {
        if (!esPosicionValida(palabra, fila, columna, horizontal)) {
            return false;
        }

        for (int i = 0; i < palabra.length(); i++) {
            if (horizontal) {
                tablero[fila][columna + i] = palabra.charAt(i);
            } else {
                tablero[fila + i][columna] = palabra.charAt(i);
            }
        }
        return true;
    }

}
