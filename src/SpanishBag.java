
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.ArrayList;
import java.util.Random;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SpanishBag implements CharactersBag {
    private ArrayList<Character> characterList;

    public SpanishBag() {
        characterList = new ArrayList<>();
        addAll();
    }

    public ArrayList<Character> getCharacterList() {
        return characterList;
    }

    public int numberOfCharacters() {
        return characterList.size();
    }

    public void setCharacterList(ArrayList<Character> characterList) {
        this.characterList = characterList;
    }

    @Override
    public ArrayList<Character> get(int n) {
        ArrayList<Character> extractedCharacters = new ArrayList<>();
        if (characterList.size() >= n) {
            for (int i = 0; i != n; i++) {
                extractCharacters(extractedCharacters);
            }
        }
        else {
            if (characterList.size() < n) {
                while (!characterList.isEmpty()) {
                    extractCharacters(extractedCharacters);
                }
            }
        }
        return extractedCharacters;
    }

    @Override
    public void reset() {
        characterList.clear();
        addAll();
    }

    private void extractCharacters(ArrayList<Character> list) {
        Random random = new Random();
        int randomIndex = random.nextInt(characterList.size());
        Character randomItem = characterList.get(randomIndex);
        list.add(randomItem);
        characterList.remove(randomItem);
    }

    private void add(String symbol, int point, int times) {
        for (int i = 0; i<times; i++) {
            Character character = new Character(symbol, point);
            characterList.add(character);
        }
    }

    private void addAll() {
        add("A", 1, 12);
        add("B", 4, 2);
        add("C", 3, 4);
        add("CH", 8, 1);
        add("D", 3, 5);
        add("E", 1, 12);
        add("F", 5, 1);
        add("G", 3, 2);
        add("H", 5, 2);
        add("I", 1, 6);
        add("J", 10, 1);
        add("L", 2, 4);
        add("LL", 8, 1);
        add("M", 3, 2);
        add("N", 2, 5);
        add("Ñ", 10, 1);
        add("O", 1, 9);
        add("P", 4, 2);
        add("Q", 8, 1);
        add("R", 2, 5);
        add("RR", 8, 1);
        add("S", 1, 6);
        add("T", 1, 4);
        add("U", 1, 5);
        add("V", 4, 1);
        add("X", 10, 1);
        add("Y", 5, 1);
        add("Z", 10, 1);
        add("Comodin", 0, 2);
    }

    @Override
    public String toString() {
        return "SpanishBag{"
                + "characterList=" + characterList
                + '}';
    }

    @Override
    public int remaning() {
        return characterList.size();
    }
}