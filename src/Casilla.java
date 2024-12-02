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
            case "2L": return puntosBase * 2;
            case "3L": return puntosBase * 3;
            case "2P": return puntosBase * 2;
            case "3P": return puntosBase * 3;
            default: return puntosBase;
        }
    }

    @Override
    public String toString() {
        if (ficha != null) {
            return ficha.toString();
        }
        return (bonificacion != null) ? bonificacion : " ";
    }
}
