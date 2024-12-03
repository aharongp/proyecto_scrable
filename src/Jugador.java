import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.*;

/**
 * Clase que representa a un jugador en el juego.
 * Un jugador tiene un alias, un correo electrónico, un puntaje total, una cantidad de palabras jugadas,
 * las fichas que posee, y el tiempo total jugado en segundos.
 * Esta clase incluye métodos para gestionar las fichas del jugador, su puntaje, y el progreso durante el juego.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Jugador {
    private String alias;            // Alias del jugador
    private String email;            // Correo electrónico del jugador
    private int score;               // Puntaje total del jugador
    private int palabrasJugadas;     // Cantidad de palabras jugadas por el jugador
    private FichasJugador playerCharacters; // Fichas que posee el jugador
    private long tiempoTotalJugado;   // Tiempo total jugado en segundos

    /**
     * Constructor vacío por defecto.
     */
    public Jugador(){

    }

    /**
     * Constructor para crear un jugador con un alias y un correo electrónico.
     * Inicializa el puntaje, palabras jugadas, fichas y tiempo total jugado en valores predeterminados.
     *
     * @param alias Alias del jugador.
     * @param email Correo electrónico del jugador.
     */
    public Jugador(String alias, String email) {
        this.alias = alias;
        this.email = email;
        this.score = 0;
        this.palabrasJugadas = 0;
        this.playerCharacters = new FichasJugador();
        this.tiempoTotalJugado = 0;
    }

    /**
     * Establece las fichas del jugador.
     *
     * @param playerCharacters Las nuevas fichas del jugador.
     */
    public void setPlayerCharacters(FichasJugador playerCharacters) {
        this.playerCharacters = playerCharacters;
    }

    // Métodos de acceso (getters)

    /**
     * Obtiene el alias del jugador.
     *
     * @return El alias del jugador.
     */
    public String getAlias() {
        return alias;
    }

    /**
     * Obtiene el correo electrónico del jugador.
     *
     * @return El correo electrónico del jugador.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Obtiene el puntaje total del jugador.
     *
     * @return El puntaje total del jugador.
     */
    public int getScore() {
        return score;
    }

    /**
     * Obtiene la cantidad de palabras jugadas por el jugador.
     *
     * @return La cantidad de palabras jugadas.
     */
    public int getPalabrasJugadas() {
        return palabrasJugadas;
    }

    /**
     * Obtiene el tiempo total jugado por el jugador en segundos.
     *
     * @return El tiempo total jugado en segundos.
     */
    public long getTiempoTotalJugado() {
        return tiempoTotalJugado;
    }

    /**
     * Limpia las fichas del jugador, reiniciándolas a un estado vacío.
     */
    public void limpiarFichas(){
        this.playerCharacters = new FichasJugador();
    }

    /**
     * Obtiene las fichas del jugador.
     *
     * @return Las fichas del jugador.
     */
    public FichasJugador getPlayerCharacters() {
        return playerCharacters;
    }

    /**
     * Obtiene la cantidad de fichas que tiene el jugador.
     *
     * @return El número de fichas del jugador.
     */
    public int numberOfCharacters() {
        return playerCharacters.cantidadDeFichas();
    }

    /**
     * Imprime las fichas del jugador en la consola.
     */
    public void printCharacters(){
        System.out.println("Fichas del jugador: " + this.alias + "\n" + this.playerCharacters);
    }

    /**
     * Agrega nuevas fichas al jugador.
     *
     * @param newCharacters Lista de nuevas fichas a agregar.
     */
    public void addCharacters(ArrayList<Character> newCharacters){
        playerCharacters.reponer(newCharacters);
    }

    /**
     * Agrega puntos al puntaje del jugador.
     *
     * @param puntos Los puntos a agregar.
     */
    public void addPoints(int puntos) {
        this.score += puntos;
    }

    /**
     * Aumenta la cantidad de palabras jugadas por el jugador.
     */
    public void addWordPlayed() {
        this.palabrasJugadas +=1 ;
    }

    /**
     * Aumenta el tiempo total jugado por el jugador en segundos.
     *
     * @param seconds El tiempo en segundos a agregar.
     */
    public void addTimePlated(long seconds) {
        this.tiempoTotalJugado += seconds;
    }

    /**
     * Representa al jugador como una cadena de texto.
     *
     * @return Una cadena con los detalles del jugador.
     */
    public String toString() {
        return "Jugador{" +
                "alias='" + alias + '\'' +
                ", email='" + email + '\'' +
                ", score=" + score +
                ", palabrasJugadas=" + palabrasJugadas +
                ", tiempoTotalJugado=" + tiempoTotalJugado + " segundos" +
                '}';
    }

    /**
     * Valida si el jugador tiene los caracteres necesarios para formar una palabra.
     * Los caracteres especiales como "CH", "RR", y "LL" son verificados de forma especial.
     * Si el jugador no tiene los caracteres requeridos, el método retorna false.
     *
     * @param caracteresNecesarios La cadena de caracteres necesarios para formar una palabra.
     * @return true si el jugador tiene los caracteres necesarios, false en caso contrario.
     */
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
                if (!playerCharacters.existeCaracterEnJugador("+")){
                    return false;
                }
            }
            i++; // Avanzar al siguiente carácter
        }
        return true; // Si todos los caracteres están presentes, retorna true
    }
}
