import javax.management.openmbean.ArrayType;
import java.lang.reflect.Array;
import java.util.Random;

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


    public Partida(Jugador jugador1, Jugador jugador2) {
        this.jugador1 = jugador1;
        this.jugador2 = jugador2;
        this.tablero = new Tablero();
        this.score1 = 0;
        this.score2 = 0;
        this.actualTurn = 1;
    }

    public void alternarTurno(){
        if (this.actualTurn == 1){ this.actualTurn= 2; }
        else{ this.actualTurn = 1; }
    }

    public void startGame(){
        this.bag = new SpanishBag();
        this.jugador1.addCharacters(bag.get(7));
        this.jugador2.addCharacters(bag.get(7));
        this.initialTime = System.currentTimeMillis();
    }

    public void game(){
        startGame();
        while (!chooseWinner()){
            this.tablero.mostrarTablero();
            if(this.actualTurn == 1){
                jugador1.printCharacters();
                System.out.println();
                alternarTurno();
            }else{
                jugador2.printCharacters();
            }
        }
        finishGame();
    }

    public void finishGame(){
        this.finishtime = System.currentTimeMillis();
        this.time = this.finishtime - this.initialTime;

    }

    public boolean chooseWinner(){
        return false;
    }

}
