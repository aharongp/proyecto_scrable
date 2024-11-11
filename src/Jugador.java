import java.util.*;

public class Jugador {
    private String alias;            // Alias del jugador
    private String email;            // Correo electrónico del jugador
    private int score;               // Puntaje total del jugador
    private int palabrasJugadas; // Lista de palabras jugadas por el jugador
    private ArrayList<Character> playerCharacters;
    private int tiempoTotalJugado;   // Tiempo total jugado en segundos

    // Constructor
    public Jugador(String alias, String email) {
        this.alias = alias;
        this.email = email;
        this.score = 0;
        this.palabrasJugadas = 0;
        this.playerCharacters = new ArrayList<Character>();
        this.tiempoTotalJugado = 0;
    }

    // Métodos de acceso (getters y setters)
    public String getAlias() {
        return alias;
    }

    public String getEmail() {
        return email;
    }

    public int getScore() {
        return score;
    }

    public int getPalabrasJugadas() {
        return palabrasJugadas;
    }

    public int getTiempoTotalJugado() {
        return tiempoTotalJugado;
    }

    public ArrayList<Character> getPlayerCharacters() {
        return playerCharacters;
    }

    public void clearPlayerCharacters(ArrayList<Character> playerCharacters) {
        this.playerCharacters.clear();
    }

    // Método para agregar puntaje
    public void agregarPuntaje(int puntos) {
        this.score += puntos;
    }

    // Método para agregar una palabra jugada
    public void sumarPalabraJugada(String palabra) {
        this.palabrasJugadas +=1 ;
    }

    // Método para aumentar el tiempo total jugado
    public void agregarTiempoJugado(int segundos) {
        this.tiempoTotalJugado += segundos;
    }

    // Método para mostrar la información del jugador
    @Override
    public String toString() {
        return "Jugador{" +
                "alias='" + alias + '\'' +
                ", email='" + email + '\'' +
                ", score=" + score +
                ", palabrasJugadas=" + palabrasJugadas +
                ", tiempoTotalJugado=" + tiempoTotalJugado + " segundos" +
                '}';
    }
}
