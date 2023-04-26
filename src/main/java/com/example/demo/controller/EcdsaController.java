package com.example.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;

@RestController
@RequestMapping("/v1/mtls")
public class EcdsaController {

    @GetMapping(value = "")
    public ResponseEntity<?> teste() {
        String str = "mTLS Teste Controller";
        return new ResponseEntity<>(str, HttpStatus.OK);
    }

    @PostMapping(value = "")
    public ResponseEntity<?> mtlsTeste(
            @RequestHeader(value = "Access-Signature", required = false) String accessSignature
    ) {

        try {

            if(accessSignature.equals("null")){

            }

            System.out.println("access-signature: " + accessSignature);

            byte[] xBytes = Base64.getUrlDecoder().decode("AbFCmkni9pvGC1o9uAOhPGfgveJrk9Y4NgtNHLyb6FtgwgMHXSMZPoFLsA03neFa1i2-36GnT_zTydSbyMZMS9Py".getBytes());
            System.out.println(xBytes);

            byte[] yBytes = Base64.getUrlDecoder().decode("AXR3qFe3LBJvY7pXrBw3bQdUP6ozsHZqa7VIFUVGnY5a7iakifA6QeGmtNbcGI9y32nJzHorDcYKLQ38BUbWd9JM".getBytes());
            System.out.println(yBytes);

            String curveName = "secp521r1";

            return new ResponseEntity<String>("OK", HttpStatus.OK);

        } catch (Exception e) {

            e.printStackTrace();

            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);

        }

    }

}
