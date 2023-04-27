package com.example.demo.service.utils;

import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;

public class GeneratePrivateKey {

    public PrivateKey generatePrivateKeyPem(String privateKeyPem) throws Exception {

        byte[] privateKeyBytes = Base64.getDecoder().decode(privateKeyPem);

        PKCS8EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(privateKeyBytes);

        KeyFactory keyFactory = KeyFactory.getInstance("EC");

        PrivateKey PRIVATE_KEY = keyFactory.generatePrivate(privateKeySpec);

        return PRIVATE_KEY;
    }

}
