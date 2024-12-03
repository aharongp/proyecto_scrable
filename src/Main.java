import java.util.Scanner;

/**
 * Clase principal que inicia y gestiona el flujo del juego, permitiendo a los jugadores iniciar sesión,
 * registrarse, continuar partidas anteriores y ver estadísticas.
 * También permite a los jugadores iniciar nuevas partidas o continuar partidas guardadas.
 */
public class Main {

    // Escáner global para leer entradas del usuario
    public static Scanner read = new Scanner(System.in);

    // Colores para el texto y el fondo en la consola
    public static String RESET = "\u001B[0m";       // Reset a color normal
    public static String TEXTO_ROJO = "\u001B[31m";      // Color rojo
    public static String TEXTO_VERDE = "\u001B[32m";     // Color verde
    public static String TEXTO_AZUL = "\u001B[34m";      // Color azul
    public static String TEXTO_BLANCO = "\u001B[37m"; // Texto blanco
    public static String TEXTO_NEGRO = "\u001B[30m"; // Texto blanco

    public static String FONDO_BLANCO= "\u001B[107m"; // Fondo rojo
    public static String FONDO_VERDE = "\u001B[102m"; // Fondo verde
    public static String FONDO_AMARILLO = "\u001B[103m"; // Fondo verde
    public static String FONDO_CYAN = "\u001B[106m"; // Fondo azul
    public static String FONDO_NEGRO= "\u001B[40m"; // Fondo rojo

    /**
     * Inicia el proceso de inicio de sesión o registro de un jugador.
     * Permite al jugador iniciar sesión si ya tiene una cuenta, o registrarse si es un nuevo jugador.
     *
     * @return Un objeto Jugador con la información del jugador autenticado o registrado.
     */
    public static Jugador inicio() {
        String alias = "";
        String email = "";
        Authentication auth = new Authentication();
        System.out.println(TEXTO_AZUL + "\n Ingrese la opcion que quiere elegir: " + RESET);
        System.out.println(TEXTO_AZUL + " 1.Iniciar sesion \n 2.Registrarse \n\n 0. salir" + RESET);
        int opc = read.nextInt();

        // Bucle hasta que se ingrese una opción válida
        while (opc != 0) {
            if ((opc == 1) || (opc == 2)) {
                System.out.println("Ingresa el correo electronico");
                email = read.next();
                // Validar el correo electrónico
                while (!auth.validateEmail(email)) {
                    System.out.println(TEXTO_ROJO + "Correo invalido, por favor ingrese otro" + RESET);
                    email = read.next();
                }
                System.out.println("Ingresa el alias del jugador");
                alias = read.next();

                // Iniciar sesión o registrarse según la opción
                if (opc == 1) {
                    Jugador jugador = auth.login(alias, email);
                    while (jugador == null) {
                        System.out.print("Usuario no existe, ingrese un usuario valido");
                        jugador = inicio();  // Recursión para intentar nuevamente
                    }
                    jugador.limpiarFichas(); // Limpiar fichas del jugador al iniciar
                    return jugador;
                }
                if (opc == 2) {
                    return auth.register(alias, email);  // Registrar un nuevo jugador
                }
            } else {
                System.out.println("Ingrese una opcion valida");
                System.out.println("\n\n 1.Iniciar sesion \n 2.Registrarse \n\n 0. salir");
                opc = read.nextInt();
            }
        }
        return null; // Retorna null si el jugador decide salir
    }

    /**
     * Método principal que gestiona el flujo del juego.
     * Permite a dos jugadores iniciar sesión, registrarse, ver estadísticas, o continuar una partida.
     * También permite iniciar una nueva partida o continuar una guardada.
     *
     * @param args Argumentos de línea de comandos (no utilizados en este caso).
     */
    public static void main(String[] args) {

        // Mensajes iniciales con colores en la consola
        System.out.println(FONDO_VERDE + TEXTO_NEGRO + "****************************************" + RESET);
        System.out.println(FONDO_VERDE + TEXTO_NEGRO + "***********Inicio del juego*************" + RESET);
        System.out.println(FONDO_VERDE + TEXTO_NEGRO + "***************Jugador 1****************" + RESET);
        Jugador jugador1 = inicio();  // Iniciar sesión o registrar al Jugador 1
        System.out.println(FONDO_VERDE + TEXTO_NEGRO + "***************Jugador 2****************" + RESET);
        Jugador jugador2 = inicio();  // Iniciar sesión o registrar al Jugador 2

        // Menú para las opciones disponibles después de que ambos jugadores inicien sesión
        System.out.println("\n" + FONDO_CYAN + TEXTO_NEGRO + "¿Qué les gustaría hacer?" + RESET);
        System.out.println(FONDO_CYAN + TEXTO_NEGRO + "1. Iniciar una partida" + RESET);
        System.out.println(FONDO_CYAN + TEXTO_NEGRO + "2. Continuar partida anterior" + RESET);
        System.out.println(FONDO_CYAN + TEXTO_NEGRO + "3. Ver estadísticas de los jugadores" + RESET);
        System.out.println(FONDO_CYAN + TEXTO_NEGRO + "0. Salir" + RESET);
        int opc = read.nextInt();

        // Bucle de opciones hasta que se decida salir
        while (opc != 0) {
            if (opc == 1) {
                Partida partida = new Partida(jugador1, jugador2); // Iniciar una nueva partida
                partida.game();
            }
            if (opc == 2) {
                ManejadorDeArchivos archivos = new ManejadorDeArchivos();
                Partida continuar = archivos.buscarPartida(jugador1.getAlias(), jugador2.getAlias());
                if (continuar == null) {
                    System.out.println("No hay ninguna partida guardada para cargar.");
                } else {
                    continuar.continuarPartida();  // Continuar una partida guardada
                }
                // Volver a mostrar el menú de opciones
                System.out.println("Ingrese una opcion valida");
                System.out.println("\n" + FONDO_CYAN + TEXTO_NEGRO + "¿Qué les gustaría hacer?" + RESET);
                System.out.println(FONDO_CYAN + TEXTO_NEGRO + "1. Iniciar una partida" + RESET);
                System.out.println(FONDO_CYAN + TEXTO_NEGRO + "2. Continuar partida anterior" + RESET);
                System.out.println(FONDO_CYAN + TEXTO_NEGRO + "3. Ver estadísticas de los jugadores" + RESET);
                System.out.println(FONDO_CYAN + TEXTO_NEGRO + "0. Salir" + RESET);
                opc = read.nextInt();
            }
            if (opc == 3) {
                Estadisticas estadisticas = new Estadisticas();
                System.out.println(FONDO_VERDE + TEXTO_NEGRO + "***************Jugador 1****************");
                estadisticas.estad(jugador1.getAlias());  // Mostrar estadísticas del Jugador 1
                System.out.println(FONDO_AMARILLO + TEXTO_NEGRO + "***************Jugador 2****************");
                estadisticas.estad(jugador2.getAlias());  // Mostrar estadísticas del Jugador 2

                // Volver a mostrar el menú de opciones
                System.out.println(RESET + "\n" + FONDO_CYAN + TEXTO_NEGRO + "¿Qué les gustaría hacer?" + RESET);
                System.out.println(FONDO_CYAN + TEXTO_NEGRO + "1. Iniciar una partida" + RESET);
                System.out.println(FONDO_CYAN + TEXTO_NEGRO + "2. Continuar partida anterior" + RESET);
                System.out.println(FONDO_CYAN + TEXTO_NEGRO + "3. Ver estadísticas de los jugadores" + RESET);
                System.out.println(FONDO_CYAN + TEXTO_NEGRO + "0. Salir" + RESET);
                opc = read.nextInt();
            } else {
                System.out.println("Ingrese una opción válida");
                System.out.println("\n" + FONDO_CYAN + TEXTO_NEGRO + "¿Qué les gustaría hacer?" + RESET);
                System.out.println(FONDO_CYAN + TEXTO_NEGRO + "1. Iniciar una partida" + RESET);
                System.out.println(FONDO_CYAN + TEXTO_NEGRO + "2. Continuar partida anterior" + RESET);
                System.out.println(FONDO_CYAN + TEXTO_NEGRO + "3. Ver estadísticas de los jugadores" + RESET);
                System.out.println(FONDO_CYAN + TEXTO_NEGRO + "0. Salir" + RESET);
                opc = read.nextInt();
            }
        }
    }
}
