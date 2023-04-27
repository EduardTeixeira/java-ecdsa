package com.example.demo.service.utils;

import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;

public class GeneratePrivateKey {

    public PrivateKey generatePrivateKeyPem(String privateKeyPem) throws Exception {

        byte[] privateKeyBytes = Base64.getDecoder().decode(privateKeyPem);

        PKCS8EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(privateKeyBytes);

        KeyFactory keyFactory = KeyFactory.getInstance("EC");

        return keyFactory.generatePrivate(privateKeySpec);
    }

    public static class HexToString {

        public static void main(String[] args) {

            String b = "0xfd00000aa8660b5b010006acdc0100000101000100010000";
            byte[] bytes = hexStringToByteArray(b);
            String st = new String(bytes, StandardCharsets.UTF_8);
            System.out.println(st);

            String hexString = "01b8bc04f0a1051083d0bf385fb4d7c29fea37250296fdb739ac943430f51d529d69639f5462d39c0fd445372c6fe763ed65bac4a944a14b04249cbc2809d95bc87a008a34e6d81aa8f5858ac0eccf8a5e1112ea80e9d2d0cddf9fd90f129699d082f251d87faaf31ef38413a3f472d5bebd6a67c72bc3e9c39b162562a0f93947cd536f";
            byte[] bytes2 = hexStringToByteArray(hexString);
            String result= new String(bytes2, StandardCharsets.UTF_8);
            System.out.println(result);

            // Decode data on other side, by processing encoded data
            byte[] valueDecoded = org.apache.commons.codec.binary.Base64.decodeBase64(result);
            System.out.println("Decoded value is: " + new String(valueDecoded));

        }

        public static byte[] hexStringToByteArray(String hex) {
            int l = hex.length();
            byte[] data = new byte[l / 2];
            for (int i = 0; i < l; i += 2) {
                data[i / 2] = (byte) ((Character.digit(hex.charAt(i), 16) << 4)
                        + Character.digit(hex.charAt(i + 1), 16));
            }
            return data;
        }

    }
}
