package com.example.demo.service.utils;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.util.Base64;

public class RsaUtils {

    public static byte[] encrypt(String data, String publicKey) throws BadPaddingException, IllegalBlockSizeException,
            InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException {

        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");

        cipher.init(Cipher.ENCRYPT_MODE, GeneratePublicKeyRsa.getPublicKey(publicKey));

        return cipher.doFinal(data.getBytes());
    }

    public static String decrypt(String data, String base64PrivateKey) throws IllegalBlockSizeException,
            InvalidKeyException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException {

        return decrypt(Base64.getDecoder().decode(data.getBytes()), GeneratePrivateKeyRsa.getPrivateKey(base64PrivateKey));
    }

    public static String decrypt(byte[] data, PrivateKey privateKey) throws NoSuchPaddingException,
            NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {

        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");

        cipher.init(Cipher.DECRYPT_MODE, privateKey);

        return new String(cipher.doFinal(data));
    }

}
