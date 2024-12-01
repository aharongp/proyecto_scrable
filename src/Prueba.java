public class Prueba {
    public static void main(String[] args) {
        test00();
    }

    public static void test00(){
        Jugador jugador1 = new Jugador("Pepe", "pepe@gmail.com");
        Jugador jugador2 = new Jugador("Paco", "paco@gmail.com");
        Partida partida = new Partida(jugador1, jugador2);
        partida.game();
    }

    public static void test01(){
        Estadisticas estadisticas=new Estadisticas();
        estadisticas.estad("Pepe");
    }

}