import java.util.ArrayList;

public class FichasJugador {
    private ArrayList<Character> fichas;

    public FichasJugador() {
        fichas = new ArrayList<>();
    }

    public void setComodin(String letra){
        for ( Character ficha : fichas){
            String symbol = ficha.getSymbol();
            if (symbol.equalsIgnoreCase("+")){
                ficha.setSymbol(letra);
                break;
            }
        }
    }

    public ArrayList<Character> getFichas() {
        return fichas;
    }

    public void setFichas(ArrayList<Character> fichas) {
        this.fichas = fichas;
    }

    public boolean existeComodin(){
        for ( Character ficha : fichas){
            String symbol = ficha.getSymbol();
            if (symbol.equalsIgnoreCase("+")) return true;
        }
        return false;
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

    public int cantidadDeFichas(){
        return fichas.size();
    }

    public void reponer(ArrayList<Character> characterList) {
        fichas.addAll(characterList);
    }

    public boolean existeCaracterEnJugador(String simbolo) {
        boolean result = false;
        for (Character c : fichas) {
            if (c.getSymbol().equalsIgnoreCase(simbolo)) {
                result = true;
                break;
            }
        }
        return result;
    }

    @Override
    public String toString() {
        String result="";
        String separador="";
        for (Character c : fichas) {
            result=result+separador+c.getSymbol();
            separador=",";
        }
        return result;
    }
}
