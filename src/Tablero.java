public class Tablero {
    private static final int TAMANO = 15;
    private char[][] tablero;

    public Tablero() {
        tablero = new char[TAMANO][TAMANO];
        inicializarTablero();
    }

    private void inicializarTablero() {
        for (int i = 0; i < TAMANO; i++) {
            for (int j = 0; j < TAMANO; j++) {
                tablero[i][j] = ' ';
            }
        }
    }

    public void mostrarTablero() {
        for (int i = 0; i < TAMANO; i++) {
            for (int j = 0; j < TAMANO; j++) {
                System.out.print("|" + tablero[i][j]);
            }
            System.out.println("|");
        }
    }



}
