package mackleaps.csbc.model;

import android.support.annotation.NonNull;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static android.provider.ContactsContract.CommonDataKinds.Website.URL;

/**
 * Created by Leonardo Fuso on 31/05/17.
 */

public class JSONReader {



    public static <T> Object deserializeList(Class<T[]> batata, String json) throws IOException {

        ObjectMapper mapper = new ObjectMapper();

        List<T> list = Arrays.asList(mapper.readValue(json, batata));

        return list;
    }

    public static String serializeObject(Object batata) throws IOException {

        ObjectMapper mapper = new ObjectMapper();

        return mapper.writeValueAsString(batata);
    }

    public static <T> Object deserializeObject(Class<T> batata, String json) throws IOException{
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(json, batata.getClass());
    }



}

