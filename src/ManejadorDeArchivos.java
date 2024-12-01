import java.io.*;

public class ManejadorDeArchivos {

    public void salvarPartida(Partida partida) {
        String nombreDeArchivo = partida.getId();
        File archivo = new File(nombreDeArchivo+".par");
        try {
            FileWriter escrituraArchivo = new FileWriter(archivo);
            BufferedWriter bw = new BufferedWriter(escrituraArchivo);
            bw.write(JSONMapper.objectoToJson(partida));
            bw.flush();
            bw.close();
        } catch (IOException e) {
            //System.out.println("Excepcion=" + e.getStackTrace());
        }

    }

    public Partida restaurarPartida(String filename) {
        try {
            FileReader fr = new FileReader(filename);
            BufferedReader br = new BufferedReader(fr);
            String json = br.readLine();
            System.out.println(json);
            return JSONMapper.jsonToObject(json, Partida.class);
        } catch (FileNotFoundException e) {
            //System.out.println("Excepcion=" + e.getMessage());
        } catch (IOException e) {
            //System.out.println("Excepcion=" + e.getMessage());
        }
        return null;
    }

    public void salvarJugador(Jugador jugador) {
        String nombreDeArchivo = jugador.getAlias();
        File archivo = new File(nombreDeArchivo+".jug");
        try {
            FileWriter escrituraArchivo = new FileWriter(archivo);
            BufferedWriter bw = new BufferedWriter(escrituraArchivo);
            bw.write(JSONMapper.objectoToJson(jugador));
            bw.flush();
            bw.close();
        } catch (IOException e) {
            //System.out.println("Excepcion=" + e.getStackTrace());
        }
    }

    public Jugador restaurarJugador(String alias) {
        try {
            FileReader fr = new FileReader(alias+".jug");
            BufferedReader br = new BufferedReader(fr);
            String json = br.readLine();
            System.out.println(json);
            return JSONMapper.jsonToObject(json, Jugador.class);
        } catch (FileNotFoundException e) {
            //System.out.println("Excepcion=" + e.getStackTrace());
        } catch (IOException e) {
            //System.out.println("Excepcion=" + e.getStackTrace());
        }
        return null;
    }
}
