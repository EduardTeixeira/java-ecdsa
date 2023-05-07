package com.example.demo.service.utils;

import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;

public class GeneratePrivateKeyEcdsa {

    public PrivateKey generatePrivateKeyPem(String privateKeyPem) throws Exception {

        KeyFactory keyFactory = KeyFactory.getInstance("EC");

        PKCS8EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(
                Base64.getDecoder().decode(privateKeyPem)
        );

        return keyFactory.generatePrivate(privateKeySpec);
    }

}
