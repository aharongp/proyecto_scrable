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
    private int winner;


    public Partida(Jugador jugador1, Jugador jugador2) {
        this.jugador1 = jugador1;
        this.jugador2 = jugador2;
        this.bag = new SpanishBag();
        this.tablero = new Tablero(bag);
        this.score1 = 0;
        this.score2 = 0;
        this.actualTurn = 1;
        this.winner = 0;
    }

    public void alternarTurno(){
        if (this.actualTurn == 1){ this.actualTurn= 2; }
        else{ this.actualTurn = 1; }
    }

    public void startGame(){
        this.jugador1.addCharacters(bag.get(7));
        this.jugador2.addCharacters(bag.get(7));
        this.initialTime = System.currentTimeMillis();
    }

    public void game(){
        startGame();
        while (!chooseWinner()){
            this.tablero.mostrarTablero();
            if(this.actualTurn == 1){
                System.out.println("Es turno del jugador 1: " + jugador1.getAlias());
                jugador1.printCharacters();
                System.out.println("Escribe la palabra que quieres poner");
                String word = Main.read.next();
                if (jugador1.validarCaracteres(word)){
                    System.out.println("¿En que posicion la deseas colocar?\s numero de fila: ");
                    int fila = Main.read.nextInt();
                    System.out.println("numero de columna: ");
                    int columna = Main.read.nextInt();
                    System.out.println("""
                            Escribe la direccion de la palabra:\s
                             1. Horizontal\s
                             2. Vertical""");
                    int direccion = Main.read.nextInt();
                    if (direccion == 1) {
                        this.tablero.colocarPalabra(word, fila,columna,true, jugador1);
                        alternarTurno();
                    } else if (direccion == 2) {
                        this.tablero.colocarPalabra(word, fila,columna,false, jugador1);
                        alternarTurno();
                    }

                }
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
        if (this.bag.getNumberOfCharacters() == 0){
            if(this.jugador1.getNumberOfCharacters() == 0){
                this.winner = 1;
                return true;
            } else if (this.jugador2.getNumberOfCharacters() == 0) {
                this.winner = 2;
                return true;
            }
        }
        return false;
    }

}
