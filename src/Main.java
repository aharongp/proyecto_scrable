import java.util.Scanner;

public class Main {

    public static Scanner read = new Scanner(System.in);

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




    public static Jugador inicio(){
        String alias ="";
        String email = "";
        Authentication auth = new Authentication();
        System.out.println(TEXTO_AZUL+"\n Ingrese la opcion que quiere elegir: "+ RESET);
        System.out.println(TEXTO_AZUL+" 1.Iniciar sesion \n 2.Registrarse \n\n 0. salir" + RESET);
        int opc = read.nextInt();
        while(opc != 0){
            if((opc == 1) | (opc == 2)){
                System.out.println("Ingresa el correo electronico");
                email = read.next();
                while (!auth.validateEmail(email)){
                    System.out.println(TEXTO_ROJO+"Correo invalido, por favor ingrese otro"+ RESET);
                    email = read.next();
                }
                System.out.println("Ingresa el alias del jugador");
                alias = read.next();
                if( opc == 1) {
                    Jugador jugador = auth.login(alias,email);
                    while (jugador == null){
                        System.out.print("usuario no existe, ingrese un usuario valido");
                        jugador = inicio();
                    }
                    jugador.limpiarFichas();
                    return jugador;
                }
                if(opc == 2) { return auth.register(alias,email);}
            } else {
                System.out.println("Ingrese una opcion valida");
                System.out.println("\n\n 1.Iniciar sesion \n 2.Registrarse \n\n 0. salir");
                opc = read.nextInt();
            }
        }
        return null;
    }


    public static void main(String[] args) {

        System.out.println(FONDO_VERDE+TEXTO_NEGRO + "****************************************" +RESET);
        System.out.println(FONDO_VERDE+TEXTO_NEGRO + "***********Inicio del juego*************" + RESET);
        System.out.println(FONDO_VERDE+TEXTO_NEGRO + "***************Jugador 1****************"+ RESET);
        Jugador jugador1 = inicio();
        System.out.println(FONDO_VERDE+TEXTO_NEGRO + "***************Jugador 2****************"+ RESET);
        Jugador jugador2 = inicio();
        System.out.println("\n"+FONDO_CYAN+ TEXTO_NEGRO+"¿Que les gustaria hacer?"+ RESET);
        System.out.println(FONDO_CYAN+ TEXTO_NEGRO+"1. Iniciar una partida"+ RESET);
        System.out.println(FONDO_CYAN+ TEXTO_NEGRO+"2. Continuar partida anterior"+ RESET);
        System.out.println(FONDO_CYAN+ TEXTO_NEGRO+"3. Ver estadisticas de los jugadores"+ RESET);
        System.out.println(FONDO_CYAN+ TEXTO_NEGRO+"0. Salir"+ RESET);
        int opc = read.nextInt();
        while(opc != 0){
            if(opc == 1){
                Partida partida = new Partida(jugador1,jugador2);
                partida.game();
            } if (opc == 2){
                ManejadorDeArchivos archivos = new ManejadorDeArchivos();
                Partida continuar = archivos.buscarPartida(jugador1.getAlias(), jugador2.getAlias());
                if (continuar == null) {
                    System.out.println("No hay ninguna partida guardada para cargar.");
                } else {
                    continuar.continuarPartida();
                }
                System.out.println("Ingrese una opcion valida");
                System.out.println("\n"+FONDO_CYAN+ TEXTO_NEGRO+"¿Que les gustaria hacer?"+ RESET);
                System.out.println(FONDO_CYAN+ TEXTO_NEGRO+"1. Iniciar una partida"+ RESET);
                System.out.println(FONDO_CYAN+ TEXTO_NEGRO+"2. Continuar partida anterior"+ RESET);
                System.out.println(FONDO_CYAN+ TEXTO_NEGRO+"3. Ver estadisticas de los jugadores"+ RESET);
                System.out.println(FONDO_CYAN+ TEXTO_NEGRO+"0. Salir"+ RESET);
                opc = read.nextInt();
            } if (opc == 3) {
                Estadisticas estadisticas = new Estadisticas();
                System.out.println("***************Jugador 1****************");
                estadisticas.estad(jugador1.getAlias());
                System.out.println("***************Jugador 2****************");
                estadisticas.estad(jugador2.getAlias());
                System.out.println("Ingrese una opcion valida");
                System.out.println("\n"+FONDO_CYAN+ TEXTO_NEGRO+"¿Que les gustaria hacer?"+ RESET);
                System.out.println(FONDO_CYAN+ TEXTO_NEGRO+"1. Iniciar una partida"+ RESET);
                System.out.println(FONDO_CYAN+ TEXTO_NEGRO+"2. Continuar partida anterior"+ RESET);
                System.out.println(FONDO_CYAN+ TEXTO_NEGRO+"3. Ver estadisticas de los jugadores"+ RESET);
                System.out.println(FONDO_CYAN+ TEXTO_NEGRO+"0. Salir"+ RESET);
                opc = read.nextInt();
            } else {
                System.out.println("Ingrese una opcion valida");
                System.out.println("\n"+FONDO_CYAN+ TEXTO_NEGRO+"¿Que les gustaria hacer?"+ RESET);
                System.out.println(FONDO_CYAN+ TEXTO_NEGRO+"1. Iniciar una partida"+ RESET);
                System.out.println(FONDO_CYAN+ TEXTO_NEGRO+"2. Continuar partida anterior"+ RESET);
                System.out.println(FONDO_CYAN+ TEXTO_NEGRO+"3. Ver estadisticas de los jugadores"+ RESET);
                System.out.println(FONDO_CYAN+ TEXTO_NEGRO+"0. Salir"+ RESET);
                opc = read.nextInt();
            }
        }

    }
}