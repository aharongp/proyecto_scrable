import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Diccionario diccionario = new Diccionario();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Bienvenido al validador de palabras usando la API de Oxford.");
        System.out.println("Ingrese una palabra para verificar si existe en el diccionario (escriba 'salir' para terminar):");

        while (true) {
            System.out.print("Ingrese palabra: ");
            String palabra = scanner.nextLine().trim();

            if (palabra.equalsIgnoreCase("salir")) {
                System.out.println("Saliendo del programa...");
                break;
            }

            boolean existe = diccionario.verificarPalabra(palabra);

            if (existe) {
                System.out.println("La palabra '" + palabra + "' existe en el diccionario.");
            } else {
                System.out.println("La palabra '" + palabra + "' no existe en el diccionario.");
            }
        }

        scanner.close();
    }
}

