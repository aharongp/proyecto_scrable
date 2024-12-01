import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Authentication {

    public Jugador login(String alias, String email){

        return null;
    }
    public Jugador register(String alias, String email){

        return null;
    }

    public boolean validateEmail(String email){
        Pattern pattern = Pattern.compile("^([0-9a-zA-Z]+[-._+&])*[0-9a-zA-Z]+@([-0-9a-zA-Z]+[.])+[a-zA-Z]{2,6}$");
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
