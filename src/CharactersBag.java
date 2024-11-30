import java.util.ArrayList;
import java.util.List;

public interface CharactersBag {
    public ArrayList<Character> get(int n);
    public void reset();
    public int remaning();
    public void reponer(ArrayList<Character> characterList);
}
