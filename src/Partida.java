
import java.util.ArrayList;

public class Partida {

    private Jugador jugador1;
    private Jugador jugador2;
    private int score1;
    private int score2;
    private SpanishBag bag;
    private Tablero tablero;
    private int actualTurn;
    private long initialTime;
    private long finishtime;
    private long time;
    private int winner;

    public Partida(Jugador jugador1, Jugador jugador2) {
        this.jugador1 = jugador1;
        this.jugador2 = jugador2;
        this.bag = new SpanishBag();
        this.tablero = new Tablero();
        this.score1 = 0;
        this.score2 = 0;
        this.actualTurn = 1;
        this.winner = 0;
    }

    public void alternarTurno() {
        if (actualTurn == 1) {
            actualTurn = 2;
        }
        else {
            actualTurn = 1;
        }
    }

    private void startGame() {
        jugador1.addCharacters(bag.get(7));
        jugador2.addCharacters(bag.get(7));
        initialTime = System.currentTimeMillis();
    }

    public void turnoJugador(Jugador jugador) {
        System.out.println("Es turno del jugador " + jugador.getAlias());
        jugador.printCharacters();
        System.out.println("Escribe la palabra que quieres poner");
        String word = ScreenReader.read.next();
        if (jugador.validarCaracteres(word)) {
            System.out.println("¿En que posicion la deseas colocar?\s numero de fila: ");
            int fila = ScreenReader.read.nextInt();
            System.out.println("numero de columna: ");
            int columna = ScreenReader.read.nextInt();
            System.out.println("""
                            Escribe la direccion de la palabra:\s
                             1. Horizontal\s
                             2. Vertical""");
            int direccion = ScreenReader.read.nextInt();
            if (direccion == 1) {
                this.tablero.colocarPalabra(word, fila, columna, true, jugador);
            }
            else if (direccion == 2) {
                this.tablero.colocarPalabra(word, fila, columna, false, jugador);
            }
            reponerFichas(jugador);
        }
    }

    public void reponerFichas(Jugador jugador) {
        int fichasNecesarias = 7 - jugador.getNumberOfCharacters();
        if (fichasNecesarias > 0) {
            ArrayList<Character> nuevasFichas = bag.get(fichasNecesarias);
            jugador.addCharacters(nuevasFichas);
        }
    }

    public void game() {
        startGame();
        while (!chooseWinner()) {
            this.tablero.mostrarTablero();
            if (actualTurn == 1) {
                turnoJugador(jugador1);
            }
            else {
                turnoJugador(jugador2);
            }
            alternarTurno();
        }
        finishGame();
    }

    public void finishGame() {
        this.finishtime = System.currentTimeMillis();
        this.time = this.finishtime - this.initialTime;
    }

    public boolean chooseWinner() {
        if (this.bag.remaning() == 0) {
            if (this.jugador1.getNumberOfCharacters() == 0) {
                this.winner = 1;
                return true;
            }
            else if (this.jugador2.getNumberOfCharacters() == 0) {
                this.winner = 2;
                return true;
            }
        }
        return false;
    }
}
