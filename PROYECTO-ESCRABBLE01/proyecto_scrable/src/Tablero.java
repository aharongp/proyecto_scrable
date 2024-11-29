public class Tablero {
    private static final int TAMANO = 15;
    private char[][] tablero;

    public Tablero() {
        tablero = new char[15][15];
        inicializarTablero();
    }

    private void inicializarTablero() {
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                tablero[i][j] = ' ';
            }
        }
    }

    public void mostrarTablero() {
        System.out.print("   ");
        for (int i = 0; i < TAMANO; i++) {
            System.out.printf("%2d ", i);
        }
        System.out.println();

        for (int i = 0; i < TAMANO; i++) {
            System.out.printf("%2d ", i);
            for (int j = 0; j < TAMANO; j++) {
                System.out.print("| " + tablero[i][j]);
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

    private boolean esPosicionValida(String palabra, int fila, int columna, boolean horizontal) {
        if (horizontal) {
            if (columna + palabra.length() > TAMANO) return false;
        } else {
            if (fila + palabra.length() > TAMANO) return false;
        }

        for (int i = 0; i < palabra.length(); i++) {
            if (horizontal) {
                if (tablero[fila][columna + i] != ' ' && tablero[fila][columna + i] != palabra.charAt(i)) {
                    return false;
                }
            } else {
                if (tablero[fila + i][columna] != ' ' && tablero[fila + i][columna] != palabra.charAt(i)) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean hayLetrasEnElCentro() {
        return tablero[8][8] != ' ';
    }

    public char[][] getTablero() {
        return tablero;
    }

}
