import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.logging.Level;
import java.util.logging.Logger;


public abstract class JSONMapper {

    public static ObjectMapper mapper = new ObjectMapper();

    public static String objectoToJson(Object obj) {
        try {
            return mapper.writeValueAsString(obj);
        }
        catch (JsonProcessingException ex) {
            Logger.getLogger(JSONMapper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static <T> T jsonToObject(String json, Class<T> classOfT) {
        try {
            return mapper.readValue(json, classOfT);
        }
        catch (JsonProcessingException ex) {
            Logger.getLogger(JSONMapper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}