public class Tablero {
    private static final int TAMANIO = 15; // Tamaño estándar del tablero
    private Casilla[][] casillas;

    public Tablero() {
        casillas = new Casilla[TAMANIO][TAMANIO];
        inicializarCasillas();
    }

    private void inicializarCasillas() {
        // Inicializa las casillas del tablero
        for (int i = 0; i < TAMANIO; i++) {
            for (int j = 0; j < TAMANIO; j++) {
                casillas[i][j] = new Casilla();

                // Triple palabra
                if ((i == j || i + j == 14) && (i == 0 || i == 7 || i == 14)) {
                    casillas[i][j].setBonificacion("3W");
                }
                // Doble palabra
                else if ((i == j || i + j == 14) && (i == 1 || i == 2 || i == 3 || i == 11 || i == 12 || i == 13)) {
                    casillas[i][j].setBonificacion("2W");
                }
                // Triple letra
                else if ((i == 5 || i == 9) && (j == 1 || j == 5 || j == 9 || j == 13)) {
                    casillas[i][j].setBonificacion("3L");
                }
                // Doble letra
                else if ((i == 3 || i == 11) && (j == 0 || j == 6 || j == 8 || j == 14)
                        || (i == 6 || i == 8) && (j == 3 || j == 11)) {
                    casillas[i][j].setBonificacion("2L");
                }
                // Centro (casilla inicial)
                else if (i == 7 && j == 7) {
                    casillas[i][j].setBonificacion("2W");
                }
            }
        }
    }

    public void mostrarTablero() {
        for (int i = 0; i < TAMANIO; i++) {
            for (int j = 0; j < TAMANIO; j++) {
                System.out.print("[" + casillas[i][j] + "]");
            }
            System.out.println();
        }
    }

    public boolean colocarPalabra(String palabra, int fila, int columna, boolean esHorizontal) {
        if (!puedeColocarPalabra(palabra, fila, columna, esHorizontal)) {
            System.out.println("La palabra no puede colocarse en esta posición.");
            return false;
        }

        for (int k = 0; k < palabra.length(); k++) {
            char letra = palabra.charAt(k);
            Casilla casilla;

            if (esHorizontal) {
                casilla = casillas[fila][columna + k];
            } else {
                casilla = casillas[fila + k][columna];
            }

            if (casilla.estaVacia()) {
                casilla.colocarFicha(new Character(String.valueOf(letra), 0));
            }
        }

        return true;
    }

    private boolean puedeColocarPalabra(String palabra, int fila, int columna, boolean esHorizontal) {
        if (esHorizontal && columna + palabra.length() > TAMANIO) return false;
        if (!esHorizontal && fila + palabra.length() > TAMANIO) return false;

        for (int k = 0; k < palabra.length(); k++) {
            int x = esHorizontal ? fila : fila + k;
            int y = esHorizontal ? columna + k : columna;

            if (!casillas[x][y].estaVacia() && !casillas[x][y].getFicha().toString().equals(String.valueOf(palabra.charAt(k)))) {
                return false;
            }
        }

        return true;
    }

    public Casilla[][] getCasillas() {
        return casillas;
    }
}
