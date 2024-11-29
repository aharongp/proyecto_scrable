import java.util.ArrayList;

public class SpanishBagTest {

    public void sBagTest(){
        CharactersBag spanishBag = new SpanishBag();
        ArrayList<Character> temp = new ArrayList<>();
        spanishBag.reset();
        while(spanishBag.remaning()>0) {
            temp=spanishBag.get(7);
            System.out.println(temp);
        }
        System.out.println(spanishBag.remaning());
    }
}