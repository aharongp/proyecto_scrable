public class Casilla {
    private Character ficha;
    private String bonificacion;

    public Casilla() {
        this.ficha = null;
        this.bonificacion = null;
    }

    public Casilla(String bonificacion) {
        this.ficha = null;
        this.bonificacion = bonificacion;
    }

    public void colocarFicha(Character ficha) {
        this.ficha = ficha;
    }

    public Character getFicha() {
        return ficha;
    }

    public boolean estaVacia() {
        return ficha == null;
    }

    public void setBonificacion(String bonificacion) {
        this.bonificacion = bonificacion;
    }

    public String getBonificacion() {
        return bonificacion;
    }

    public int aplicarMultiplicador(int puntosBase) {
        if (bonificacion == null) {
            return puntosBase;
        }
        switch (bonificacion) {
            case "2L": return puntosBase * 2; // Doble letra
            case "3L": return puntosBase * 3; // Triple letra
            case "2W": return puntosBase * 2; // Doble palabra
            case "3W": return puntosBase * 3; // Triple palabra
            default: return puntosBase; // Sin bonificación
        }
    }

    @Override
    public String toString() {
        if (ficha != null) {
            return ficha.toString(); // Devuelve el símbolo de la ficha
        }
        return (bonificacion != null) ? bonificacion : " "; // Devuelve la bonificación o un espacio vacío
    }
}
