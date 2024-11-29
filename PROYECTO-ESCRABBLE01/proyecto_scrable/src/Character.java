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

    @Override
    public String toString() {
        return "Symbol=("+getSymbol()+") Point=("+getPoints()+")";
    }
}