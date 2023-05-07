package com.example.demo.service.utils;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class GeneratePublicKeyRsa {

    public static PublicKey getPublicKey(String base64PublicKey) {

        PublicKey publicKey = null;

        try {
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(Base64.getDecoder().decode(base64PublicKey.getBytes()));
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            publicKey = keyFactory.generatePublic(keySpec);
            return publicKey;
        } catch (NoSuchAlgorithmException | InvalidKeySpecException exception) {
            // LOGGER.error("Erro ao definir chave publica (RSA)
            // ".concat(exception.getMessage()));
            exception.printStackTrace();
        }
        return null;
    }

}
