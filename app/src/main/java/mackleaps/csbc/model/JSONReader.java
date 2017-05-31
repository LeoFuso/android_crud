package mackleaps.csbc.model;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Leonardo Fuso on 31/05/17.
 */

public class JSONReader {


    public static <T> Object readJSON(Class<T[]> batata, String json) throws IOException {

        ObjectMapper mapper = new ObjectMapper();

        List<T> list = Arrays.asList(mapper.readValue(json, batata));

        return list;
    }
}
