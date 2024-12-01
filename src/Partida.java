import java.util.ArrayList;
import java.util.UUID;

public class Partida {
    private ManejadorDeArchivos manejadorDeArchivos=new ManejadorDeArchivos();
    UUID uuid = UUID.randomUUID();
    private String id;
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


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Jugador getJugador1() {
        return jugador1;
    }

    public void setJugador1(Jugador jugador1) {
        this.jugador1 = jugador1;
    }

    public Jugador getJugador2() {
        return jugador2;
    }

    public void setJugador2(Jugador jugador2) {
        this.jugador2 = jugador2;
    }

    public int getScore1() {
        return score1;
    }

    public void setScore1(int score1) {
        this.score1 = score1;
    }

    public int getScore2() {
        return score2;
    }

    public void setScore2(int score2) {
        this.score2 = score2;
    }

    public SpanishBag getBag() {
        return bag;
    }

    public void setBag(SpanishBag bag) {
        this.bag = bag;
    }

    public Tablero getTablero() {
        return tablero;
    }

    public void setTablero(Tablero tablero) {
        this.tablero = tablero;
    }

    public int getActualTurn() {
        return actualTurn;
    }

    public void setActualTurn(int actualTurn) {
        this.actualTurn = actualTurn;
    }

    public long getInitialTime() {
        return initialTime;
    }

    public void setInitialTime(long initialTime) {
        this.initialTime = initialTime;
    }

    public long getFinishtime() {
        return finishtime;
    }

    public void setFinishtime(long finishtime) {
        this.finishtime = finishtime;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public int getWinner() {
        return winner;
    }

    public void setWinner(int winner) {
        this.winner = winner;
    }

    public Partida(){
    }

    public Partida(Jugador jugador1, Jugador jugador2) {
        this.id= uuid.toString();
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

    public void continuarPartida(){
        while (!chooseWinner()) {
            this.tablero.mostrarTablero();
            if (actualTurn == 1) {
                menuDeJugador(jugador1);
            }
            else {
                menuDeJugador(jugador2);
            }
            String jsonPartida=JSONMapper.objectoToJson(this);
            System.out.println("Partida en JSON=" + jsonPartida);
            manejadorDeArchivos.salvarPartida(this);
        }
        finishGame();
    }

    public void menuDeJugador(Jugador jugador){
        System.out.println("Es turno del jugador " + jugador.getAlias());
        jugador.printCharacters();
        int opcion;
        boolean finalizar=false;
        do {
            System.out.println("=========================");
            System.out.println("        MENU JUEGO      ");
            System.out.println("=========================");
            System.out.println("1. Colocar Palabra");
            System.out.println("2. Cambiar Fichas");
            System.out.println("3. Pasar Turno");
            System.out.println("4. Salir de la Partida");
            System.out.println("=========================");
            System.out.print("Por favor, selecciona una opcion (1-4): ");
            //opcion = scanner.nextInt();
            opcion=ScreenReader.read.nextInt();
            switch (opcion) {
                case 1:
                    colocarPalabra(jugador);
                    alternarTurno();
                    finalizar=true;
                    break;
                case 2:
                    finalizar=cambiarFichasDeJugador(jugador);
                    jugador.printCharacters();
                    alternarTurno();
                    break;
                case 3:
                    alternarTurno();
                    finalizar=true;
                    break;
                case 4:
                    salirDePartida();
                    finalizar=true;
                    break;
                default:
                    System.out.println("Opcion invalida. Por favor, selecciona una opcion valida.");
            }
        } while (!finalizar);

    }

    public boolean cambiarFichasDeJugador(Jugador jugador){
        System.out.println("Fichas del jugador:"+jugador.getAlias());
        jugador.printCharacters();
        System.out.println("Indique las fichas a cambiar:");
        String fichasACambiar=ScreenReader.read.next();
        //String[] cambio=fichasACambiar.split(",");
        ArrayList<Character> fichasJ=jugador.getPlayerCharacters().getFichas();
        //public boolean reemplazarFichas(String fichasPorExtraer, CharactersBag bag)
        FichasJugador fichasJugador=jugador.getPlayerCharacters();
        return fichasJugador.reemplazarFichas(fichasACambiar,bag);
    }
    public void salirDePartida(){

    }


    public void colocarPalabra(Jugador jugador) {
        //System.out.println("Es turno del jugador " + jugador.getAlias());
        //jugador.printCharacters();
        System.out.println("Escribe la palabra que quieres poner");
        String word = ScreenReader.read.next();
        //if (jugador.validarCaracteres(word)) {
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
                this.tablero.ubicarPalabra(word, fila, columna, true, jugador);
            }
            else if (direccion == 2) {
                this.tablero.ubicarPalabra(word, fila, columna, false, jugador);
            }
            reponerFichas(jugador);
        //}
    }

    public void reponerFichas(Jugador jugador) {
        int fichasNecesarias = 7 - jugador.numberOfCharacters();
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
                menuDeJugador(jugador1);

            }
            else {
                menuDeJugador(jugador2);
            }
            String jsonPartida=JSONMapper.objectoToJson(this);
            System.out.println("Partida en JSON=" + jsonPartida);
            manejadorDeArchivos.salvarPartida(this);
            manejadorDeArchivos.salvarJugador(getJugador1());
            manejadorDeArchivos.salvarJugador(getJugador2());
        }
        finishGame();
    }

    public void finishGame() {
        this.finishtime = System.currentTimeMillis();
        this.time = this.finishtime - this.initialTime;
    }

    public boolean chooseWinner() {
        if (this.bag.remaning() == 0) {
            if (this.jugador1.numberOfCharacters() == 0) {
                this.winner = 1;
                return true;
            }
            else if (this.jugador2.numberOfCharacters() == 0) {
                this.winner = 2;
                return true;
            }
        }
        return false;
    }
}
