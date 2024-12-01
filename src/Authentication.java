import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Authentication {

    public Jugador login(String alias, String email){
        ManejadorDeArchivos archivo = new ManejadorDeArchivos();
        Jugador jugador = archivo.restaurarJugador(alias);
        String emailjugador = jugador.getEmail();
        if(emailjugador.equals(email)){
            return jugador;
        }else {
            return null;
        }
    }

    public Jugador register(String alias, String email){
        ManejadorDeArchivos archivo = new ManejadorDeArchivos();
        Jugador jugador = new Jugador(alias, email);
        archivo.salvarJugador(jugador);
        return jugador;
    }

    public boolean validateEmail(String email){
        Pattern pattern = Pattern.compile("^([0-9a-zA-Z]+[-._+&])*[0-9a-zA-Z]+@([-0-9a-zA-Z]+[.])+[a-zA-Z]{2,6}$");
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
