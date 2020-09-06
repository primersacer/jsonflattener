import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import com.google.gson.stream.JsonReader;
import com.google.gson.TypeAdapter;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.StringReader;
import java.util.Scanner;
import java.util.Map;

/**
 * A utility for inputting json via standard input and outputting flattened
 * json via standard output
 */
public class JsonFlattener {
    private final static char PATH_DELIMITER = '.';

    /**
     * flattenJsonHelper is used to flatten a json.  Recursively walks down a
     * JSON object to construct flattened paths.  Whenever a terminal value is
     * found, flatJson is updated with path:value
     * @param jsonElement JsonElement to flatten
     * @param sb StringBuilder to store flattened paths
     * @param flatJson Flattened json
     */
    private static void flattenJsonHelper(final JsonElement jsonElement, StringBuilder sb, JsonObject flatJson) {
        if (jsonElement == null) {
            return;
        }
        int len = sb.length();
        for (Map.Entry<String, JsonElement> set : jsonElement.getAsJsonObject().entrySet()) {
            sb.append(set.getKey());
            if (set.getValue().isJsonObject()) {
                flattenJsonHelper(set.getValue(), sb.append(PATH_DELIMITER), flatJson);
            } else {
                flatJson.add(sb.toString(), set.getValue());
            }
            sb.delete(len, sb.length());
        }
    }

    /**
     * flattenJson Takes a String representation of a JSON object as input
     * and outputs a String representation of a flattened JSON object
     * @param str String representation of a JSON object
     * @return String representation of a JSON object
     */
    private static String flattenJson(String str) {
        JsonObject flatJsonObj = new JsonObject();
        final JsonObject jsonObject = strictJsonParse(str);
        flattenJsonHelper(jsonObject, new StringBuilder(), flatJsonObj);
        return flatJsonObj.toString();
    }

    /**
     * Parse the json string into a JsonObject
     * @param json String representation of a JSON object
     * @return Parsed JSON object
     * @throws JsonSyntaxException If invalid json is detected
     */
    private static JsonObject strictJsonParse(String json) {
        try {
            TypeAdapter<JsonObject> adapter = new Gson().getAdapter(JsonObject.class);
            try (JsonReader reader = new JsonReader(new StringReader(json))) {
                JsonObject result = adapter.read(reader);
                reader.hasNext();
                return result;
            }
        } catch (IOException e) {
            throw new JsonSyntaxException(e);
        }
    }

    /**
     * main parses standard input as a json object and outputs a flattened json
     */
    public static void main(String[] args) {
        /* Construct input json from standard input */
        StringBuilder sb = new StringBuilder();
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            sb.append(scanner.nextLine());
        }
        String inputJson = sb.toString();

        /* Flatten the json and print to standard output */
        System.out.println(flattenJson(inputJson));
    }
}
