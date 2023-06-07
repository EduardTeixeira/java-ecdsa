package com.example.demo.service.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MapsAndJsonExamples {

    public static Map<String, Object> readValueAsMap(String value) {
        try {
            ObjectMapper objm = new ObjectMapper();
            return objm.readValue(value, new TypeReference<Map<String, Object>>() {
            });
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String writeValueAsString(Object value) {
        try {
            ObjectMapper objm = new ObjectMapper();
            return objm.writeValueAsString(value);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws JsonProcessingException {

        String input111 = "{\"fi\": 7,\"chn\": \"3\",\"trn\": 640,\"seqFlow\": 8,\"data\": {\"MULTI_DATA\": [{\"trn\": 640,\"seqFlow\": 8,\"ID_CONCILIACAO\": \"AILAB27FBA5BC744E30AA09E1BAFB5A8EBF\",\"ID_DISPOSITIVO\": 1}]}}";

        ObjectMapper objectMapper = new ObjectMapper();

        // Converter a string em um Map
        Map<String, Object> map = null;
        try {
            map = readValueAsMap(input111);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Exemplo de uso do Map
        System.out.println(map.get("fi")); // 7

        Map<String, Object> mapDataInput = (Map<String, Object>) map.get("data");

        map.remove("data");

        Map<String, Object> mapComEmail = new HashMap<>();
        mapComEmail.put("FILE_NAME", "nomeArquivo.pdf");
        mapComEmail.put("FILE_DATA", "meuData");

        ArrayList<Object> arrayEmail = new ArrayList<>();
        arrayEmail.add(mapComEmail);

        mapDataInput.put("ARQUIVOS", arrayEmail);

        map.put("data", mapDataInput);

        JSONObject jsonOb1 = new JSONObject(map);
        System.out.println("\n\nString Json To Json --- usando JSON OBJECT @@@");
        System.out.printf("JSON: %s", jsonOb1);
        Object jsonKeyData = jsonOb1.get("data");
        System.out.println();


        Map<String, Object> mapaComAttachEmail = new HashMap();
        mapaComAttachEmail.put("FILE_NAME", "nomeArquivo.pdf");
        mapaComAttachEmail.put("FILE_DATA", "meubase64");

        ArrayList<Object> listEmail = new ArrayList<>();
        listEmail.add(mapaComAttachEmail);

        Map<String, Object> mapaComData = new HashMap<String, Object>();
        mapaComData.put("TXT_CNPJ", "value1");
        mapaComData.put("TXT_NOME", "value2");
        mapaComData.put("SEND_EMAIL", true);
        mapaComData.put("ATTACH_EMAIL", mapaComAttachEmail);
        mapaComData.put("TESTE_EMAIL", listEmail);

        Map<String, Object> mapa123 = new HashMap();
        mapa123.put("TRN", 640);
        mapa123.put("SEQFLOW", 11);
        mapa123.put("data", mapaComData);


        JSONObject jsonOb = new JSONObject(mapa123);
        System.out.println("\n\nEXEMPLO JSON usando JSON OBJECT @@@");
        System.out.printf("JSON: %s", jsonOb);
        System.out.println();


        String jsonJack = new ObjectMapper().writeValueAsString(mapa123);
        System.out.println("\n\nEXEMPLO JSON usando JACKSON @@@");
        System.out.printf("JSON: %s", jsonJack);
        System.out.println();


        Gson gson = new Gson();
        JsonObject jsonGson = gson.toJsonTree(mapa123).getAsJsonObject();
        System.out.println("\n\nEXEMPLO JSON usando GSON @@@");
        System.out.printf("JSON: %s", jsonGson);
        System.out.println();

    }

}
