import java.util.*;
public class Jugador {
    private String alias;            // Alias del jugador
    private String email;            // Correo electrónico del jugador
    private int score;               // Puntaje total del jugador
    private int palabrasJugadas; // Lista de palabras jugadas por el jugador
    private FichasJugador playerCharacters;
    private long tiempoTotalJugado;   // Tiempo total jugado en segundos

    // Constructor
    public Jugador(String alias, String email) {
        this.alias = alias;
        this.email = email;
        this.score = 0;
        this.palabrasJugadas = 0;
        this.playerCharacters = new FichasJugador();
        this.tiempoTotalJugado = 0;
    }

    public void setPlayerCharacters(FichasJugador playerCharacters) {
        this.playerCharacters = playerCharacters;
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


    public FichasJugador getPlayerCharacters() {
        return playerCharacters;
    }

    public int getNumberOfCharacters() {
        return playerCharacters.cantidadDeFichas();
    }

    public void printCharacters(){
        System.out.println("Fichas del jugador: " + this.alias + "\n" + this.playerCharacters);
    }

    public void addCharacters(ArrayList<Character> newCharacters){
       playerCharacters.reponer(newCharacters);
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
        int i = 0;
        while (i < caracteresNecesarios.length()) {
            char c = caracteresNecesarios.charAt(i);
            // Verificar si el carácter actual es 'c' y el siguiente es 'h'
            if (c == 'c' && (i + 1 < caracteresNecesarios.length() && caracteresNecesarios.charAt(i + 1) == 'h')) {
                if (!playerCharacters.existeCaracterEnJugador("CH")) {
                    return false;
                }
                i += 2; // Saltar el siguiente carácter
                continue;
            }
            // Verificar si el carácter actual es 'r' y el siguiente es 'r'
            if (c == 'r' && (i + 1 < caracteresNecesarios.length() && caracteresNecesarios.charAt(i + 1) == 'r')) {
                if (!playerCharacters.existeCaracterEnJugador("RR")) {
                    return false;
                }
                i += 2; // Saltar el siguiente carácter
                continue;
            }
            // Verificar si el carácter actual es 'l' y el siguiente es 'l'
            if (c == 'l' && (i + 1 < caracteresNecesarios.length() && caracteresNecesarios.charAt(i + 1) == 'l')) {
                if (!playerCharacters.existeCaracterEnJugador("LL")) {
                    return false;
                }
                i += 2; // Saltar el siguiente carácter
                continue;
            }
            // Verificar caracteres individuales
            if (!playerCharacters.existeCaracterEnJugador(String.valueOf(c))) {
                return false;
            }
            i++; // Avanzar al siguiente carácter
        }
        return true; // Si todos los caracteres están presentes, retorna true
    }
}