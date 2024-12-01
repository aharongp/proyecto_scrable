import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Estadisticas {
    private String alias;
    private long scoreTotal;
    private long tiempoTotal;
    private long palabrasJugadasTotal;
    private long partidasJugadasTotal;

    /*public static void recuperar(){
        ManejadorDeArchivos manejadorDeArchivos=new ManejadorDeArchivos();
        String nombre="84fcc350-f4a0-4487-a749-1dacab9b5e34";
        Partida partida=manejadorDeArchivos.restaurar(nombre);
        partida.continuarPartida();
    }*/

    public void estad(String alias){
        scoreTotal=0;
        palabrasJugadasTotal=0;
        partidasJugadasTotal=0;
        tiempoTotal=0;
        ManejadorDeArchivos manejadorDeArchivos=new ManejadorDeArchivos();
        File directory = new File(System.getProperty("user.dir"));
        String extension = ".par";
        List<String> fileNames = BuscarArchivo.getFileNamesWithExtension(directory, extension);
        System.out.println(fileNames.size());
        ArrayList<Partida> partidas=new ArrayList<>();
        for (String fileName : fileNames) {
            System.out.println("Nombre de archivo="+fileName);
            Partida partida=manejadorDeArchivos.restaurarPartida(fileName);
            if (partida.getJugador1().getAlias().equals(alias) || partida.getJugador2().getAlias().equals(alias)){
                partidas.add(partida);
            }
        }
        System.out.println("ID-------------------------------------SCORE---TIEMPO-----PALABRAS-------GANO/PERDIO");
        long score=0;
        long tiempo=0;
        long palabras=0;
        for (Partida partida : partidas){
            if (partida.getJugador1().getAlias().equals(alias)){
                score=partida.getJugador1().getScore();
                tiempo=partida.getTime();
                palabras=partida.getJugador1().getPalabrasJugadas();
            }
            else{
                score=partida.getJugador2().getScore();
                tiempo=partida.getTime();
                palabras=partida.getJugador2().getPalabrasJugadas();
            }
            System.out.println(partida.uuid+"       "+score+"       "+tiempo+"       "+palabras);
            partidasJugadasTotal=partidasJugadasTotal+1;
            scoreTotal=scoreTotal+score;
            tiempoTotal=tiempoTotal+tiempo;
            palabrasJugadasTotal=palabrasJugadasTotal+palabras;
        }
        System.out.println("Estadisticas totales del jugador " +alias +" son:\n"
                +"Score Total=" + scoreTotal
                +"\nTiempo total jugado=" + tiempoTotal
                +"\nPalabras jugadas=" + palabrasJugadasTotal
                +"\nPartidas jugadas=" + partidasJugadasTotal
        );
    }
}