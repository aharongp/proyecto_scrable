package Proyect;
import java.util.ArrayList;
import java.util.List;

public class Jugador {
    private String alias;            // Alias del jugador
    private String email;            // Correo electrónico del jugador
    private int score;               // Puntaje total del jugador
    private List<String> palabrasJugadas; // Lista de palabras jugadas por el jugador
    private int tiempoTotalJugado;   // Tiempo total jugado en segundos

    // Constructor
    public Jugador(String alias, String email) {
        this.alias = alias;
        this.email = email;
        this.score = 0;
        this.palabrasJugadas = new ArrayList<>();
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

    public List<String> getPalabrasJugadas() {
        return palabrasJugadas;
    }

    public int getTiempoTotalJugado() {
        return tiempoTotalJugado;
    }

    // Método para agregar puntaje
    public void agregarPuntaje(int puntos) {
        this.score += puntos;
    }

    // Método para agregar una palabra jugada
    public void agregarPalabraJugadas(String palabra) {
        palabrasJugadas.add(palabra);
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
