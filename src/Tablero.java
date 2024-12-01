import java.util.ArrayList;

public class Tablero {
    private static final int TAMANO = 15;
    private static final int MAX_FICHAS = 7;
    private Character[][] tablero;

    public Tablero() {
        tablero = new Character[TAMANO][TAMANO];
        inicializarTablero();
    }
    public boolean hayLetrasEnElCentro() {
        return tablero[7][7] != null;
    }

    public Character[][] getTablero() {
        return tablero;
    }

    private void inicializarTablero() {
        for (int i = 0; i < TAMANO; i++) {
            for (int j = 0; j < TAMANO; j++) {
                tablero[i][j] = null;
            }
        }
    }

    public void mostrarTablero() {
        String disp = "";
        System.out.print("   ");
        for (int i = 0; i < TAMANO; i++) {
            System.out.printf("%2d ", i);
        }
        System.out.println();
        for (int i = 0; i < TAMANO; i++) {
            System.out.printf("%2d ", i);
            for (int j = 0; j < TAMANO; j++) {
                if (tablero[i][j] == null) {
                    disp = " ";
                } else {
                    disp = tablero[i][j].getSymbol();
                }
                System.out.print("| " + disp);
            }
            System.out.println("|");
        }
    }

    public boolean ubicarPalabra(String palabra, int fila, int columna, boolean horizontal, Jugador jugador) {
        ColocadorDePalabra colocadorDePalabra=new ColocadorDePalabra();
        return colocadorDePalabra.colocarPalabra(palabra,fila,columna,horizontal,jugador,this.getTablero());
    }

    /*public boolean colocarPalabra(String palabra, int fila, int columna, boolean horizontal, Jugador jugador) {
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
    }*/

    /*private boolean esPosicionValida(String palabra, int fila, int columna, boolean horizontal) {
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
    }*/

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

}