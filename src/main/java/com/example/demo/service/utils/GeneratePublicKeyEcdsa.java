package com.example.demo.service.utils;

import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

@Component
public class GeneratePublicKeyEcdsa {

    public static PublicKey generatePublicKeyPem(String publicKeyPem) throws Exception {

        KeyFactory keyFactory = KeyFactory.getInstance("EC");

        X509EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(
                Base64.getDecoder().decode(publicKeyPem)
        );

        return keyFactory.generatePublic(publicKeySpec);
    }

    public static void main(String[] args) {

        File file = new File("chave_ecdsa.public");

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {

            String line;

            StringBuilder sb = new StringBuilder();

            boolean inContent = true;

            while ((line = br.readLine()) != null) {
                if (line.startsWith("-----BEGIN") || line.startsWith("-----END")) {
                    continue;
                }
                if (inContent) {
                    sb.append(line.trim());
                }
                if (line.equals("-----BEGIN CERTIFICATE-----")) {
                    inContent = true;
                } else if (line.equals("-----END CERTIFICATE-----")) {
                    inContent = false;
                }
            }

            String base64Content = sb.toString();
            System.out.println(base64Content);

            PublicKey pubKey = generatePublicKeyPem(base64Content);
            System.out.println(pubKey);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
