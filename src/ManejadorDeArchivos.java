import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase encargada de la gestión de archivos relacionados con las partidas y jugadores.
 * Permite salvar y restaurar partidas y jugadores desde archivos, así como buscar partidas guardadas.
 */
public class ManejadorDeArchivos {

    /**
     * Guarda una partida en un archivo con extensión ".par".
     *
     * @param partida La partida que se desea guardar.
     */
    public void salvarPartida(Partida partida) {
        String nombreDeArchivo = partida.getId();
        File archivo = new File(nombreDeArchivo + ".par");
        try {
            FileWriter escrituraArchivo = new FileWriter(archivo);
            BufferedWriter bw = new BufferedWriter(escrituraArchivo);
            bw.write(JSONMapper.objectoToJson(partida)); // Convierte la partida a formato JSON y la guarda
            bw.flush();
            bw.close();
        } catch (IOException e) {
            // Handle exceptions (comentado para fines de depuración)
        }
    }

    /**
     * Restaura una partida desde un archivo JSON dado.
     *
     * @param filename El nombre del archivo a restaurar.
     * @return El objeto Partida restaurado, o null si ocurre algún error.
     */
    public Partida restaurarPartida(String filename) {
        try {
            FileReader fr = new FileReader(filename);
            BufferedReader br = new BufferedReader(fr);
            String json = br.readLine();
            return JSONMapper.jsonToObject(json, Partida.class); // Convierte el JSON a un objeto Partida
        } catch (FileNotFoundException e) {
            // Handle exceptions (comentado para fines de depuración)
        } catch (IOException e) {
            // Handle exceptions (comentado para fines de depuración)
        }
        return null;
    }

    /**
     * Guarda los datos de un jugador en un archivo con extensión ".jug".
     *
     * @param jugador El jugador que se desea guardar.
     */
    public void salvarJugador(Jugador jugador) {
        String nombreDeArchivo = jugador.getAlias();
        File archivo = new File(nombreDeArchivo + ".jug");
        try {
            FileWriter escrituraArchivo = new FileWriter(archivo);
            BufferedWriter bw = new BufferedWriter(escrituraArchivo);
            bw.write(JSONMapper.objectoToJson(jugador)); // Convierte al jugador a formato JSON y lo guarda
            bw.flush();
            bw.close();
        } catch (IOException e) {
            // Handle exceptions (comentado para fines de depuración)
        }
    }

    /**
     * Restaura los datos de un jugador desde un archivo dado.
     *
     * @param alias El alias del jugador cuya información se desea restaurar.
     * @return El objeto Jugador restaurado, o null si ocurre algún error.
     */
    public Jugador restaurarJugador(String alias) {
        try {
            FileReader fr = new FileReader(alias + ".jug");
            BufferedReader br = new BufferedReader(fr);
            String json = br.readLine();
            return JSONMapper.jsonToObject(json, Jugador.class);
        } catch (FileNotFoundException e) {
            // Handle exceptions (comentado para fines de depuración)
        } catch (IOException e) {
            // Handle exceptions (comentado para fines de depuración)
        }
        return null;
    }

    /**
     * Busca una partida guardada entre dos jugadores por sus alias.
     *
     * @param alias El alias del primer jugador.
     * @param alias2 El alias del segundo jugador.
     * @return La partida encontrada entre ambos jugadores, o null si no se encuentra ninguna.
     */
    public Partida buscarPartida(String alias, String alias2) {
        ManejadorDeArchivos manejadorDeArchivos = new ManejadorDeArchivos();
        File directory = new File(System.getProperty("user.dir"));
        String extension = ".par";
        List<String> fileNames = BuscarArchivo.getFileNamesWithExtension(directory, extension);
        ArrayList<Partida> partidas = new ArrayList<>();

        for (String fileName : fileNames) {
            Partida partida = manejadorDeArchivos.restaurarPartida(fileName);
            if (partida.getJugador1().getAlias().equals(alias) || partida.getJugador2().getAlias().equals(alias)) {
                partidas.add(partida);
            }
        }

        for (Partida partida : partidas) {
            if (partida.getJugador1().getAlias().equals(alias) && partida.getJugador2().getAlias().equals(alias2)) {
                return partida;
            }
        }
        return null;
    }
}
