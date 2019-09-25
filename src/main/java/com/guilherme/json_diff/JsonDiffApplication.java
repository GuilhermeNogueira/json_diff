package com.guilherme.json_diff;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.*;

@SpringBootApplication
public class JsonDiffApplication {

    public static void main(String[] args) {
        String json1 = "{\n" +
                "    \"glossary\": {\n" +
                "        \"title\": \"example glossary\",\n" +
                "\t\t\"GlossDiv\": {\n" +
                "            \"title\": \"S\",\n" +
                "\t\t\t\"GlossList\": {\n" +
                "                \"GlossEntry\": {\n" +
                "                    \"ID\": \"SGML\",\n" +
                "\t\t\t\t\t\"SortAs\": \"SGML\",\n" +
                "\t\t\t\t\t\"GlossTerm\": \"Standard Generalized Markup Language\",\n" +
                "\t\t\t\t\t\"Acronym\": \"SGML\",\n" +
                "\t\t\t\t\t\"Abbrev\": \"ISO 8879:1986\",\n" +
                "\t\t\t\t\t\"GlossDef\": {\n" +
                "                        \"para\": \"A meta-markup language, used to create markup languages such as DocBook.\",\n" +
                "\t\t\t\t\t\t\"GlossSeeAlso\": [\"GML\", \"XML\"]\n" +
                "                    },\n" +
                "\t\t\t\t\t\"GlossSee\": \"markup\"\n" +
                "                }\n" +
                "            }\n" +
                "        }\n" +
                "    }\n" +
                "}";
        String json2 = "{\"city\":\"XYZ\", \"street\":\"123 anyplace\", \"name\":\"ABC\",  \"states\":\"CA\"}";
        String json4 = "{\"city\":\"XdYZ\", \"street\":\"123 anypldace\", \"name\":\"ABC\",  \"stdates\":\"CA\"}";
        String json3 = "{\n" +
                "    \"glossary\": {\n" +
                "        \"title\": \"example glossary2\",\n" +
                "\t\t\"GlossDiv\": {\n" +
                "            \"title\": \"S\",\n" +
                "\t\t\t\"GlossList\": {\n" +
                "                \"GlossEntry\": {\n" +
                "                    \"ID\": \"SGML2\",\n" +
                "\t\t\t\t\t\"SortAs\": \"SGML\",\n" +
                "\t\t\t\t\t\"GlossTerm\": \"Standard Generalized Markup Language2\",\n" +
                "\t\t\t\t\t\"Acronym\": \"SGML\",\n" +
                "\t\t\t\t\t\"Abbrev\": \"ISO 8879:1986\",\n" +
                "\t\t\t\t\t\"GlossDef\": {\n" +
                "                        \"para\": \"A meta-markup language, 2used to create markup languages such as DocBook.\",\n" +
                "\t\t\t\t\t\t\"GlossSeeAlso\": [\"GML\", \"XML\"]\n" +
                "                    },\n" +
                "\t\t\t\t\t\"GlossSee\": \"marku2p\"\n" +
                "                }\n" +
                "            }\n" +
                "        }\n" +
                "    }\n" +
                "}";

        JSONObject jsonObject = new JSONObject(json1);
        JSONObject jsonObject2 = new JSONObject(json4);

        boolean b = JSONUtils.areEqual(jsonObject, jsonObject2);
        boolean db = jsonObject.equals(jsonObject2);

        Map<String, Object> o = (Map<String, Object>) JSONUtils.convertJsonElement(jsonObject);

        int mapSize = zzzzzz(o);

        SpringApplication.run(JsonDiffApplication.class, args);

    }

    private static Integer zzzzzz(Map<String, Object> ob1) {

        Integer total = ob1.size();

        int sum = ob1.values().stream()
                .filter(HashMap.class::isInstance)
                .map(HashMap.class::cast)
                .map(v -> zzzzzz(v))
                .mapToInt(Integer::intValue)
                .sum();


        return total;
    }

    public static class JSONUtils {

        public static boolean areEqual(Object ob1, Object ob2) throws JSONException {
            Object obj1Converted = convertJsonElement(ob1);
            Object obj2Converted = convertJsonElement(ob2);
            return obj1Converted.equals(obj2Converted);
        }

        public static Object convertJsonElement(Object elem) throws JSONException {
            if (elem instanceof JSONObject) {
                JSONObject obj = (JSONObject) elem;
                Iterator<String> keys = obj.keys();
                Map<String, Object> jsonMap = new HashMap<>();
                while (keys.hasNext()) {
                    String key = keys.next();
                    jsonMap.put(key, convertJsonElement(obj.get(key)));
                }
                return jsonMap;
            } else if (elem instanceof JSONArray) {
                JSONArray arr = (JSONArray) elem;
                Set<Object> jsonSet = new HashSet<>();
                for (int i = 0; i < arr.length(); i++) {
                    jsonSet.add(convertJsonElement(arr.get(i)));
                }
                return jsonSet;
            } else {
                return elem;
            }
        }
    }


}
