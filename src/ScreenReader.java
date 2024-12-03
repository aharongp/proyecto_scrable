/**
 * Clase abstracta que proporciona métodos estáticos para la lectura de entradas desde la consola.
 * Utiliza un objeto `Scanner` para leer los datos introducidos por el usuario.
 */
public abstract class ScreenReader {

    /**
     * Objeto `Scanner` utilizado para leer la entrada del usuario desde la consola.
     * Es estático para permitir el acceso global en toda la aplicación.
     */
    public static Scanner read = new Scanner(System.in);
}
