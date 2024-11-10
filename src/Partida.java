import javax.management.openmbean.ArrayType;
import java.lang.reflect.Array;
import java.util.Random;

public class Partida {
    private Jugador jugador1;
    private Jugador jugador2;
    private String[][] tablero;
    private int turnoActual;
    private long tiempoPartida;

    public Partida(Jugador jugador1, Jugador jugador2) {
        this.jugador1 = jugador1;
        this.jugador2 = jugador2;
        this.tablero = new String[15][15];
        this.turnoActual = 1;
    }

    public void alternarTurno(){
        if (this.turnoActual == 1){ this.turnoActual= 2; }
        else{ this.turnoActual = 1; }
    }

    public void iniciarPartida(){

    }

    public void finalizarPartida(){

    }

    public void guardarPartida(){

    }

    public void seleccionarGanador(){

    }

}
