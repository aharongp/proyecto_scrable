import java.util.ArrayList;

public class Tablero {
    private static final int TAMANO = 15;
    private static final int MAX_FICHAS = 7;
    private Casilla[][] tablero;


    public Tablero() {
        tablero = new Casilla[TAMANO][TAMANO];
        inicializarTablero();
    }

    private void inicializarTablero() {
        for (int i = 0; i < TAMANO; i++) {
            for (int j = 0; j < TAMANO; j++) {
                tablero[i][j] = new Casilla();
            }
        }
        String[] triplePalabra = {"0,0", "0,7", "0,14", "7,0", "7,14", "14,0", "14,7", "14,14"};
        String[] doblePalabra = {"1,1", "2,2", "3,3", "4,4", "7,7", "10,10", "11,11", "12,12", "13,13"};
        String[] tripleLetra = {"1,5", "5,1", "5,5", "5,9", "5,13", "9,1", "9,5", "9,9", "9,13", "13,5"};
        String[] dobleLetra = {"0,3", "0,11", "2,6", "2,8", "3,0", "3,7", "3,14", "6,2", "6,6", "6,8", "6,12",
                "7,3", "7,11", "8,2", "8,6", "8,8", "8,12", "11,0", "11,7", "11,14", "12,6", "12,8",
                "14,3", "14,11"};

        for (String pos : triplePalabra) {
            String[] coords = pos.split(",");
            tablero[Integer.parseInt(coords[0])][Integer.parseInt(coords[1])].setBonificacion("3W");
        }

        for (String pos : doblePalabra) {
            String[] coords = pos.split(",");
            tablero[Integer.parseInt(coords[0])][Integer.parseInt(coords[1])].setBonificacion("2W");
        }

        for (String pos : tripleLetra) {
            String[] coords = pos.split(",");
            tablero[Integer.parseInt(coords[0])][Integer.parseInt(coords[1])].setBonificacion("3L");
        }

        for (String pos : dobleLetra) {
            String[] coords = pos.split(",");
            tablero[Integer.parseInt(coords[0])][Integer.parseInt(coords[1])].setBonificacion("2L");
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
                String bonificacion = tablero[i][j].getBonificacion();
                if (bonificacion == null) {
                    System.out.print("|   ");
                } else {
                    System.out.print("| " + bonificacion + " ");
                }
            }
            System.out.println("|");
        }
    }


    public boolean colocarPalabra(String palabra, int fila, int columna, boolean horizontal, Jugador jugador) {
        palabra = palabra.toUpperCase();

        if (!jugador.validarCaracteres(palabra)) {
            return false;
        }

        if (!esPosicionValida(palabra, fila, columna, horizontal)) {
            return false;
        }

        int puntosGanados = 0;
        ArrayList<Character> fichasUsadas = new ArrayList<>();
        int i = 0;

        while (i < palabra.length()) {
            String currentSymbol = String.valueOf(palabra.charAt(i)).toLowerCase();
            boolean isSpecialChar = false;

            if (i + 1 < palabra.length()) {
                String possibleSpecial = currentSymbol + String.valueOf(palabra.charAt(i + 1)).toLowerCase();
                if (possibleSpecial.equals("ch") || possibleSpecial.equals("ll") || possibleSpecial.equals("rr")) {
                    colocarFicha(possibleSpecial.toUpperCase(), fila, columna, horizontal, i, jugador, fichasUsadas);
                    puntosGanados += obtenerPuntosFicha(possibleSpecial, jugador);
                    i += 2;
                    isSpecialChar = true;
                    continue;
                }
            }

            if (!isSpecialChar) {
                colocarFicha(currentSymbol.toUpperCase(), fila, columna, horizontal, i, jugador, fichasUsadas);
                puntosGanados += obtenerPuntosFicha(currentSymbol, jugador);
                i++;
            }
        }

        jugador.getPlayerCharacters().getFichas().removeAll(fichasUsadas);

        jugador.addPoints(puntosGanados);
        jugador.addWordPlayed();

        return true;
    }

    private boolean esPosicionValida(String palabra, int fila, int columna, boolean horizontal) {
        if (horizontal && columna + palabra.length() > TAMANO) return false;
        if (!horizontal && fila + palabra.length() > TAMANO) return false;

        boolean tocaLetraExistente = false;
        int i = 0;

        while (i < palabra.length()) {
            String currentSymbol = String.valueOf(palabra.charAt(i)).toLowerCase();
            boolean isSpecialChar = false;

            if (i + 1 < palabra.length()) {
                String possibleSpecial = currentSymbol + String.valueOf(palabra.charAt(i + 1)).toLowerCase();
                if (possibleSpecial.equals("ch") || possibleSpecial.equals("ll") || possibleSpecial.equals("rr")) {
                    if (!validarCasilla(possibleSpecial, fila, columna, horizontal, i)) return false;
                    tocaLetraExistente |= hayLetraAdyacente(fila, columna, horizontal, i);
                    i += 2;
                    isSpecialChar = true;
                    continue;
                }
            }

            if (!isSpecialChar) {
                if (!validarCasilla(currentSymbol, fila, columna, horizontal, i)) return false;
                tocaLetraExistente |= hayLetraAdyacente(fila, columna, horizontal, i);
                i++;
            }
        }

        return tocaLetraExistente || tableroVacio();
    }

    private boolean validarCasilla(String simbolo, int fila, int columna, boolean horizontal, int offset) {
        int posFila = horizontal ? fila : fila + offset;
        int posColumna = horizontal ? columna + offset : columna;

        if (tablero[posFila][posColumna] == null) return true;
        return tablero[posFila][posColumna].getSymbol().equalsIgnoreCase(simbolo);
    }

    private boolean hayLetraAdyacente(int fila, int columna, boolean horizontal, int offset) {
        int posFila = horizontal ? fila : fila + offset;
        int posColumna = horizontal ? columna + offset : columna;

        int[][] direcciones = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        for (int[] dir : direcciones) {
            int adyFila = posFila + dir[0];
            int adyColumna = posColumna + dir[1];

            if (adyFila >= 0 && adyFila < TAMANO && adyColumna >= 0 && adyColumna < TAMANO) {
                if (tablero[adyFila][adyColumna] != null) return true;
            }
        }
        return false;
    }

    private boolean tableroVacio() {
        for (int i = 0; i < TAMANO; i++) {
            for (int j = 0; j < TAMANO; j++) {
                if (tablero[i][j] != null) return false;
            }
        }
        return true;
    }

    private void colocarFicha(String simbolo, int fila, int columna, boolean horizontal, int offset, Jugador jugador, ArrayList<Character> fichasUsadas) {
        int posFila = horizontal ? fila : fila + offset;
        int posColumna = horizontal ? columna + offset : columna;

        if (tablero[posFila][posColumna] == null) {
            for (Character c : jugador.getPlayerCharacters().getFichas()) {
                if (c.getSymbol().equalsIgnoreCase(simbolo)) {
                    tablero[posFila][posColumna] = new Character(simbolo, c.getPoints());
                    fichasUsadas.add(c);
                    break;
                }
            }
        }
    }

    private int obtenerPuntosFicha(String simbolo, Jugador jugador) {
        for (Character c : jugador.getPlayerCharacters().getFichas()) {
            if (c.getSymbol().equalsIgnoreCase(simbolo)) {
                return c.getPoints();
            }
        }
        return 0;
    }

    public boolean hayLetrasEnElCentro() {
        return tablero[7][7] != null;
    }

    public Character[][] getTablero() {
        return tablero;
    }
}

