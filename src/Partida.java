import javax.management.openmbean.ArrayType;
import java.lang.reflect.Array;
import java.util.Random;

public class Partida {
    private Jugador jugador1;
    private Jugador jugador2;
    private int score1;
    private int score2;
    private Character[][] tablero;
    private int turnoActual;
    private long tiempoPartida;

    public Partida(Jugador jugador1, Jugador jugador2) {
        this.jugador1 = jugador1;
        this.jugador2 = jugador2;
        this.tablero = new Character[15][15];
        this.score1 = 0;
        this.score2 = 0;
        this.turnoActual = 1;
    }

    public void alternarTurno(){
        if (this.turnoActual == 1){ this.turnoActual= 2; }
        else{ this.turnoActual = 1; }
    }

    public void iniciarPartida(){
        SpanishBag bag = new SpanishBag();
        jugador1.addCharacters(bag.get(7));
        jugador2.addCharacters(bag.get(7));
        long tiempoInicial = System.currentTimeMillis();
        while (!finalizarPartida()){

        }
        long tiempoFinal = System.currentTimeMillis();
        this.tiempoPartida = tiempoFinal - tiempoFinal;
    }

    public boolean finalizarPartida(){

        return false;
    }

    public void guardarPartida(){

    }

    public void seleccionarGanador(){

    }

}
