public class Character {
    private String symbol;
    private int points;

    public Character(String symbol, int points) {
        this.symbol = symbol;
        this.points = points;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    // Método nuevo para comparar caracteres
    public boolean equals(String otherSymbol) {
        return this.symbol.equals(otherSymbol);
    }

    // Método nuevo para verificar si es espacio vacío
    public boolean isEmpty() {
        return this.symbol.equals(" ");
    }

    @Override
    public String toString() {
        return getSymbol();
    }
}