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

    public int getNumberOfCharacters(){
        return playerCharacters.size();
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
        int i = 0;

        while (i < caracteresNecesarios.length()) {
            char c = caracteresNecesarios.charAt(i);

            // Verificar si el carácter actual es 'c' y el siguiente es 'h'
            if (c == 'c' && (i + 1 < caracteresNecesarios.length() && caracteresNecesarios.charAt(i + 1) == 'h')) {
                if (!existeCaracterEnJugador("ch", caracteresJugador)) {
                    return false;
                }
                i += 2; // Saltar el siguiente carácter
                continue;
            }

            // Verificar si el carácter actual es 'r' y el siguiente es 'r'
            if (c == 'r' && (i + 1 < caracteresNecesarios.length() && caracteresNecesarios.charAt(i + 1) == 'r')) {
                if (!existeCaracterEnJugador("rr", caracteresJugador)) {
                    return false;
                }
                i += 2; // Saltar el siguiente carácter
                continue;
            }

            // Verificar si el carácter actual es 'l' y el siguiente es 'l'
            if (c == 'l' && (i + 1 < caracteresNecesarios.length() && caracteresNecesarios.charAt(i + 1) == 'l')) {
                if (!existeCaracterEnJugador("ll", caracteresJugador)) {
                    return false;
                }
                i += 2; // Saltar el siguiente carácter
                continue;
            }

            // Verificar caracteres individuales
            if (!existeCaracterEnJugador(String.valueOf(c), caracteresJugador)) {
                return false; // Si no se encuentra un carácter, retorna false
            }

            i++; // Avanzar al siguiente carácter
        }
        return true; // Si todos los caracteres están presentes, retorna true
    }

    private boolean existeCaracterEnJugador(String simbolo, ArrayList<Character> caracteresJugador) {
        for (Character caracter : caracteresJugador) {
            if (caracter.getSymbol().equals(simbolo)) {
                return true;
            }
        }
        return false;
    }

}
