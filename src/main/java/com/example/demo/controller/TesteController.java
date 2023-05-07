package com.example.demo.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Enumeration;

@RestController
@RequestMapping("/v1/teste")
public class TesteController {

    @PostMapping(value = "")
    public ResponseEntity<?> ecdsaPost(
            HttpServletRequest request,
            @RequestBody String requestBody,
            @RequestHeader(value = "Header-Teste", required = false) String headerTeste
    ) throws IOException {

        System.out.println("\nController...");
        System.out.println(request);
        System.out.println(requestBody);
        System.out.println(headerTeste);

        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = (String) headerNames.nextElement();
            if (!key.equals("header1") && !key.equals("header2")) {
                String value = request.getHeader(key);
                System.out.println("Key: " + key + " --- Value: " + value);
            }
        }

        System.out.println(headerTeste);

        return new ResponseEntity<String>("Sucesso", HttpStatus.OK);
    }

}


