import java.util.ArrayList;

public class Tablero {
    private static final int TAMANO = 15;
    private static final int MAX_FICHAS = 7;
    private Character[][] tablero;
    private SpanishBag bag;

    public Tablero(SpanishBag bag) {
        this.bag = bag;
        tablero = new Character[15][15];
        inicializarTablero();
    }

    private void inicializarTablero() {
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                tablero[i][j] = new Character(" ", 0);
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
                System.out.print("| " + tablero[i][j].getSymbol());
            }
            System.out.println("|");
        }
    }

    public boolean colocarPalabra(String palabra, int fila, int columna, boolean horizontal, Jugador jugador) {
        // Primero validamos si el jugador tiene las fichas necesarias
        if (!jugador.validarCaracteres(palabra.toUpperCase())) {
            return false;
        }

        // Luego validamos si la posición es válida en el tablero
        if (!esPosicionValida(palabra, fila, columna, horizontal)) {
            return false;
        }

        int puntosGanados = 0;
        ArrayList<Character> fichasUsadas = new ArrayList<>();
        int i = 0;

        while (i < palabra.length()) {
            String currentSymbol = String.valueOf(palabra.charAt(i)).toLowerCase();

            // Manejar caracteres especiales (ch, ll, rr)
            boolean isSpecialChar = false;
            if (i + 1 < palabra.length()) {
                String possibleSpecial = (currentSymbol + String.valueOf(palabra.charAt(i + 1))).toLowerCase();
                if (possibleSpecial.equals("ch") || possibleSpecial.equals("ll") || possibleSpecial.equals("rr")) {
                    // Buscar y remover el carácter especial de las fichas del jugador
                    for (Character c : jugador.getPlayerCharacters()) {
                        if (c.getSymbol().equalsIgnoreCase(possibleSpecial)) {
                            fichasUsadas.add(c);
                            puntosGanados += c.getPoints();
                            if (horizontal) {
                                tablero[fila][columna + i] = new Character(possibleSpecial.toUpperCase(), c.getPoints());
                            } else {
                                tablero[fila + i][columna] = new Character(possibleSpecial.toUpperCase(), c.getPoints());
                            }
                            break;
                        }
                    }
                    i += 2;
                    isSpecialChar = true;
                    continue;
                }
            }

            if (!isSpecialChar) {
                // Buscar y remover el carácter individual de las fichas del jugador
                for (Character c : jugador.getPlayerCharacters()) {
                    if (c.getSymbol().equalsIgnoreCase(currentSymbol)) {
                        fichasUsadas.add(c);
                        puntosGanados += c.getPoints();
                        if (horizontal) {
                            tablero[fila][columna + i] = new Character(currentSymbol.toUpperCase(), c.getPoints());
                        } else {
                            tablero[fila + i][columna] = new Character(currentSymbol.toUpperCase(), c.getPoints());
                        }
                        break;
                    }
                }
                i++;
            }
        }

        // Remover las fichas usadas del jugador
        jugador.getPlayerCharacters().removeAll(fichasUsadas);

        // Reponer fichas al jugador hasta tener 7
        int fichasNecesarias = MAX_FICHAS - jugador.getNumberOfCharacters();
        if (fichasNecesarias > 0) {
            ArrayList<Character> nuevasFichas = bag.get(fichasNecesarias);
            jugador.addCharacters(nuevasFichas);
        }

        // Actualizar estadísticas del jugador
        jugador.addPoints(puntosGanados);
        jugador.addWordPlayed();

        return true;
    }

    private boolean esPosicionValida(String palabra, int fila, int columna, boolean horizontal) {
        // Verificar límites del tablero
        if (horizontal) {
            if (columna + palabra.length() > TAMANO) return false;
        } else {
            if (fila + palabra.length() > TAMANO) return false;
        }

        int i = 0;
        while (i < palabra.length()) {
            String currentSymbol = String.valueOf(palabra.charAt(i)).toLowerCase();

            // Verificar caracteres especiales
            boolean isSpecialChar = false;
            if (i + 1 < palabra.length()) {
                String possibleSpecial = currentSymbol + String.valueOf(palabra.charAt(i + 1)).toLowerCase();
                if (possibleSpecial.equals("ch") || possibleSpecial.equals("ll") || possibleSpecial.equals("rr")) {
                    if (horizontal) {
                        if (!tablero[fila][columna + i].isEmpty() &&
                                !tablero[fila][columna + i].getSymbol().equalsIgnoreCase(possibleSpecial)) {
                            return false;
                        }
                    } else {
                        if (!tablero[fila + i][columna].isEmpty() &&
                                !tablero[fila + i][columna].getSymbol().equalsIgnoreCase(possibleSpecial)) {
                            return false;
                        }
                    }
                    i += 2;
                    isSpecialChar = true;
                    continue;
                }
            }

            if (!isSpecialChar) {
                if (horizontal) {
                    if (!tablero[fila][columna + i].isEmpty() &&
                            !tablero[fila][columna + i].getSymbol().equalsIgnoreCase(currentSymbol)) {
                        return false;
                    }
                } else {
                    if (!tablero[fila + i][columna].isEmpty() &&
                            !tablero[fila + i][columna].getSymbol().equalsIgnoreCase(currentSymbol)) {
                        return false;
                    }
                }
                i++;
            }
        }
        return true;
    }

    public boolean hayLetrasEnElCentro() {
        return !tablero[7][7].isEmpty();
    }

    public Character[][] getTablero() {
        return tablero;
    }
}