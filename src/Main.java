import java.util.Scanner;

public class Main {

    public static Scanner read = new Scanner(System.in);

    public static Jugador inicio(){
        String alias ="";
        String email = "";
        Authentication auth = new Authentication();
        System.out.println("\n Ingrese la opcion que quiere elegir:");
        System.out.println(" 1.Iniciar sesion \n 2.Registrarse \n\n 0. salir");
        int opc = read.nextInt();
        while(opc != 0){
            if((opc == 1) | (opc == 2)){
                System.out.println("Ingresa el correo electronico");
                email = read.next();
                while (!auth.validateEmail(email)){
                    System.out.println("Correo invalido, por favor ingrese otro");
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

        System.out.println("****************************************");
        System.out.println("***********Inicio del juego*************");
        System.out.println("***************Jugador 1****************");
        Jugador jugador1 = inicio();
        System.out.println("***************Jugador 2****************");
        Jugador jugador2 = inicio();
        System.out.println("¿Que les gustaria hacer?");
        System.out.println("1. Iniciar una partida");
        System.out.println("2. Continuar partida anterior");
        System.out.println("3. Ver estadisticas de los jugadores");
        System.out.println("0. Salir");
        int opc = read.nextInt();
        while(opc != 0){
            if(opc == 1){
                Partida partida = new Partida(jugador1,jugador2);
                partida.game();
            } if (opc == 2){
                ManejadorDeArchivos archivos = new ManejadorDeArchivos();
                Partida continuar = archivos.buscarPartida(jugador1.getAlias(), jugador2.getAlias());
                if (continuar == null) {
                    System.out.println("No hay ninguna partida que cargar");
                } else {
                    continuar.continuarPartida();
                }
            } if (opc == 3) {
                Estadisticas estadisticas = new Estadisticas();
                System.out.println("***************Jugador 1****************");
                estadisticas.estad(jugador1.getAlias());
                System.out.println("***************Jugador 2****************");
                estadisticas.estad(jugador2.getAlias());
                System.out.println("Ingrese una opcion valida");
                System.out.println("\n\n 1.Iniciar sesion \n 2.Registrarse \n\n 0. salir");
                opc = read.nextInt();
            } else {
                System.out.println("Ingrese una opcion valida");
                System.out.println("\n\n 1.Iniciar sesion \n 2.Registrarse \n\n 0. salir");
                opc = read.nextInt();
            }
        }

    }
}