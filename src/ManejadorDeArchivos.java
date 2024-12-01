import java.io.*;

public class ManejadorDeArchivos {

    public void salvarPartida(Partida partida) {
        String nombreDeArchivo = partida.getId();
        File archivo = new File(nombreDeArchivo);
        try {
            FileWriter escrituraArchivo = new FileWriter(archivo);
            BufferedWriter bw = new BufferedWriter(escrituraArchivo);
            bw.write(JSONMapper.objectoToJson(partida));
            bw.flush();
            bw.close();
        } catch (IOException e) {
            System.out.println("Excepcion=" + e.getStackTrace());
        }

    }

    public Partida restaurar(String id) {
        try {
            FileReader fr = new FileReader(id);
            BufferedReader br = new BufferedReader(fr);
            String json = br.readLine();
            System.out.println(json);
            return JSONMapper.jsonToObject(json, Partida.class);
        } catch (FileNotFoundException e) {
            System.out.println("Excepcion=" + e.getStackTrace());
        } catch (IOException e) {
            System.out.println("Excepcion=" + e.getStackTrace());
        }
        return null;
    }
}
