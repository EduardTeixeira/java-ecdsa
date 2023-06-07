package com.example.demo.controller;

import com.example.demo.model.enums.HeaderTest;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.Normalizer;
import java.util.Enumeration;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/v1/teste")
public class TesteController {

    @PostMapping(value = "")
    public ResponseEntity<?> ecdsaPost(
            HttpServletRequest request,
            @RequestBody String requestBody,
            @RequestHeader(value = HeaderTest.ACCESS_TIME, required = false) String headerTeste
    ) throws IOException {

        System.out.println("\nController...");
        System.out.println(request);
        System.out.println(headerTeste);

        System.out.println(HeaderTest.ACCESS_DATA);

        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = (String) headerNames.nextElement();
            if (!key.equals("header1") && !key.equals("header2")) {
                String value = request.getHeader(key);
                System.out.println("Key: " + key + " --- Value: " + value);
            }
        }

        System.out.println(headerTeste);


        ObjectMapper objectMapper = new ObjectMapper();

        System.out.println("\n Body...");
        System.out.println(requestBody);

        System.out.println("\n Resultado");
        String result = converterParaUmaLinha(requestBody);
        System.out.println(result);

        //String novoBody = requestBody.replaceAll("(?:\\s)\\s", "");
        //System.out.println(novoBody);

        // Remove espaços em branco
        //String jsonSemEspacos = removerEspacos(requestBody);

        //System.out.println(jsonSemEspacos);

        /*
        String jsonObj = objectMapper.writeValueAsString(requestBody);
        System.out.println(jsonObj);

         */

        return new ResponseEntity<String>(result, HttpStatus.OK);
    }

    public static String converterParaUmaLinha(String json) {
        return json.replaceAll("\\s+(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", "");
    }


    public static String removerEspacos10(String json) {

        Gson gson = new GsonBuilder().create();
        //Gson gson = new Gson();

        //Object obj = gson.fromJson(json, Object.class);
        //gson.toJson(json);

        // Object obj = gson.fromJson(gson.toJson(json.trim()), Object.class);

        //return gson.toJson(obj);
        //return gson.toJson(json.trim());

        return "obj.toString();";
    }

    public static String removerEspacos4(String json) {
        // Define o padrão de regex para localizar o espaço em branco entre ": \d"
        Pattern pattern = Pattern.compile(":\\s+\\d");
        Matcher matcher = pattern.matcher(json.trim());

        // Substitui o espaço em branco por uma string vazia
        String jsonSemEspaco = matcher.replaceAll("");

        return jsonSemEspaco;
    }

    public static String removerEspacos3(String json) {
        // Define o padrão de regex para localizar o espaço em branco entre ": 7"
        Pattern pattern = Pattern.compile(":\\s+\\d");
        Matcher matcher = pattern.matcher(json);

        // Substitui o espaço em branco por uma string vazia
        String jsonSemEspaco = matcher.replaceAll(":\\d");

        return jsonSemEspaco;
    }

    public static String removerEspacos2(String json) {
        // Define o padrão de regex para localizar os valores entre aspas duplas
        Pattern pattern = Pattern.compile("\"[^\"]+\"");

        Matcher matcher = pattern.matcher(json);

        StringBuffer sb = new StringBuffer();

        // Enquanto houver correspondência encontrada pelo padrão
        while (matcher.find()) {
            // Obtém o valor encontrado
            String match = matcher.group();

            // Remove espaços em branco do valor
            String valorSemEspacos = match.replaceAll("\\s+", "");

            // Substitui o valor original pelo valor sem espaços no StringBuffer
            matcher.appendReplacement(sb, valorSemEspacos);
        }

        // Completa a construção do StringBuffer
        matcher.appendTail(sb);

        return sb.toString();
    }

    public static String removerEspacos(String json) {
        // Define o padrão de regex para localizar espaços em branco dentro de chaves {}
        Pattern pattern = Pattern.compile("\\s+(?![^\\{]*\\})");
        Matcher matcher = pattern.matcher(json);

        // Substitui os espaços em branco por uma string vazia
        String jsonSemEspacos = matcher.replaceAll("");

        return jsonSemEspacos;
    }

    public static void main(String[] args) {
        String input = "ÁáÉéÍíÇç";
        String output = Normalizer.normalize(input, Normalizer.Form.NFD);
        output = output.replaceAll("[^\\p{ASCII}]", "");
        System.out.println(output);
    }

}


