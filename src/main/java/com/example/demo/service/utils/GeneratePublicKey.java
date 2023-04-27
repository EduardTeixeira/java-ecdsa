package com.example.demo.service.utils;

import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class GeneratePublicKey {

    public PublicKey generatePublicKeyPem(String publicKeyPem) throws Exception {

        byte[] publicKeyBytes = Base64.getDecoder().decode(publicKeyPem);

        X509EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(publicKeyBytes);

        KeyFactory keyFactory = KeyFactory.getInstance("EC");

        PublicKey PUBLIC_KEY = keyFactory.generatePublic(publicKeySpec);

        return PUBLIC_KEY;
    }

}
