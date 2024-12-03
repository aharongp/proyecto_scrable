import java.util.Scanner;

/**
 * Clase principal que inicia y gestiona el flujo del juego, permitiendo a los jugadores iniciar sesión,
 * registrarse, continuar partidas anteriores y ver estadísticas.
 * También permite a los jugadores iniciar nuevas partidas o continuar partidas guardadas.
 */
public class Main {

    public static ManejadorDeArchivos archivos = new ManejadorDeArchivos();
    public static Scanner read = new Scanner(System.in);
    public static boolean validInput = false;

    public static String RESET = "\u001B[0m";
    public static String TEXTO_ROJO = "\u001B[31m";
    public static String TEXTO_VERDE = "\u001B[32m";
    public static String TEXTO_AZUL = "\u001B[34m";
    public static String TEXTO_NEGRO = "\u001B[30m";

    public static String FONDO_BLANCO= "\u001B[107m";
    public static String FONDO_VERDE = "\u001B[102m";
    public static String FONDO_AMARILLO = "\u001B[103m";
    public static String FONDO_CYAN = "\u001B[106m";

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
                return auth.register(alias,email);
            } else {
                System.out.println("Ingrese una opcion valida");
                System.out.println("\n\n 1.Iniciar sesion \n 2.Registrarse \n\n 0. salir");
                opc = read.nextInt();
            }
        }
        return null;
    }

    public static int leerNumero(){
        int opc = -1;
        while (!validInput) {
            try {
                opc = read.nextInt();
                validInput = true;
            } catch (Exception e) {
                System.out.println("Entrada inválida. Por favor, introduce un número.");
                read.nextLine();
            }
        }
        validInput = false;
        return opc;
    };
    public static void menu(){
        System.out.println("\n"+FONDO_CYAN+ TEXTO_NEGRO+"¿Que les gustaria hacer?"+ RESET);
        System.out.println(FONDO_CYAN+ TEXTO_NEGRO+"1. Iniciar una partida"+ RESET);
        System.out.println(FONDO_CYAN+ TEXTO_NEGRO+"2. Continuar partida anterior"+ RESET);
        System.out.println(FONDO_CYAN+ TEXTO_NEGRO+"3. Ver estadisticas de los jugadores"+ RESET);
        System.out.println(FONDO_CYAN+ TEXTO_NEGRO+"0. Salir"+ RESET);
    }


    /**
     * Método principal que gestiona el flujo del juego.
     * Permite a dos jugadores iniciar sesión, registrarse, ver estadísticas, o continuar una partida.
     * También permite iniciar una nueva partida o continuar una guardada.
     *
     * @param args Argumentos de línea de comandos (no utilizados en este caso).
     */
    public static void main(String[] args) {

        System.out.println(FONDO_VERDE+TEXTO_NEGRO + "****************************************" +RESET);
        System.out.println(FONDO_VERDE+TEXTO_NEGRO + "***********Inicio del juego*************" + RESET);
        System.out.println(FONDO_VERDE+TEXTO_NEGRO + "***************Jugador 1****************"+ RESET);
        Jugador jugador1 = inicio();
        System.out.println(FONDO_VERDE+TEXTO_NEGRO + "***************Jugador 2****************"+ RESET);
        Jugador jugador2 = inicio();
            System.out.println("\n"+FONDO_CYAN+ TEXTO_NEGRO+"¿Que les gustaria hacer?"+ RESET);
            menu();
        int opc = leerNumero();
        while(opc != 0){
            if(opc == 1){
                Partida partida = new Partida(jugador1,jugador2);
                partida.game();
                menu();
                opc = leerNumero();
            }else if (opc == 2){
                if(jugador1!=null && jugador2 != null){
                    Partida continuar = archivos.buscarPartida(jugador1.getAlias(), jugador2.getAlias());
                    if (continuar == null) {
                        System.out.println("No hay ninguna partida guardada para cargar.");
                    } else {
                        continuar.continuarPartida();
                    }
                }
                System.out.println(RESET+"\n"+FONDO_CYAN+ TEXTO_NEGRO+"¿Que les gustaria hacer?"+ RESET);
                menu();
                opc = leerNumero();
            }else if (opc == 3) {
                Estadisticas estadisticas = new Estadisticas();
                if ((jugador1 != null) && (jugador2 != null)){
                System.out.println(FONDO_VERDE+TEXTO_NEGRO + "***************Jugador 1****************");
                estadisticas.estad(jugador1.getAlias());
                System.out.println(FONDO_AMARILLO+TEXTO_NEGRO + "***************Jugador 2****************");
                estadisticas.estad(jugador2.getAlias());
                }
                System.out.println(RESET+"\n"+FONDO_CYAN+ TEXTO_NEGRO+"¿Que les gustaria hacer?"+ RESET);
                menu();
                opc = leerNumero();
            } else {
                System.out.println("Ingrese una opcion valida");
                menu();
                opc = leerNumero();
            }
        }

    }
}