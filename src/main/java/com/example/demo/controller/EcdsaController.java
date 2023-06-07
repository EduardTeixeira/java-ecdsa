package com.example.demo.controller;

import com.example.demo.service.EcdsaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.Normalizer;

@RestController
@RequestMapping("/v1/ecdsa")
public class EcdsaController {

    @Autowired
    private EcdsaService ecdsaService;

    @GetMapping(value = "")
    public ResponseEntity<?> teste() {
        String str = "Ecdsa Teste Controller";
        return new ResponseEntity<>(str, HttpStatus.OK);
    }

    @PostMapping(value = "")
    public ResponseEntity<?> ecdsaPost(
            @RequestBody String requestBody,
            @RequestHeader(value = "Header-1", required = false) String header1,
            @RequestHeader(value = "Header-2", required = false) String header2,
            @RequestHeader(value = "Header-3", required = false) String header3,
            @RequestHeader(value = "Header-4", required = false) String header4,
            @RequestHeader(value = "Header-5", required = false) String header5,
            @RequestHeader(value = "Header-6", required = false) String header6,
            @RequestHeader(value = "Header-7", required = false) String header7
    ) {

        boolean result = ecdsaService.ecdsaVerify(requestBody, header1, header2, header3, header4, header5, header6, header7);

        if (result == true) {
            return new ResponseEntity<String>("Verificando a assinatura ::: " + result, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Não autorizado. Resultado ::: " + result, HttpStatus.UNAUTHORIZED);
        }
    }

    public static void main(String[] args) {
        String input = "ÁáÉéÍí";
        String output = Normalizer.normalize(input, Normalizer.Form.NFD);
        output = output.replaceAll("[^\\p{ASCII}]", "");
        System.out.println(output);
    }

}
