import java.util.ArrayList;

public class FichasJugador {
    private ArrayList<Character> fichas;

    public FichasJugador() {
        fichas = new ArrayList<>();
    }

    public void agregarFicha(Character ficha) {
        fichas.add(ficha);
    }

    public Character buscar(String simbolo) {
        Character result = null;
        for (Character c : fichas) {
            if (c.getSymbol().equalsIgnoreCase(simbolo)) {
                result = c;
                break;
            }
        }
        return result;
    }

    public boolean reemplazarFichas(String fichasPorExtraer, CharactersBag bag) {
        String[] porExtraer = fichasPorExtraer.split(",");
        if (porExtraer.length > bag.remaning()) {
            return false;
        }
        ArrayList<Character> extraidas = extraerFichas(fichasPorExtraer);
        if (extraidas.isEmpty()) {
            return false;
        }
        fichas.addAll(bag.get(extraidas.size()));
        bag.reponer(extraidas);
        return true;
    }

    public ArrayList<Character> extraerFichas(String fichasPorExtraer) {
        ArrayList<Character> extraidas = new ArrayList<>();
        String[] porExtraer = fichasPorExtraer.split(",");
        Character c;
        for (String ficha : porExtraer) {
            c = buscar(ficha);
            if (c != null) {
                fichas.remove(c);
                extraidas.add(c);
            }
            else {
                fichas.addAll(extraidas);
                extraidas.clear();
            }
        }
        return extraidas;
    }
}
