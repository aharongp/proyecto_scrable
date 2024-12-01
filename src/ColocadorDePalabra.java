import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ColocadorDePalabra {
    private int puntosGanados=0;
    public boolean colocarPalabra(String palabra, int fila, int columna, boolean horizontal, Jugador jugador, Character[][] tablero) {
        ArrayList<Character> fichasUsadas=new ArrayList<>();
        boolean canPlace = canPlaceWord(palabra,fila,columna,jugador.getPlayerCharacters(),horizontal,tablero);
        if (canPlace) {
            System.out.println("The palabra \"" + palabra + "\" can be placed on the board.");
            actualizarTablero(palabra, fila, columna, horizontal,tablero,jugador.getPlayerCharacters(),fichasUsadas);
            jugador.getPlayerCharacters().getFichas().removeAll(fichasUsadas);
            jugador.addPoints(puntosGanados);
            jugador.addWordPlayed();
        } else {
            System.out.println("The palabra \"" + palabra + "\" cannot be placed on the board.");
        }
        return canPlace;
    }

    public boolean canPlaceWord(String word,int row, int col,FichasJugador fichasJugador, boolean horizontal, Character[][] board) {
        Map<String, Integer> tileCount = new HashMap<>();
        for (Character tile : fichasJugador.getFichas()) {
            tileCount.put(tile.getSymbol(), tileCount.getOrDefault(tile.getSymbol(), 0) + 1);
        }
        int requiredTilesCount = 0;
        for (int i = 0; i < word.length(); ) {
            String tile;
            if (i < word.length() - 1 && (word.charAt(i) == 'L' && word.charAt(i + 1) == 'L')) {
                tile = "LL";
                requiredTilesCount++;
                i += 2;
            } else if (i < word.length() - 1 && (word.charAt(i) == 'R' && word.charAt(i + 1) == 'R')) {
                tile = "RR";
                requiredTilesCount++;
                i += 2;
            } else if (i < word.length() - 1 && (word.charAt(i) == 'C' && word.charAt(i + 1) == 'H')) {
                tile = "CH";
                requiredTilesCount++;
                i += 2;
            } else {
                tile = String.valueOf(word.charAt(i));
                requiredTilesCount++;
                i++;
            }
            if (horizontal) {
                col = col + (i - requiredTilesCount) - 1;
            }
            else {
                row = row + (i - requiredTilesCount) - 1 ;
            }
            if (board[row][col]!=null && !board[row][col].getSymbol().equals(tile)) {
                System.out.println("Letra en tablero no coincide : " + row +", "+col+","+"ficha=" +tile + ",tab="+ board[row][col].getSymbol());
                return false;
            }
            else {
               System.out.println("Pasa en tablero");
            }
            if (board[row][col]!=null && tileCount.getOrDefault(tile, 0) <= 0) {
                System.out.println("Jugador no tiene letra : " + row + ", " + col + "," + "ficha=" + tile + ",tab=" + board[row][col].getSymbol());
                return false;
            }
           else {
                    System.out.println("Pasa en ficha");
                }
            if (board[row][col]!=null) {
                tileCount.put(tile, tileCount.get(tile) - 1);
            }
        }

        return true;
    }

    public void actualizarTablero(String word, int fila, int columna, boolean horizontal, Character[][] tablero, FichasJugador fichasJugador,ArrayList<Character> fichasUsadas) {
        if (horizontal) {
            for (int i = 0; i < word.length(); ) {
                if(tablero[fila][columna]==null) {
                    String tile;
                    if (i < word.length() - 1 && (word.charAt(i) == 'L' && word.charAt(i + 1) == 'L')) {
                        tile = "LL";
                        Character ficha = fichasJugador.buscar(tile);
                        tablero[fila][columna] = ficha;
                        fichasUsadas.add(ficha);
                        puntosGanados = puntosGanados + ficha.getPoints();
                        columna++;
                        i += 2;
                    } else if (i < word.length() - 1 && (word.charAt(i) == 'R' && word.charAt(i + 1) == 'R')) {
                        tile = "RR";
                        Character ficha = fichasJugador.buscar(tile);
                        tablero[fila][columna] = ficha;
                        fichasUsadas.add(ficha);
                        puntosGanados = puntosGanados + ficha.getPoints();
                        columna++;
                        i += 2;
                    } else if (i < word.length() - 1 && (word.charAt(i) == 'C' && word.charAt(i + 1) == 'H')) {
                        tile = "CH";
                        Character ficha = fichasJugador.buscar(tile);
                        tablero[fila][columna] = ficha;
                        fichasUsadas.add(ficha);
                        puntosGanados = puntosGanados + ficha.getPoints();
                        columna++;
                        i += 2;
                    } else {
                        tile = String.valueOf(word.charAt(i));
                        Character ficha = fichasJugador.buscar(tile);
                        tablero[fila][columna] = ficha;
                        fichasUsadas.add(ficha);
                        puntosGanados = puntosGanados + ficha.getPoints();
                        columna++;
                        i++;
                    }
                }
                else {
                    i++;
                    columna++;
                }
            }
        } else {
            for (int i = 0; i < word.length(); ) {
                if(tablero[fila][columna]==null) {
                    String tile;
                    if (i < word.length() - 1 && (word.charAt(i) == 'L' && word.charAt(i + 1) == 'L')) {
                        tile = "LL";
                        Character ficha = fichasJugador.buscar(tile);
                        tablero[fila][columna] = ficha;
                        fichasUsadas.add(ficha);
                        puntosGanados = puntosGanados + ficha.getPoints();
                        fila++; // Move to the next position
                        i += 2; // Skip the next 'L'
                    } else if (i < word.length() - 1 && (word.charAt(i) == 'R' && word.charAt(i + 1) == 'R')) {
                        tile = "RR";
                        Character ficha = fichasJugador.buscar(tile);
                        tablero[fila][columna] = ficha;
                        fichasUsadas.add(ficha);
                        puntosGanados = puntosGanados + ficha.getPoints();
                        fila++;
                        i += 2;
                    } else if (i < word.length() - 1 && (word.charAt(i) == 'C' && word.charAt(i + 1) == 'H')) {
                        tile = "CH";
                        Character ficha = fichasJugador.buscar(tile);
                        tablero[fila][columna] = ficha;
                        fichasUsadas.add(ficha);
                        puntosGanados = puntosGanados + ficha.getPoints();
                        fila++;
                        i += 2;
                    } else {
                        tile = String.valueOf(word.charAt(i));
                        Character ficha = fichasJugador.buscar(tile);
                        tablero[fila][columna] = ficha;
                        fichasUsadas.add(ficha);
                        puntosGanados = puntosGanados + ficha.getPoints();
                        fila++;
                        i++;
                    }
                }
                else {
                    i++;
                    fila++;
                }
            }
        }
    }
}