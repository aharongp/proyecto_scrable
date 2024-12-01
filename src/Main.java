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
                while (auth.validateEmail(email)){
                    System.out.println("Correo invalido, por favor ingrese otro");
                }
                System.out.println("Ingresa el alias del jugador");
                alias = read.next();
                if( opc == 1) { return auth.login(alias,email);}
                if(opc ==2) { return auth.register(alias,email);}
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

            } if (opc == 2){

            } if (opc == 3) {

            } else {
                System.out.println("Ingrese una opcion valida");
                System.out.println("\n\n 1.Iniciar sesion \n 2.Registrarse \n\n 0. salir");
                opc = read.nextInt();
            }
        }

    }
}