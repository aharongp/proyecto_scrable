import java.util.*;

public class Jugador {
    private String alias;            // Alias del jugador
    private String email;            // Correo electrónico del jugador
    private int score;               // Puntaje total del jugador
    private int palabrasJugadas; // Lista de palabras jugadas por el jugador
    private ArrayList<Character> playerCharacters;
    private long tiempoTotalJugado;   // Tiempo total jugado en segundos

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

    public long getTiempoTotalJugado() {
        return tiempoTotalJugado;
    }

    public ArrayList<Character> getPlayerCharacters() {
        return playerCharacters;
    }

    public void printCharacters(){
        System.out.println("Fichas del jugador: " + this.alias + "\n" + this.playerCharacters);
    }

    public void addCharacters(ArrayList<Character> newCharacters){
        this.playerCharacters.addAll(newCharacters);
    }

    public void clearPlayerCharacters(ArrayList<Character> playerCharacters) {
        this.playerCharacters.clear();
    }

    public void addPoints(int puntos) {
        this.score += puntos;
    }

    public void addWordPlayed() {
        this.palabrasJugadas +=1 ;
    }

    public void addTimePlated(long seconds) {
        this.tiempoTotalJugado += seconds;
    }

    public String toString() {
        return "Jugador{" +
                "alias='" + alias + '\'' +
                ", email='" + email + '\'' +
                ", score=" + score +
                ", palabrasJugadas=" + palabrasJugadas +
                ", tiempoTotalJugado=" + tiempoTotalJugado + " segundos" +
                '}';
    }

    public boolean validarCaracteres(String caracteresNecesarios) {
        ArrayList<Character> caracteresJugador = this.getPlayerCharacters();

        for (char c : caracteresNecesarios.toCharArray()) {
            boolean encontrado = false;
            for (Character caracter : caracteresJugador) {
                if (caracter.getSymbol().charAt(0) == c) {
                    encontrado = true;
                    break;
                }
            }
            if (!encontrado) {
                return false; // Si no se encuentra un carácter, retorna false
            }
        }
        return true; // Si todos los caracteres están presentes, retorna true
    }

}
