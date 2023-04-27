package com.example.demo.service;

import javax.crypto.Cipher;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;

public class RsaService {

    public static void main(String[] args) throws Exception {

        try {

            // Geração das chaves pública e privada
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(512);
            KeyPair keyPair = keyPairGenerator.generateKeyPair();
            PublicKey publicKey = keyPair.getPublic();
            PrivateKey privateKey = keyPair.getPrivate();

            // Criptografar uma mensagem
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            byte[] plaintext = "mensagem secreta".getBytes();
            byte[] ciphertext = cipher.doFinal(plaintext);
            System.out.println("Mensagem criptografada: " + new String(ciphertext));

            // Descriptografar a mensagem criptografada
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            byte[] decrypted = cipher.doFinal(ciphertext);
            System.out.println("Mensagem descriptografada: " + new String(decrypted));

        } catch (Exception e) {

            e.printStackTrace();

        }
        
    }

}
